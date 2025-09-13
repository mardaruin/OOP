package ru.syspro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {

    @Test
    void testAddingCards() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        assertEquals(1, hand.getCard().size(), "Hand should have one card");
    }

    @Test
    void testBlackjackCondition() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        assertTrue(hand.isBlackjack(), "Should recognize Blackjack condition");
    }

    @Test
    void testScoreCalculation() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.THREE));
        assertEquals(10, hand.calculateScore(), "Incorrect score calculation");
    }

    @Test
    void testAceAdjustment() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.TEN));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        assertEquals(21, hand.calculateScore(), "Ace adjustment failed");
    }
}