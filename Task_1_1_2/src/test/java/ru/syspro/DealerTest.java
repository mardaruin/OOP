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
        dealer.getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.SIX));
        dealer.getHand().addCard(new Card(Card.Suit.SPADES, Card.Rank.TEN));
        assertTrue(dealer.wantsAnotherCard(), "Dealer stopped below required limit");
    }

    @Test
    void testWantsAnotherCardOver17() {
        dealer.getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.NINE));
        dealer.getHand().addCard(new Card(Card.Suit.SPADES, Card.Rank.NINE));
        assertFalse(dealer.wantsAnotherCard(), "Dealer continued beyond allowed limit");
    }

    @Test
    void testReceiveCard() {
        dealer.receiveCard(new Card(Card.Suit.HEARTS, Card.Rank.KING));
        assertEquals(1, dealer.getHand().getCardsCopy().size(), "The size of hand must increase");
    }
    
    @Test
    void testClearCard() {
        dealer.receiveCard(new Card(Card.Suit.HEARTS, Card.Rank.KING));
        dealer.clearHand();
        assertEquals(0, dealer.getHand().getCardsCopy().size(), "Hand must be empty");
    }
}