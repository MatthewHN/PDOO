public class Shield {

    private final float protection;
    private int uses;

    public Shield(float protection, int uses) {
        this.protection = protection;
        this.uses = uses;
    }

    public float protect() {
        if (uses > 0) {
            uses--;
            return protection;
        } else
            return 0;
    }

    public String toString() {
        return "S[" + protection + ", " + uses + "]";
    }

    public boolean discard(Dice dice) {
        return dice.discardElement(uses);
    }

}
