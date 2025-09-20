package ru.syspro;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlackJackGameTest {

    private BlackJackGame game;
    private Scanner scanner;

    @BeforeEach
    void setup() {
        scanner = new Scanner("");
        game = new BlackJackGame();
    }

    @Test
    void testInitialDeal() {
        game.resetHands();
        game.dealInitialCards();
        assertEquals(2, game.player.getHand().getCard().size(), "Player must have 2 cards");
        assertEquals(2, game.dealer.getHand().getCard().size(), "Dealer must have 2 cards");
    }

    @Test
    void testCalculateScores() {
        game.resetHands();

        game.player.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        game.player.getHand().addCard(new Card(Suit.SPADES, Rank.TEN));
        assertEquals(21, game.player.getScore(), "Score must be 21");

        game.dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.EIGHT));
        game.dealer.getHand().addCard(new Card(Suit.DIAMONDS, Rank.SEVEN));
        assertEquals(15, game.dealer.getScore(), "Score must be 15");
    }

    @Test
    void testHandlePlayerTurn() {
        game.resetHands();
        game.dealInitialCards();

        InputStream inputStream = new ByteArrayInputStream("1".getBytes());
        System.setIn(inputStream);
        scanner = new Scanner(System.in);
        game.scanner = scanner;

        game.handlePlayerTurn();
        assertEquals(3, game.player.getHand().getCard().size(), "Player must ger another card.");
    }

    @Test
    void testHandleDealerTurn() {
        game.resetHands();
        game.dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.EIGHT));
        game.dealer.getHand().addCard(new Card(Suit.DIAMONDS, Rank.SEVEN));

        game.handleDealerTurn();
        assertTrue(game.dealer.getHand().getCard().size() > 2, "Dealer must have at least 3 cards.");
    }

    @Test
    void testPlayerVictory() {
        game.resetHands();

        game.player.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN));
        game.player.getHand().addCard(new Card(Suit.SPADES, Rank.NINE));
        game.dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        game.dealer.getHand().addCard(new Card(Suit.DIAMONDS, Rank.FIVE));

        game.determineWinner(0, 0);
        assertEquals(1, game.playerWin, "Player must win");
    }

    @Test
    void testPlayerBust() {
        game.resetHands();

        game.player.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN));
        game.player.getHand().addCard(new Card(Suit.SPADES, Rank.NINE));
        game.player.getHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        game.dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        game.dealer.getHand().addCard(new Card(Suit.DIAMONDS, Rank.FIVE));

        game.determineWinner(0, 0);
        assertEquals(1, game.dealerWin, "Player busted scored over 21");
    }

    @Test
    void testDealerDrawDecision() {
        game.resetHands();
        game.dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.TWO));
        game.dealer.getHand().addCard(new Card(Suit.SPADES, Rank.THREE));

        boolean shouldTakeCard = game.dealer.wantsAnotherCard();
        assertTrue(shouldTakeCard, "Dealer has to take another card");
    }

    @Test
    void testDrawScenario() {
        game.resetHands();

        game.player.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN));
        game.player.getHand().addCard(new Card(Suit.SPADES, Rank.NINE));
        game.dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.TEN));
        game.dealer.getHand().addCard(new Card(Suit.DIAMONDS, Rank.NINE));

        game.determineWinner(0, 0);
        assertEquals(0, game.playerWin, "The result should be a draw.");
        assertEquals(0, game.dealerWin, "The result should be a draw.");
    }

    @Test
    void testDetermineWinner() {
        game.resetHands();
        game.player.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN));
        game.player.getHand().addCard(new Card(Suit.SPADES, Rank.NINE));
        game.dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        game.dealer.getHand().addCard(new Card(Suit.DIAMONDS, Rank.FIVE));

        game.determineWinner(0, 0);
        assertEquals(1, game.playerWin, "Player must win once");
    }

    @Test
    void testResetHands() {
        game.resetHands();

        assertEquals(0, game.player.getHand().getCard().size(), "Player must have 0 cards");
        assertEquals(0, game.dealer.getHand().getCard().size(), "Dealer must have 0 cards");
    }

}