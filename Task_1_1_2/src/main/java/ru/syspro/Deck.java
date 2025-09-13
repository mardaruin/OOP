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

    public List<Card> getCardsCopy() {
        return new ArrayList<>(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card peekTopCard() {
        return cards.get(0);
    }

    private void createDeck() {
        cards = new ArrayList<>();
        for (Suit suit: Suit.values()) {
            for (Rank rank: Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        throw new IllegalStateException("Колода пуста");
    }
}