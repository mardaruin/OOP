package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HumanPlayerTest {

    private HumanPlayer player;
    private Deck deck;

    @BeforeEach
    void setup() {
        player = new HumanPlayer();
        deck = new Deck();
    }

    @Test
    void testWantsAnotherCard() {
        assertFalse(player.wantsAnotherCard(), "Dealer continued beyond allowed limit");
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