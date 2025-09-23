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

    public int playerWin = 0;
    public int dealerWin = 0;

    protected Dealer getDealer() {
        return dealer;
    }

    protected HumanPlayer getPlayer() {
        return player;
    }

    protected void setScanner(Scanner newScanner) {
        scanner = newScanner;
    }

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
     * Constructor accepting predefined deck and scanner.
     * For testing purposes.
     * 
     */
    public BlackJackGame(Scanner customScanner, Deck customDeck) {
        scanner = customScanner;
        dealer = new Dealer();
        player = new HumanPlayer();
        deck = customDeck;
    }

    /**
     * Starts the game.
     *
     */
    public void play() {
        int round = 0;
        System.out.printf("Добро пожаловать в Блэкджек!\n");
        System.out.print("Введите желаемое количество раундов: ");
        String input = scanner.nextLine().trim();
        try {
            round = Integer.parseInt(input);
            System.out.println("Вы выбрали " + round + " раундов.");
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Вы ввели некорректное число!");
        }
        for (int i = 1; i <= round; i++) {
            System.out.println("Раунд " + i);
            resetHands();
            dealInitialCards();
            checkForBlackjacks();
            if (!player.hasBlackjack() && !dealer.hasBlackjack()) {
                handlePlayerTurn();
                if (!player.busted()) {
                    handleDealerTurn();
                }
            }
            determineWinner();
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
    private void dealInitialCards() {
        System.out.println("Дилер раздал карты");
        player.receiveCard(deck.drawCard());
        player.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        printCurrentStatus(false);
    }

    protected void setDealInitialCards() {
        dealInitialCards();
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
                player.receiveCard(drawnCard);
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
     */
    public void determineWinner() {
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
            Card firstDealerCard = dealer.getHand().getCardsCopy().get(0);
            firstDealerCard.reveal();
            System.out.println("Карточки дилера: [" + firstDealerCard + ", <закрытая карта>]");
        }
    }
}