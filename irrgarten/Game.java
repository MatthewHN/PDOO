package irrgarten;

import java.util.ArrayList;

public class Game {
    private static final int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    private ArrayList<Monster> monsters;
    private ArrayList<Player> players;
    private Player currentPlayer = new Player('0',Dice.randomIntelligence(), Dice.randomStrength());
    private Labyrinth labyrinth = new Labyrinth(10,20,9,17);

    public Game(int nplayers){
        // Inicializar la lista de jugadores y monstruos
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        log = ""; // Inicializar el registro de eventos
        // Crear y añadir jugadores
        for (int i = 0; i < nplayers; i++) {
            char playerNumber = (char) ('0' + i);
            players.add(new Player(playerNumber, Dice.randomIntelligence(), Dice.randomStrength()));
        }
        // Determinar quién comienza y establecer el jugador actual
        currentPlayerIndex = Dice.whoStarts(nplayers);
        currentPlayer = players.get(currentPlayerIndex);
        configureLabyrinth();
        labyrinth.spreadPlayers(players);
    }

    public boolean finished(){
        return labyrinth.haveAWinner();
    }

    public boolean nextStep(Directions preferredDirections){
        throw new UnsupportedOperationException();
    }

    public GameState getGameState(){
        return new GameState(labyrinth.toString(),players.toString(),monsters.toString(),currentPlayer.getNumber(),finished(),log);
    }

    private void configureLabyrinth(){
        char[][] laberinto = {
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'M', 'X', 'M', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'M', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'M', 'X', 'X', 'X', 'X', 'M', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'M', 'X', 'X'},
                {'X', 'M', 'X', 'X', 'X', 'M', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'M', 'X', 'X', 'M', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'M', 'X', 'X', 'X', 'X', 'M', 'X', 'M', 'X', 'X', 'X', 'M', 'X', 'X', 'X', 'X', 'M', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'E', 'X', 'X'}
        };

        // Configura el laberinto
        for (int row = 0; row < labyrinth.getnRows(); row++) {
            for (int col = 0; col < labyrinth.getnCols(); col++) {
                if (laberinto[row][col] == 'M') {
                    // Agrega un monstruo en la posición (row, col)
                    labyrinth.addMonster(row, col, new Monster("monstruo", Dice.randomIntelligence(), Dice.randomStrength()));
                } else if (laberinto[row][col] == 'E') {
                    // Establece la casilla de salida en la posición (row, col)
                    labyrinth.setExit(row, col);
                } else if (laberinto[row][col] == 'X') {
                    // Agrega un bloque en la posición (row, col) con orientación aleatoria entre vertical y horizontal

                    int orientation = Dice.randomPos(2); // 0 para horizontal, 1 para vertical
                    if (orientation == 0) {
                        labyrinth.addBlock(Orientation.HORIZONTAL, row, col, 1);
                    } else {
                        labyrinth.addBlock(Orientation.VERTICAL, row, col, 1);
                    }
                }
            }
        }
    }

    private void nextPlayer(){
        // Incrementar el índice del jugador actual para que esté en rango
        currentPlayerIndex++;

        // Si el índice supera el último jugador, volver al primer jugador
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }

        // Actualizar el jugador actual
        currentPlayer = players.get(currentPlayerIndex);
    }

    private Directions actualDirection(Directions preferredDirection){
        int currentRow = this.currentPlayer.getRow();
        int currentCol = this.currentPlayer.getCol();
        Directions[] validMoves = labyrinth.validMoves(currentRow, currentCol).toArray(new Directions[0]);

        return this.currentPlayer.move(preferredDirection, validMoves); 
    }

    private GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;

        float playerAttack = this.currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);

        while ((!lose) && (rounds < MAX_ROUNDS)){
            winner = GameCharacter.MONSTER;
            rounds++;

            float monsterAttack = monster.attack();
            lose = this.currentPlayer.defend(monsterAttack);

            if (!lose) {
                playerAttack = this.currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }

        this.logRounds(rounds, MAX_ROUNDS);

        return winner;
    }

    private void manageReward(GameCharacter winner){
        if (winner == GameCharacter.PLAYER){
            this.currentPlayer.receiveReward();
            this.logPlayerWon();
        }
        else{
            this.logMonsterWon();
        }
    }

    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();

        if (resurrect){
            this.currentPlayer.resurrect();
            this.logResurrected();
        }
        else{
            this.logPlayerSkipTurn();
        }
    }

    private void logPlayerWon(){
        log += currentPlayer.getName() + " ha ganado el combate.\n";
    }

    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate.\n";
    }

    private void logResurrected(){
        log += currentPlayer.getName() + " ha resucitado.\n";
    }

    private void logPlayerSkipTurn(){
        log += currentPlayer.getName() + " ha perdido el turno por estar muerto.\n";
    }

    private void logPlayerNoOrders(){
        log += currentPlayer.getName() + " no ha seguido las instrucciones del jugador humano (no fue posible).\n";
    }

    private void logNoMonster(){
        log += currentPlayer.getName() + " se ha movido a una celda vacía o no le ha sido posible moverse.\n";
    }

    private void logRounds(int rounds, int max){
        log += "Se han producido " + rounds + " de " + max + " rondas de combate.\n";
    }
}
