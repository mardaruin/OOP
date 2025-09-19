package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setup() {
        player = new HumanPlayer();
    }

    @Test
    void testGetScore() {
        player.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.getHand().addCard(new Card(Suit.SPADES, Rank.TEN));
        assertEquals(21, player.getScore(), "Score must be 21");
    }

    @Test
    void testBlackjackRecognition() {
        player.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.getHand().addCard(new Card(Suit.SPADES, Rank.KING));
        assertTrue(player.hasBlackjack(), "Player must have BlackJack");
    }

    @Test
    void testBustedCondition() {
        player.getHand().addCard(new Card(Suit.HEARTS, Rank.KING));
        player.getHand().addCard(new Card(Suit.SPADES, Rank.TEN));
        player.getHand().addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        assertTrue(player.busted(), "Player failed");
    }

    @Test
    void testReceiveCard() {
        player.receiveCard(new Card(Suit.HEARTS, Rank.KING));
        assertEquals(1, player.getHand().getCard().size(), "The size of hand must increase");
    }

    @Test
    void testClearCard() {
        player.receiveCard(new Card(Suit.HEARTS, Rank.KING));
        player.clearHand();
        assertEquals(0, player.getHand().getCard().size(), "Hand must be empty");
    }
}