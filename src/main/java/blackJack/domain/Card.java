package blackJack.domain;

public class Card {

    private final Symbol symbol;
    private final Denomination denomination;

    public Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

    public int getScore() {
        return denomination.getScore();
    }
}