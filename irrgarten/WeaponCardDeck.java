package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon> {

    private static final int MAX_CARDS = 10;

    public WeaponCardDeck(){
        super();
    }
    @Override
    protected void addCards() {
        for (int i=0; i<MAX_CARDS; i++){
            Weapon arma = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            addCard(arma);
        }
    }
}
