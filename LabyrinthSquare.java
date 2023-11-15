public class LabyrinthSquare {
    private int row;
    private int col;
    private char content; //????

    private boolean isBlocked;

    public LabyrinthSquare(int row, int col, char content){
        this.row=row;
        this.col=col;
        this.content=content;
    }

    public void setContent(char content){
        this.content=content;
    }

    public char getContent(){
        return content;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}
