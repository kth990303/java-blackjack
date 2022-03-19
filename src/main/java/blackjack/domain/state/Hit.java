package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit implements Status {

    private final Cards cards;

    public Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Status draw(Card card) {
        cards.receiveCard(card);
        if (cards.isBust()) {
            return new Bust();
        }
        return new Hit(cards);
    }

    public Cards getCards() {
        return cards;
    }
}