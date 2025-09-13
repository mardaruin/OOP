package ru.syspro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    void testSetHand() {
        Player player = new HumanPlayer(null);
        Hand hand = new Hand();
        player.setHand(hand);
        assertSame(hand, player.getHand(), "Setting hand did not work");
    }

    @Test
    void testBlackjackRecognition() {
        Player player = new HumanPlayer(null);
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        player.setHand(hand);
        assertTrue(player.hasBlackjack(), "Blackjack recognition failed");
    }

    @Test
    void testBustedCondition() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.TEN));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        Player player = new HumanPlayer(null);
        player.setHand(hand);
        assertTrue(player.busted(), "Bust detection failed");
    }
}