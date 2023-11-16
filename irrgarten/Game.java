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
        boolean dead = this.currentPlayer.dead();
        if (!dead) {
            Directions direction = this.actualDirection(preferredDirections);

            if (direction != preferredDirections) {
                this.logPlayerNoOrders();
            }

            Monster monster = this.labyrinth.putPlayer(direction, currentPlayer);

            if (monster == null) {
                this.logNoMonster();
            } else {
                GameCharacter winner = this.combat(monster);
                this.manageReward(winner);
            }
        }
        else {
            this.manageResurrection();
        }

        boolean endGame = this.finished();

        if (!endGame) {
            this.nextPlayer();
        }

        return endGame;
    }

    public GameState getGameState(){
        return new GameState(labyrinth.toString(),players.toString(),monsters.toString(),currentPlayer.getNumber(),finished(),log);
    }

    private void configureLabyrinth(){
        Monster m1=new Monster("Monster 1", Dice.randomIntelligence(),Dice.randomStrength());
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 1, 1);
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 2, 1);
        labyrinth.addBlock(Orientation.VERTICAL, 1, 4, 1);
        labyrinth.addMonster(0,1, m1);
        monsters.add(m1);
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
