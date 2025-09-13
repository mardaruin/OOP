package ru.syspro;

/**
 * Class that contains Card params.
 *
 */

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    public Rank getRank() {
        return rank;
    }

    public int value() {
        return rank.getValue();
    }

    public String toString() {
        return rank + "of" + suit;
    }
}

enum Suit { SPADES, HEARTS, DIAMONDS, CLUBS }

enum Rank {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}