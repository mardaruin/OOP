package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DealerTest {

    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    void setup() {
        dealer = new Dealer();
        deck = new Deck();
    }

    @Test
    void testWantsAnotherCardWhenLess17() {
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.SIX));
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.TEN));
        assertTrue(dealer.wantsAnotherCard(), "Dealer stopped below required limit");
    }

    @Test
    void testWantsAnotherCardOver17() {
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.NINE));
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.NINE));
        assertFalse(dealer.wantsAnotherCard(), "Dealer continued beyond allowed limit");
    }

    @Test
    void testReceiveCard() {
        dealer.receiveCard(new Card(Suit.HEARTS, Rank.KING));
        assertEquals(1, dealer.getHand().getCard().size(), "The size of hand must increase");
    }
    
    @Test
    void testClearCard() {
        dealer.receiveCard(new Card(Suit.HEARTS, Rank.KING));
        dealer.clearHand();
        assertEquals(0, dealer.getHand().getCard().size(), "Hand must be empty");
    }
}