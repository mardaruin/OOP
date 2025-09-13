package ru.syspro;

/**
 * The heir of the common player class, the dealer.
 *
 */

public class Dealer extends Player {
    @Override
    void takeTurn(Deck deck) {
        System.out.printf("Ход дилера\n" + "-------\n");
        while (hand.calculateScore() < 17) {
            Card drawnCard = deck.drawCard();
            hand.addCard(drawnCard);
            System.out.printf("Дилер открывает закрытую карту %s (%d)\n", drawnCard, drawnCard.value());
        }
    }

}