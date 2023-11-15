public class Player {
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;


    public Player (char number, float intelligence, float strength){
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
    }

    public void resurrect(){

    }

    public int getRow(){

    }

    public int getCol(){

    }

    public char getNumber(){

    }

    public void setPos(){int row, int col}{
        this.row = row;
        this.col = col;

    }

    public boolean dead(){

    }

    public Directions move (Directions direction, Directions[] validMoves){

    }

    public float attack(){

    }

    public boolean defend (float receivedAttack){

    }

    public void receiveReward(){

    }

    public String toString(){

    }

    private void receiveWeapon(Weapon w){

    }

    private void receiveShield(Shield s){

    }

    private Weapon newWeapon(){

    }

    private Shield newShield(){

    }

    private float sumWeapons(){

    }

    private float sumShields(){

    }

    private float defensiveEnergy(){

    }

    private boolean manageHit(float receivedAttack){

    }

    private void resetHits(){

    }

    private void gotWounded(){

    }

    private void incConsecutiveHits(){

    }

}
