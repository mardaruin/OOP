package ru.syspro;

/**
 * An abstract player class that serves as the parent for a specific player and dealer.
 *
 */

public abstract class Player {
    protected Hand hand;

    public Player() {
        this.hand = new Hand();
    }

    public abstract boolean wantsAnotherCard();

    public Hand getHand() {
        return hand;
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public void clearHand() {
        hand = new Hand();
    }

    public boolean hasBlackjack() {
        return hand.isBlackjack();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean busted() {
        return getScore() > 21;
    }
}