package irrgarten;

import java.util.ArrayList;

public class Player extends LabyrinthCharacter {
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    private char number;
    private int row;
    private int col;
    private int consecutiveHits = 0;

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;


   /* public Player(char number, float intelligence, float strength) {
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.weapons = new ArrayList<>(MAX_WEAPONS);
        this.shields = new ArrayList<>(MAX_SHIELDS);
        this.row = 1;
        this.col = 1;
        this.name = "Player # " + number;
    }*/

    public Player(char number,float intelligence, float strength) {
        super("Player: " + number,intelligence,strength,INITIAL_HEALTH);
        this.number = number;
        this.weapons = new ArrayList<>(MAX_WEAPONS);
        this.shields = new ArrayList<>(MAX_SHIELDS);
    }

    public Player(Player other) {
        super("Player: " + other.number, other.getStrength(), other.getHealth());
    }

    public void copyFrom(Player other){
        super.copyFrom(other);
    }

    public void resurrect() {
        this.weapons.clear(); // Hace que la lista de armas sea una lista vacía
        this.shields.clear(); // Hace que la lista de escudos sea una lista vacía
        this.setHealth(INITIAL_HEALTH); // Restablece la salud al nivel inicial
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
        return "Player: " + number;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean dead() {
        return this.getHealth() <= 0;
    }

    public Directions move(Directions direction, Directions[] validMoves) {
        if(validMoves.length > 0 && !contains(validMoves, direction)){
            return validMoves[0];
        } else {
            return direction;
        }
    }

    //metodo auxiliar para ver si un array contiene un elemento específico
    public boolean contains(Directions[] directions, Directions direction){
        for (Directions d : directions){
            if (d==direction){
                return true;
            }
        }
        return false;
    }

    public float attack() {
        // Sumar la fuerza del jugador y el total aportado por sus armas
        return this.getStrength() + sumWeapons();
    }

    public boolean defend(float receivedAttack) {
        return manageHit(receivedAttack);
    }

    public void receiveReward() {
        int wReward = Dice.weaponsReward();
        WeaponCardDeck mazo = new WeaponCardDeck();
        for (int i=0; i < wReward; i++){
            mazo.addCard(newWeapon());
            receiveWeapon(mazo.nextCard());
        }

        int sReward = Dice.shieldsReward();
        ShieldCardDeck mazoes = new ShieldCardDeck();
        for (int i=0; i < sReward; i++){
            mazoes.addCard(newShield());
            receiveShield(mazoes.nextCard());
        }

        int extraHealth = Dice.healthReward();
        this.health += extraHealth;
    }

    public String toString() {

        return "Player{name='" + number +
                ", intelligence=" + getIntelligence() +
                ", strength=" + getStrength() +
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

        return this.getIntelligence() + sumShields();
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

    public void gotWounded() {
        this.health--;
    }

    private void incConsecutiveHits() {
        this.consecutiveHits++;
    }

}
