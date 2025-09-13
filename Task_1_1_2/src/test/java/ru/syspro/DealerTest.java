package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealerTest {

    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    void setup() {
        dealer = new Dealer();
        deck = new Deck();
        dealer.setHand(new Hand());
    }

    @Test
    void testTakeTurnBelow17() {
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.TEN));
        dealer.takeTurn(deck);
        assertEquals(17, dealer.getScore(), "Dealer stopped below required limit");
    }

    @Test
    void testTakeTurnAbove17() {
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.KING));
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.NINE));
        dealer.takeTurn(deck);
        assertTrue(dealer.getScore() >= 17, "Dealer continued beyond allowed limit");
    }
}