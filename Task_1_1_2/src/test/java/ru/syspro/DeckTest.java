package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setup() {
        deck = new Deck();
    }

    @Test
    void testDeckContains52Cards() {
        assertEquals(52, deck.size(), "Новая колода должна содержать 52 карты");
    }

    @Test
    void testShuffleChangesOrder() {
        List<Card> originalDeck = new ArrayList<>(deck.getCardsCopy());
        deck.shuffle();
        assertNotEquals(originalDeck, deck.getCardsCopy(), "Порядок карт после перемешивания должен отличаться");
    }

    @Test
    void testDrawFirstCard() {
        Card expectedTopCard = deck.peekTopCard();
        Card actualTopCard = deck.drawCard();
        assertEquals(expectedTopCard, actualTopCard, "Первая карта должна совпадать с той, что была на вершине колоды");
    }

    @Test
    void testEmptyDeckThrowsException() {
        for (int i = 0; i < 52; i++) {
            deck.drawCard(); // Потратим все карты
        }
        assertThrows(IllegalStateException.class, () -> deck.drawCard(), "Опустошённая колода должна выбросить исключение");
    }

    @Test
    void testDeckContainsAllTypesOfCards() {
        Set<Card> uniqueCards = new HashSet<>(deck.getCardsCopy());
        assertEquals(52, uniqueCards.size(), "Каждая карта должна быть уникальной");
    }

}