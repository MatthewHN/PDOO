package irrgarten;

public class CombatElement {

    private float effect;
    private int uses;

    public CombatElement(float effect, int uses) {
        this.effect = effect;
        this.uses = uses;
    }

    protected float produceEffect() {
        return 0;
    }

    public boolean discard() {
        return true;
    }

    public String toString() {return "S[" + effect + ", " + uses + "]";}

}