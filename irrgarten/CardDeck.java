package irrgarten;

import java.util.ArrayList;
public class CardDeck<T> {

    private final ArrayList<T> cardDeck;

    public CardDeck(){
        this.cardDeck = new ArrayList<>();
    }

    protected void addCards(){}

    protected void addCard(T card){
        this.cardDeck.add(card);
    }

    public T nextCard(T ent){return ent;}

}