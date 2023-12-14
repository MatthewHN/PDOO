package irrgarten;

public class LabyrinthCharacter{

    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;

    public LabyrinthCharacter(String name, float intelligence, float strength, float health){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        this.row = row;
        this.col = col;
    }

    public LabyrinthCharacter(LabyrinthCharacter other){
        this.name = other.name;
        this.intelligence = other.intelligence;
        this.strength = other.strength;
        this.health = other.health;
        this.row = other.row;
        this.col = other.col;
    }

    public boolean dead(){return true;}

    public int getRow(){return 1;}

    public int getCol(){return 1;}

    protected float getIntelligence(){return 1;}

    protected float getStrength(){return 1;}

    protected float getHealth(){return 1;}

    protected void setHealth(float health){}

    public void setPos(int row, int col){}

    public String toString(String ent){return ent;}

    protected void gotWounded(){}

    public float attack(){return 0;}

    public boolean defend(float attack){return true;}
}