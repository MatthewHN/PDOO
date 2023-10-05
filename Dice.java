import java.util.Random;

public class Dice {

    private final int MAX_USES = 5;
    private final float MAX_INTELLIGENCE = 10.0F;
    private final float MAX_STRENGTH = 10.0F;
    private final float RESURRECT_PROB = 0.3F;
    private final int WEAPONS_REWARD = 2;
    private final int SHIELDS_REWARD = 3;
    private final int HEALTH_REWARD = 5;
    private final int MAX_ATTACK = 3;
    private final int MAX_SHIELD = 2;
    private final Random generator = new Random();

    public int randomPos(int max) {
        return generator.nextInt(max);
    }

    public int whoStarts(int nplayers) {
        return generator.nextInt(nplayers);
    }

    public float randomIntelligence() {
        return generator.nextFloat(MAX_INTELLIGENCE);
    }

    public float randomStrength() {
        return generator.nextFloat(MAX_STRENGTH);
    }

    public boolean resurrectPlayer() {
        return generator.nextFloat() <= RESURRECT_PROB;
    }

    public int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD);
    }

    public int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD);
    }

    public int healthReward(){
        return generator.nextInt(HEALTH_REWARD);
    }

    public float weaponPower(){
        return generator.nextFloat(MAX_ATTACK);
    }

    public float shieldPower(){
        return generator.nextFloat(MAX_SHIELD);
    }

    public int usesLeft(){
        return generator.nextInt(MAX_USES);
    }

    public float intensity(float competence){
        return generator.nextFloat(competence);
    }

    public boolean discardElement(float usesLeft) {
        float probability = 1 - (usesLeft / MAX_USES);
        return generator.nextFloat() < probability;
    }
}
