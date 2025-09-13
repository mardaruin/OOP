package ru.syspro;

import java.util.Scanner;

/**
 * The heir of the common player class, the HumanPlayer.
 *
 */

public class HumanPlayer extends Player {
    private Scanner scanner;

    public HumanPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    void takeTurn(Deck deck) {
        System.out.printf("Ваш ход\n" + "-------\n");
        while (true) {
            System.out.printf("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 1) {
                Card drawnCard = deck.drawCard();
                hand.addCard(drawnCard);
                System.out.printf("Вы открыли карту %s (%d)\n", drawnCard, drawnCard.value());
                if (busted()) {
                    break;
                }
            } else {
                break;
            }
        }
    }
}