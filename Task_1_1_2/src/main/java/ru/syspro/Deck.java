package ru.syspro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class describes deck of cards.
 *
 */

public class Deck {
    public List<Card> cards;

    public Deck() {
        createDeck();
        shuffle();
    }

    public Deck(List<Card> newDeck) {
        cards = newDeck;
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
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Method that draws a top card from deck.
     * Otherwise, throws an {@link IllegalStateException} indicating that the deck is empty.
     *
     * @return top deck card.
     * @throws IllegalStateException if there are no more cards left in the deck
     */
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        throw new IllegalStateException("Колода пуста");
    }
}