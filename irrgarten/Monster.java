package irrgarten;

public class Monster extends LabyrinthCharacter{
    private final static int INITIAL_HEALTH = 5;
    private final String name;
    private final float intelligence;
    private final float strength;
    private float health;
    private int row;
    private int col;

    /*public Monster(String name, float intelligence, float strength) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        health = INITIAL_HEALTH;
    }*/

    public Monster(String name, float intelligence, float strength, float health, String name1, float intelligence1, float strength1) {
        super(name, intelligence, strength, health);
        this.name = name1;
        this.intelligence = intelligence1;
        this.strength = strength1;
    }

    public boolean dead() {
        return this.health <= 0;
    }

    public float attack() {
        return Dice.intensity(this.strength);
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "Name: " + this.name + " Intelligence: " + this.intelligence + " Strentgh: " + this.strength + " Health: " + this.health;
    }

    public void gotWounded() {
        this.health--;
    }

    public boolean defend(float receivedAttack) {

        float defensiveEnergy = Dice.intensity(intelligence);
        if (defensiveEnergy < receivedAttack){
            gotWounded();
        }

        return dead();
    }
}
