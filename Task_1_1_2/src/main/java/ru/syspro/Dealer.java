package ru.syspro;

/**
 * The heir of the common player class, the dealer.
 *
 */

public class Dealer extends Player {

    @Override
    public boolean wantsAnotherCard() {
        return getScore() < 17;
    }

}