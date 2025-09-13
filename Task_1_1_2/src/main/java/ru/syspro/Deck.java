package ru.syspro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class describes deck of cards.
 *
 */

public class Deck {
    private List<Card> cards;

    public Deck() {
        createDeck();
        shuffle();
    }

    private void createDeck() {
        cards = new ArrayList<>();
        for (Suit suit: Suit.values()) {
            for (Rank rank: Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        throw new IllegalStateException("Колода пуста");
    }
}