package irrgarten;

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
        this.weapons = new ArrayList<>(MAX_WEAPONS);
        this.shields = new ArrayList<>(MAX_SHIELDS);
        this.row = 1;
        this.col = 1;
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

    public String getName() {
        return this.name;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;

    }

    public boolean dead() {
        return this.health <= 0;
    }

    public Directions move(Directions direction, Directions[] validMoves) {
        if(validMoves.length > 0 && !contains(validMoves, direction)){
            return validMoves[0];
        } else {
            return direction;
        }
    }

    //metodo auxiliar para ver si un array contiene un elemento específico
    private boolean contains(Directions[] directions, Directions direction){
        for (Directions d : directions){
            if (d==direction){
                return true;
            }
        }
        return false;
    }

    public float attack() {
        // Sumar la fuerza del jugador y el total aportado por sus armas
        return this.strength + sumWeapons();
    }

    public boolean defend(float receivedAttack) {
        return manageHit(receivedAttack);
    }

    public void receiveReward() {
        int wReward = Dice.weaponsReward();
        for (int i=0; i < wReward; i++){
            Weapon wnew = newWeapon();
            receiveWeapon(wnew);
        }

        int sReward = Dice.shieldsReward();
        for (int i=0; i < sReward; i++){
            Shield snew = newShield();
            receiveShield(snew);
        }

        int extraHealth = Dice.healthReward();
        this.health += extraHealth;
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
                "}\n";
    }

    private void receiveWeapon(Weapon w) {
        for (int i = weapons.size() - 1; i>=0; i--){
            Weapon wi = weapons.get(i);
            if (wi.discard()){
                weapons.remove(wi);
            }
        }

        if (weapons.size() < MAX_WEAPONS){
            weapons.add(w);
        }
    }

    private void receiveShield(Shield s) {
        //Iterar sobre la lista de escudos y descartar los que no se necesiten
        for (int i = shields.size() - 1; i >= 0; i--){
            Shield si = shields.get(i);
            if (si.discard()){
                shields.remove(si);
            }
        }

        if (shields.size() < MAX_SHIELDS){
            shields.add(s);
        }
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

    protected float sumWeapons() {
        float sumWeapons = 0;

        // Iterar sobre la lista de armas y sumar el poder de cada arma
        for (Weapon weapon : weapons) {
            sumWeapons += weapon.attack();
        }
        return sumWeapons;
    }

    protected float sumShields() {
        float sumShields = 0;

        // Iterar sobre la lista de armas y sumar el poder de cada arma
        for (Shield shield : shields) {
            sumShields += shield.protect();
        }
        return sumShields;
    }

    private float defensiveEnergy() {

        return this.intelligence + sumShields();
    }

    private boolean manageHit(float receivedAttack) {
        float defense = defensiveEnergy();

        if (defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();

            if (this.consecutiveHits == HITS2LOSE || dead()){
                resetHits();
                return true; //Indica que el jugador ha perdido o muerto
            }
        } else {
            resetHits();
        }

        return false; //Indica que el jugador sigue en el juego
    }

    private void resetHits() {
        this.consecutiveHits = 0;
    }

    private void gotWounded() {
        this.health--;
    }

    private void incConsecutiveHits() {
        this.consecutiveHits++;
    }


}
