import java.util.ArrayList;

public class Labyrinth {
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;

    private LabyrinthSquare[][] squares;
    private PlayerSquare[][] playerPositions;
    private MonsterSquare[][] monsterPositions;

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

    public void spreadPlayers(ArrayList<Player> players){

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
                    sb.append('P'); //Player
                } else if (playerPositions[i][j] != null && monsterPositions[i][j].hasMonster()) {
                    sb.append('M'); //Monstruo
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

    }

    public void addBlock(Orientation orientation, int startRow, int startCol, int length){

    }

    public ArrayList<Directions> validMoves(int row, int col){

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

    }

    private boolean combatPos (int row, int col){

    }

    private boolean canStep(int row, int col){

    }

    private void updateOldPos(int row, int col){

    }

    private int[] dir2Pos(int row, int col, Directions direction){

    }

    private int[] randomEmptyPos(){

    }

    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){

    }
}
