import java.util.ArrayList;

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

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;


    public Player(char number, float intelligence, float strength) {
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        weapons = new ArrayList<>(MAX_WEAPONS);
        shields = new ArrayList<>(MAX_SHIELDS);
        row = 0;
        col = 0;
        this.name = "Player # " + number;
    }

    public void resurrect() {
        this.weapons.clear(); // Hace que la lista de armas sea una lista vacía
        this.shields.clear(); // Hace que la lista de escudos sea una lista vacía
        this.health = INITIAL_HEALTH; // Restablece la salud al nivel inicial
        resetHits(); // Restablece el número de golpes consecutivos a cero
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public char getNumber() {
        return this.number;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;

    }

    public boolean dead() {
        return this.health <= 0;
    }

    public Directions move(Directions direction, Directions[] validMoves) {
        throw new UnsupportedOperationException();
    }

    public float attack() {
        // Sumar la fuerza del jugador y el total aportado por sus armas
        return this.strength + sumWeapons();
    }

    public boolean defend(float receivedAttack) {
        return manageHit(receivedAttack);
    }

    public void receiveReward() {
        throw new UnsupportedOperationException();
    }

    public String toString() {

        return "Player{name='" + name +
                "', number=" + number +
                ", intelligence=" + intelligence +
                ", strength=" + strength +
                ", health=" + health +
                ", row=" + row +
                ", col=" + col +
                ", consecutiveHits=" + consecutiveHits +
                ", Weapons=" + weapons.toString() +
                ", Shields=" + shields.toString() +
                "}";
    }

    private void receiveWeapon(Weapon w) {
        throw new UnsupportedOperationException();
    }

    private void receiveShield(Shield s) {
        throw new UnsupportedOperationException();
    }

    private Weapon newWeapon() {
        //Almacenamos el poder y el numero de uses del nuevo arma
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft();

        return new Weapon(power, uses);
    }

    private Shield newShield() {
        //Almacenamos la proteccion y el numero de uses del nuevo escudo
        float protection = Dice.shieldPower();
        int uses = Dice.usesLeft();

        return new Shield(protection, uses);
    }

    private float sumWeapons() {
        float sumWeapons = 0;

        // Iterar sobre la lista de armas y sumar el poder de cada arma
        for (Weapon weapon : weapons) {
            sumWeapons += weapon.attack();
        }
        return sumWeapons;
    }

    private float sumShields() {
        float sumShields = 0;

        // Iterar sobre la lista de armas y sumar el poder de cada arma
        for (Shield shield : shields) {
            sumShields += shield.protect();
        }
        return sumShields;
    }

    private float defensiveEnergy() {

        return intelligence + sumShields();
    }

    private boolean manageHit(float receivedAttack) {
        throw new UnsupportedOperationException();
    }

    private void resetHits() {
        consecutiveHits = 0;
    }

    private void gotWounded() {
        health--;
    }

    private void incConsecutiveHits() {
        consecutiveHits++;
    }

}
