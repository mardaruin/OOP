package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumanPlayerTest {

    private HumanPlayer player;
    private Deck deck;

    @BeforeEach
    void setup() {
        player = new HumanPlayer(null); // Сканнер тут не важен, используем mock позже
        deck = new Deck();
    }

    @Test
    void testSetHand() {
        Hand hand = new Hand();
        player.setHand(hand);
        assertSame(hand, player.getHand(), "Hand setting did not work");
    }

    @Test
    void testBlackjackDetection() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        player.setHand(hand);
        assertTrue(player.hasBlackjack(), "Blackjack was not detected");
    }

    @Test
    void testBustedCondition() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.TEN));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        player.setHand(hand);
        assertTrue(player.busted(), "Bust condition not recognized");
    }
}