package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield> {

    private static final int MAX_CARDS = 10;

    public ShieldCardDeck(){
        super();
    }
    @Override
    protected void addCards() {
        for (int i=0; i<MAX_CARDS; i++){
            Shield escudo = new Shield(Dice.shieldPower(), Dice.usesLeft());
            addCard(escudo);
        }
    }
}
