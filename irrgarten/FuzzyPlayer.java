package irrgarten;

public class FuzzyPlayer extends Player {

    public FuzzyPlayer(Player other) {
        super(other); // Constructor copia de Player
    }

    @Override
    public Directions move(Directions direction, Directions[] validMoves) {
        if(validMoves.length > 0 && !contains(validMoves, direction)){
            return validMoves[0];
        } else {
            return direction;
        }
    }

    @Override
    public float attack() {
        // Sumar la fuerza del jugador y el total aportado por sus armas
        return this.getStrength() + sumWeapons();
    }

    private float defensiveEnergy() {
        return this.getIntelligence() + sumShields();
    }

    @Override
    public String toString() {
        return "Fuzzy" + super.toString(); // Preponiendo "Fuzzy" a la representación de Player
    }
}
