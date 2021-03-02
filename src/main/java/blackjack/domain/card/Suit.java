package blackjack.domain.card;

public enum Suit {
    SPADE("스페이드"), CLUB("클로버"), DIAMOND("다이아몬드"), HEART("하트");

    private final String name;

    Suit(String name) {
        this.name = name;
    }
}
