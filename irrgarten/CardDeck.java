package irrgarten;

import java.util.ArrayList;
import java.util.Collections;
public class CardDeck<T> {
    private ArrayList<T> cardDeck;

    public CardDeck() {
        cardDeck = new ArrayList<>();
        addCards(); // Inicializa el mazo con cartas
    }

    protected void addCard(T card) {
        cardDeck.add(card);
    }

    // Método para ser sobrescrito en subclases, añade cartas al mazo
    protected void addCards() {
        // Implementación específica en subclases
    }

    public T nextCard() {
        if (cardDeck.isEmpty()) {
            addCards();
            Collections.shuffle(cardDeck);
        }
        return cardDeck.remove(0); // Devuelve y elimina la primera carta del mazo
    }
}