public class PlayerSquare {
    private int row;
    private int col;
    private Labyrinth players[];

    private Player player;

    public PlayerSquare(Player player){
        this.player = player;
    }

    public boolean hasPlayer(){
        return player != null;
    }

    public Player getPlayer(){
        return player;
    }
}
