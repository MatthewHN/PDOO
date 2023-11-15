package irrgarten;

import java.util.ArrayList;

public class Labyrinth {
    private static final char BLOCK_CHAR = 'X';
    private static final char MONSTER_CHAR = 'M';
    private static final char EMPTY_CHAR = '-';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    private final int nRows;
    private final int nCols;
    private int exitRow;
    private int exitCol;

    private static LabyrinthSquare[][] squares;
    private static PlayerSquare[][] playerPositions;
    private static MonsterSquare[][] monsterPositions;

    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows=nRows;
        this.nCols=nCols;
        this.exitRow=exitRow;
        this.exitCol=exitCol;

        //Inicializar la matriz de cuadrículas del laberinto
        squares = new LabyrinthSquare[nRows][nCols];
        for (int i=0; i<nRows; i++){
            for (int j=0; j<nCols; j++){
                squares[i][j] = new LabyrinthSquare(i,j,' ');
            }
        }
        squares[exitRow][exitCol].setContent(EXIT_CHAR);
    }

    public int getnRows(){return this.nRows;}
    public int getnCols(){return this.nCols;}
    public void setExit(int row, int col){
        this.exitRow = row;
        this.exitCol = col;
    }

    public void spreadPlayers(ArrayList<Player> players){
        for (Player p : players){
            //Encontrar una posición vacía aleatoria
            int[] pos = randomEmptyPos();

            //Colocar al jugador en la posición
            putPlayer2D(-1,-1,pos[0],pos[1],p);
        }
    }

    public boolean haveAWinner() {
        return playerPositions[exitRow][exitCol] != null && playerPositions[exitRow][exitCol].hasPlayer();
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<nRows; i++){
            for (int j=0; j<nCols; j++){
                if (i==exitRow && j== exitCol){sb.append(EXIT_CHAR);}
                else if (playerPositions[i][j] != null && playerPositions[i][j].hasPlayer()) {
                    sb.append('P'); //irrgarten.Player
                } else if (playerPositions[i][j] != null && monsterPositions[i][j].hasMonster()) {
                    sb.append(MONSTER_CHAR); //Monstruo
                } else if (squares[i][j].isBlocked()) {
                    sb.append(BLOCK_CHAR);
                }
                else {
                    sb.append(EMPTY_CHAR);
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void addMonster(int row, int col, Monster monster){
        //Comprobar si la posición está dentro del laberinto y está vacía
        if(emptyPos(row,col) && posOK(row,col)){
            monsterPositions[row][col] = new MonsterSquare(row,col,monster);
            monster.setPos(row,col);
        }
    }

    public Monster putPlayer(Directions direction, Player player){
        //Obtener la fila y columna actuales del jugador
        int oldRow = player.getRow();
        int oldCol = player.getCol();

        //Calcular la nueva posición basándose en la dirección dada
        int[] newPos = dir2Pos(oldRow, oldCol, direction);

        //Llamar a putPlayer2D para mover al jugador y devolver un monstrup si lo hay
        return putPlayer2D(oldRow, oldCol, newPos[0], newPos[1], player);
    }

    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int row = startRow;
        int col = startCol;

        for(int i = 0; i < length; i++){
            //Comprobar si la posición actual es valida y está vacía
            if (posOK(row,col) && emptyPos(row,col)){
                squares[row][col].setContent((BLOCK_CHAR));
            }

            //Avanzar a la siguiente posición según la orientación
            if (orientation == Orientation.VERTICAL){
                row++; //Moverse hacia abajo en la misma columna
            } else {
                col++; //Moverse a la derecha en la misma fila
            }
        }
    }

    public Directions[] validMoves(int row, int col){
        ArrayList<Directions> validDirections = new ArrayList<>();

        //Verificar si se puede mover hacia abajo
        if (canStepOn(row +1, col)){
            validDirections.add(Directions.DOWN);
        }

        // ...hacia arriba
        if (canStepOn(row -1, col)){
            validDirections.add(Directions.UP);
        }

        // ...hacia la derecha
        if (canStepOn(row, col + 1)){
            validDirections.add(Directions.RIGHT);
        }

        // ...hacia la izquierda
        if (canStepOn(row, col - 1)){
            validDirections.add(Directions.LEFT);
        }

        //Convertir la lista de direcciones válidas a un array y devolverlo
        return validDirections.toArray(new Directions[0]);
    }

    private boolean posOK(int row, int col){
        return row >= 0 && row < nRows && col >= 0 && col < nCols;
    }

    private boolean emptyPos(int row, int col){
        return monsterPositions[row][col] == null && playerPositions[row][col] == null;
    }

    private boolean monsterPos(int row, int col){
        //Verificar si la posición está dentro del rango
        if(!posOK(row,col)){return false;}

        //Verificar si hay un monstruo y no un jugador
        return monsterPositions[row][col] != null && monsterPositions[row][col].hasMonster() &&
                (playerPositions[row][col] == null || !playerPositions[row][col].hasPlayer());
    }

    private boolean exitPos(int row, int col){
        return row == exitRow && col == exitCol;
    }

    private boolean combatPos (int row, int col){
        if (!posOK(row,col)){return false;}

        return monsterPositions[row][col] != null && monsterPositions[row][col].hasMonster() &&
                (playerPositions[row][col] != null && playerPositions[row][col].hasPlayer());
    }

    private boolean canStepOn(int row, int col){
        if (!posOK(row,col)){return false;}

        return emptyPos(row,col) || monsterPos(row,col) || exitPos(row,col);
    }

    private void updateOldPos(int row, int col){
        if (!posOK(row,col)){return;}

        if (combatPos(row,col)){
            playerPositions[row][col] = null;
        } else {
            playerPositions[row][col] = null;
            monsterPositions[row][col] =null;
        }

    }

    private int[] dir2Pos(int row, int col, Directions direction){
        switch (direction){
            case LEFT:
                return new int[] {row, col - 1};
            case RIGHT:
                return new int[] {row, col + 1};
            case UP:
                return new int[] {row - 1, col};
            case DOWN:
                return new int[] {row + 1, col};
            default:
                return new int[] {row, col};
        }
    }

    private int[] randomEmptyPos(){
        int row, col;

        do {
            //Generar una posición aleatoria dentro del rango
            row = Dice.randomPos(nRows);
            col = Dice.randomPos(nCols);
        } while (!emptyPos(row, col));

        return new int[] {row, col};
    }

    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        //Comprobar si el jugador puede moverse a la nueva posición
        if (!canStepOn(row, col)){
            return null;
        }

        //Verificar si la posición es válida y actualizarla si es necesario
        if (posOK(oldRow, oldCol)){
            Player p = get(oldRow, oldCol); //Obtener el jugador en la posición antigua
            if (p == player){
                updateOldPos(oldRow, oldCol); //Actualizar la posición antigua del jugador
            }
        }

        //Verificar si hay un monstruo en la nueva posición
        boolean monsterPos = monsterPos(row, col);
        Monster output = null;

        if (monsterPos){
            //Hay un monstruo, preparar para el combate
            set(row, col, COMBAT_CHAR); //Establecer el carácter de combate
            output = getMonster(row, col); //Obtener el monstruo para la interacción
        } else {
            //No hay monstruo, mover al jugador
            set(row, col, player.getNumber()); //Establecer el número del jugador
        }

        setPlayer(row, col, player);  //Colocar al jugador
        player.setPos(row, col);  //Actualizar la posición

        return output;
    }

    public Player get(int row, int col){
        if (!posOK(row,col)){
            return null;
        }

        PlayerSquare playerSquare = playerPositions[row][col];

        return (playerSquare != null) ? playerSquare.getPlayer() : null;
    }

    public Monster getMonster(int row, int col){
        if (!posOK(row, col)){
            return null;
        }

        MonsterSquare monsterSquare = monsterPositions[row][col];

        return (monsterSquare != null) ? monsterSquare.getMonster() : null;
    }

    public void set(int row, int col, char content){
        if (!posOK(row, col)){
            return;
        }

        squares[row][col].setContent(content);
    }

    public void setPlayer(int row, int col, Player player){
        if (!posOK(row, col)){
            return;
        }

        playerPositions[row][col] = new PlayerSquare(player);
    }

}
