package ru.syspro;

import java.util.Scanner;

/**
 * The heir of the common player class, the HumanPlayer.
 *
 */

public class HumanPlayer extends Player {

    @Override
    public boolean wantsAnotherCard() {
        return false;
    }
}