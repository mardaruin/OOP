package ru.syspro;

import java.util.Scanner;

/**
 * The main class that manages the game.
 *
 */

public class BlackJackGame {
    public Dealer dealer;
    public HumanPlayer player;
    public Deck deck;
    public Scanner scanner;

    public int playerWin = 0;
    public int dealerWin = 0;

    /**
     * Method that creates initial deck and players.
     *
     */
    public BlackJackGame() {
        scanner = new Scanner(System.in);
        dealer = new Dealer();
        player = new HumanPlayer();
        deck = new Deck();
    }

    /**
     * Starts the game.
     *
     */
    public void play() {
        System.out.printf("Добро пожаловать в Блэкджек!\n");
        for (int round = 1; round <= 2; round++) {
            System.out.println("Раунд " + round);
            resetHands();
            dealInitialCards();
            checkForBlackjacks();
            if (!player.hasBlackjack() && !dealer.hasBlackjack()) {
                handlePlayerTurn();
                if (!player.busted()) {
                    handleDealerTurn();
                }
            }
            determineWinner(dealerWin, playerWin);
        }
        scanner.close();
    }

    /**
     * Resets dealer and player's hands before the game.
     *
     */
    public void resetHands() {
        player.clearHand();
        dealer.clearHand();
    }

    /**
     * Deals out the initial cards.
     *
     */
    public void dealInitialCards() {
        System.out.println("Дилер раздал карты");
        player.receiveCard(deck.drawCard());
        player.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        printCurrentStatus(false);
    }

    /**
     * Checks for BlackJacks.
     *
     */
    public void checkForBlackjacks() {
        if (player.hasBlackjack()) {
            System.out.println("У вас блэкджек! Вы победили.");
        } else if (dealer.hasBlackjack()) {
            System.out.println("У дилера блэкджек! Вы проиграли.");
        }
    }

    /**
     * Processes the player's move.
     *
     */
    public void handlePlayerTurn() {
        System.out.printf("Ваш ход\n" + "-------\n");
        while (true) {
            System.out.printf("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 1) {
                Card drawnCard = deck.drawCard();
                player.getHand().addCard(drawnCard);
                System.out.printf("Вы открыли карту %s (%d)\n", drawnCard, drawnCard.value());
                printCurrentStatus(false);
                if (player.busted()) {
                    break;
                }
            } else {
                break;
            }
        }
    }

    /**
     * Processes the dealer's move.
     *
     */
    public void handleDealerTurn() {
        System.out.printf("Ход дилера\n" + "-------\n");
        while (dealer.wantsAnotherCard()) {
            Card drawnCard = deck.drawCard();
            dealer.getHand().addCard(drawnCard);
            System.out.printf("Дилер открывает закрытую карту %s (%d)\n",
                    drawnCard, drawnCard.value());
            printCurrentStatus(true);
        }
    }

    /**
     * Determines the winner of the round.
     *
     * @param previousDealerWins amount of dealer wins before.
     * @param previousPlayerWins amount of player wins before.
     */
    public void determineWinner(int previousDealerWins, int previousPlayerWins) {
        int currentDealerWins = previousDealerWins;
        int currentPlayerWins = previousPlayerWins;
        if (player.busted()) {
            currentDealerWins += 1;
            System.out.printf("Вы перебрали! Проигрыш.\nСчёт "
                    + currentPlayerWins + ":" + currentDealerWins);
        } else if (dealer.busted()) {
            currentPlayerWins += 1;
            System.out.printf("Дилер перебрал! Победа! \nСчёт "
                    + currentPlayerWins + ":" + currentDealerWins);
        } else if (player.getScore() < dealer.getScore()) {
            currentDealerWins += 1;
            System.out.printf("Вы набрали меньше дилера! Проигрыш.\nСчёт "
                    + currentPlayerWins + ":" + currentDealerWins);
        } else if (player.getScore() > dealer.getScore()) {
            currentPlayerWins += 1;
            System.out.printf("Вы набрали больше дилера! Победа!\nСчёт "
                    + currentPlayerWins + ":" + currentDealerWins);
        } else {
            System.out.printf("Ничья! Счёт ");
        }
        if (currentPlayerWins > currentDealerWins) {
            System.out.printf(" в вашу пользу.");
        } else {
            System.out.printf(" в пользу дилера");
        }
        this.dealerWin = currentDealerWins;
        this.playerWin = currentPlayerWins;
    }

    /**
     * Prints current status of the game.
     *
     * @param showAllDealerCards True when needs to print
     *                           second dealer's card, false otherwise
     */
    private void printCurrentStatus(boolean showAllDealerCards) {
        System.out.println("Ваши карты: [" + player.getHand() + "] => " + player.getScore());
        if (showAllDealerCards) {
            System.out.println("Карточки дилера: [" + dealer.getHand() +
                    "] => " + dealer.getScore());
        } else {
            System.out.println("Карточки дилера: [" +
                    dealer.getHand().getCard().get(0).toString() + ", <закрытая карта>]");
        }
    }
}