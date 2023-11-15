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

    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){

    }

    public void spreadPlayers(Player[] players){

    }

    public boolean haveAWinner(){

    }

    public String toString(){

    }

    public void addMonster(int row, int col, Monster monster){

    }

    public Monster putPlayer(Directions direction, Player player){

    }

    public void addBlock(Orientation orientation, int startRow, int startCol, int length){

    }

    public Directions[] validMoves(int row, int col){

    }

    private boolean posOK(int row, int col){

    }

    private boolean emptyPos(int row, int col){

    }

    private boolean monsterPos(int row, int col){

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