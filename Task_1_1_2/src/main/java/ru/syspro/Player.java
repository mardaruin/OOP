package ru.syspro;

/**
 * An abstract player class that serves as the parent for a specific player and dealer.
 *
 */

abstract class Player {
    protected Hand hand;

    abstract void takeTurn(Deck deck);

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
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