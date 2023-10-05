public class TestP1 {
    public static void main(String[] args) {
        // Crear instancias de las clases Weapon y Shield
        Weapon weapon1 = new Weapon(2.0f, 5);
        Shield shield1 = new Shield(1.5f, 3);

        // Utilizar métodos de las clases Weapon y Shield
        System.out.println(weapon1.toString());
        System.out.println(shield1.toString());

        for (int i = 0; i < 100; i++) {
            Dice prueba = new Dice();
            System.out.println("Random pos: " + prueba.randomPos(8));
            System.out.println("Who starts: " + prueba.whoStarts(5));
            System.out.println("Random intelligence: " + prueba.randomIntelligence());
            System.out.println("Random strength: " + prueba.randomStrength());
            System.out.println("Resurrect player: " + prueba.resurrectPlayer());
            System.out.println("Weapons reward: " + prueba.weaponsReward());
            System.out.println("Shields reward: " + prueba.shieldsReward());
            System.out.println("Health reward: " + prueba.healthReward());
            System.out.println("Weapon power: " + prueba.weaponPower());
            System.out.println("Shield ower: " + prueba.shieldPower());
            System.out.println("Uses left: " + prueba.usesLeft());
            System.out.println("Intensity: " + prueba.intensity(9.8F));
            System.out.println("Discard element: " + prueba.discardElement(1));
        }

    }
}

