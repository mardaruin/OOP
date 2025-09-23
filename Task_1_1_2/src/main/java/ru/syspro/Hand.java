package ru.syspro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class describes cards in players hand.
 *
 */

public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && calculateScore() == 21;
    }

    /**
     * Method that counts score in hand.
     *
     * @return score in hand.
     */
    public int calculateScore() {
        int score = 0;
        int countAces = 0;
        for (Card card : cards) {
            score += card.value();
            if (card.getRank() == Card.Rank.ACE) {
                countAces += 1;
            }
        }
        while (score > 21 && countAces > 0) {
            score -= 10;
            countAces -= 1;
        }
        return score;
    }

    /**
     * Returns an unmodifiable copy of the list of cards in the hand.
     * It prevents external modification of the original list by returning a defensive copy.
     *
     * @return An immutable list containing all cards in the hand.
     */
    public List<Card> getCardsCopy() {
        return new ArrayList<>(this.cards);
    }


    @Override
    public String toString() {
        return cards.stream().map(Card::toString).collect(Collectors.joining(", "));
    }
}