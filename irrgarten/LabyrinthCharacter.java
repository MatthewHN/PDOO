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
        this.intelligence = other.name;
        this.strength = other.strength;
        this.health = other.health;
        this.row = other.row;
        this.col = other.col;
    }

    public boolean dead(){}

    public int getRow(){}

    public int getCol(){}

    protected float getIntelligence(){}

    protected float getStrength(){}

    protected float getHealth(){}

    protected void setHealth(float health){}

    public void setPos(int row, int col){}

    public String toString(){}

    protected void gotWounded(){}

    public float attack(){}

    public boolean defend(float attack){}
}