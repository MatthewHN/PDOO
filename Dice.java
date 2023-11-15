import java.util.Random;

public class Dice {

    private static final Random generator = new Random();
    private static final int MAX_USES = 5;
    private static final float MAX_INTELLIGENCE = 10.0F;
    private static final float MAX_STRENGTH = 10.0F;
    private static final float RESURRECT_PROB = 0.3F;
    private static final int WEAPONS_REWARD = 2;
    private static final int SHIELDS_REWARD = 3;
    private static final int HEALTH_REWARD = 5;
    private static final int MAX_ATTACK = 3;
    private static final int MAX_SHIELD = 2;

    public static boolean discardElement(float usesLeft) {
        float probability = 1 - (usesLeft / MAX_USES);
        return generator.nextFloat() < probability;
    }

    public static float intensity(float competence){
        return generator.nextFloat(competence);
    }

    public static int randomPos(int max) {
        return generator.nextInt(max);
    }

    public static int whoStarts(int nplayers) {
        return generator.nextInt(nplayers);
    }

    public static float randomIntelligence() {
        return generator.nextFloat(MAX_INTELLIGENCE);
    }

    public static float randomStrength() {
        return generator.nextFloat(MAX_STRENGTH);
    }

    public static boolean resurrectPlayer() {
        return generator.nextFloat() <= RESURRECT_PROB;
    }

    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD);
    }

    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD);
    }

    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD);
    }

    public static float weaponPower(){
        return generator.nextFloat(MAX_ATTACK);
    }

    public static float shieldPower(){
        return generator.nextFloat(MAX_SHIELD);
    }

    public static int usesLeft(){
        return generator.nextInt(MAX_USES);
    }

}
