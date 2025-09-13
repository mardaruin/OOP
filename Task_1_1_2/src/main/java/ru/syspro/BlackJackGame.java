package ru.syspro;

import java.util.Scanner;

/**
 * The main class that manages the game.
 *
 */

public class BlackJackGame {
    private Dealer dealer;
    private HumanPlayer player;
    private Deck deck;
    private Scanner scanner;

    /**
     * Method that creates initial deck and players.
     *
     */
    public BlackJackGame() {
        scanner = new Scanner(System.in);
        dealer = new Dealer();
        player = new HumanPlayer(scanner);
        deck = new Deck();
    }

    /**
     * The main method that manages the game.
     *
     */
    public void play() {
        System.out.printf("Добро пожаловать в Блэкджек!\n");
        int playerWin = 0;
        int dealerWin = 0;
        for (int round = 1; round <= 2; round++) {
            System.out.println("Раунд " + round);
            dealInitialCards();
            checkForBlackjacks();
            if (!player.hasBlackjack() && !dealer.hasBlackjack()) {
                player.takeTurn(deck);
                if (!player.busted()) {
                    dealer.takeTurn(deck);
                }
            }
            determineWinner(dealerWin, playerWin);
        }
        scanner.close();
    }

    private void dealInitialCards() {
        System.out.println("Дилер раздал карты");
        player.setHand(new Hand());
        dealer.setHand(new Hand());
        player.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        printCurrentStatus(false);
    }

    private void checkForBlackjacks() {
        if (player.hasBlackjack()) {
            System.out.println("У вас блэкджек! Вы победили.");
        } else if (dealer.hasBlackjack()) {
            System.out.println("У дилера блэкджек! Вы проиграли.");
        }
    }

    private void determineWinner(int dealerWin, int playerWin) {
        if (player.busted()) {
            dealerWin += 1;
            System.out.printf("Вы перебрали! Проигрыш.\nСчёт "
                    + playerWin + ":" + dealerWin);
        } else if (dealer.busted()) {
            playerWin += 1;
            System.out.printf("Дилер перебрал! Победа! \nСчёт "
                    + playerWin + ":" + dealerWin);
        } else if (player.getScore() < dealer.getScore()) {
            dealerWin += 1;
            System.out.printf("Вы набрали меньше дилера! Проигрыш.\nСчёт "
                    + playerWin + ":" + dealerWin);
        } else if (player.getScore() > dealer.getScore()) {
            playerWin += 1;
            System.out.printf("Вы набрали больше дилера! Победа!\nСчёт "
                    + playerWin + ":" + dealerWin);
        } else {
            System.out.printf("Ничья! Счёт ");
        }
        if (playerWin > dealerWin) {
            System.out.printf(" в вашу пользу.");
        } else {
            System.out.printf(" в пользу дилера");
        }
    }

    private void printCurrentStatus(boolean showAllDealerCards) {
        System.out.println("Ваши карты: [" + player.getHand() + "] => " + player.getScore());
        if (showAllDealerCards) {
            System.out.println("Карточки дилера: [" + dealer.getHand() + "] => " + dealer.getScore());
        } else {
            System.out.println("Карточки дилера: [" + dealer.getHand().getCard().get(0).toString() + ", <закрытая карта>]");
        }
    }
}