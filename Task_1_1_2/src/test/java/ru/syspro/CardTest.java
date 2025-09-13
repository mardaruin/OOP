package ru.syspro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @Test
    void testCardValues() {
        Card card = new Card(Suit.SPADES, Rank.ACE);
        assertEquals(11, card.value(), "Ace of Spades should give 11 points");
    }

    @Test
    void testCardRepresentation() {
        Card card = new Card(Suit.HEARTS, Rank.TEN);
        assertEquals("TEN of HEARTS", card.toString(), "Expected correct string representation");
    }

    @Test
    void testRankEnumValues() {
        assertEquals(10, Rank.KING.getValue(), "King should give 10 points");
        assertEquals(11, Rank.ACE.getValue(), "Ace should give 11 points initially");
    }
}