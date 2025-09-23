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

    @BeforeEach
    void setup() {
        game = new BlackJackGame();
    }

    @Test
    void testInitialDeal() {
        game.resetHands();
        game.setDealInitialCards();
        assertEquals(2, game.getPlayer().getHand().getCardsCopy().size(), "Player must have 2 cards");
        assertEquals(2, game.getDealer().getHand().getCardsCopy().size(), "Dealer must have 2 cards");
    }

    @Test
    void testCalculateScores() {
        game.resetHands();

        game.getPlayer().getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        game.getPlayer().getHand().addCard(new Card(Card.Suit.SPADES, Card.Rank.TEN));
        assertEquals(21, game.getPlayer().getScore(), "Score must be 21");

        game.getDealer().getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
        game.getDealer().getHand().addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN));
        assertEquals(15, game.getDealer().getScore(), "Score must be 15");
    }

    @Test
    void testHandlePlayerTurn() {
        game.resetHands();
        game.setDealInitialCards();

        Scanner scanner = new Scanner("1");
        game.setScanner(scanner);

        game.handlePlayerTurn();
        assertEquals(3, game.getPlayer().getHand().getCardsCopy().size(), "Player must ger another card.");
    }

    @Test
    void testHandleDealerTurn() {
        game.resetHands();
        game.getDealer().getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
        game.getDealer().getHand().addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN));

        game.handleDealerTurn();
        assertTrue(game.getDealer().getHand().getCardsCopy().size() > 2, "Dealer must have at least 3 cards.");
    }

    @Test
    void testPlayerVictory() {
        game.resetHands();

        game.getPlayer().getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        game.getPlayer().getHand().addCard(new Card(Card.Suit.SPADES, Card.Rank.NINE));
        game.getDealer().getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN));
        game.getDealer().getHand().addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE));

        game.determineWinner();
        assertEquals(1, game.playerWin, "Player must win");
    }

    @Test
    void testPlayerBust() {
        game.resetHands();

        game.getPlayer().getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        game.getPlayer().getHand().addCard(new Card(Card.Suit.SPADES, Card.Rank.NINE));
        game.getPlayer().getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        game.getDealer().getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN));
        game.getDealer().getHand().addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE));

        game.determineWinner();
        assertEquals(1, game.dealerWin, "Player busted scored over 21");
    }

    @Test
    void testDealerDrawDecision() {
        game.resetHands();
        game.getDealer().getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        game.getDealer().getHand().addCard(new Card(Card.Suit.SPADES, Card.Rank.THREE));

        boolean shouldTakeCard = game.getDealer().wantsAnotherCard();
        assertTrue(shouldTakeCard, "Dealer has to take another card");
    }

    @Test
    void testDrawScenario() {
        game.resetHands();

        game.getPlayer().getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        game.getPlayer().getHand().addCard(new Card(Card.Suit.SPADES, Card.Rank.NINE));
        game.getDealer().getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.TEN));
        game.getDealer().getHand().addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));

        game.determineWinner();
        assertEquals(0, game.playerWin, "The result should be a draw.");
        assertEquals(0, game.dealerWin, "The result should be a draw.");
    }

    @Test
    void testDetermineWinner() {
        game.resetHands();
        game.getPlayer().getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        game.getPlayer().getHand().addCard(new Card(Card.Suit.SPADES, Card.Rank.NINE));
        game.getDealer().getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN));
        game.getDealer().getHand().addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE));

        game.determineWinner();
        assertEquals(1, game.playerWin, "Player must win once");
    }

    @Test
    void testResetHands() {
        game.resetHands();

        assertEquals(0, game.getPlayer().getHand().getCardsCopy().size(), "Player must have 0 cards");
        assertEquals(0, game.getDealer().getHand().getCardsCopy().size(), "Dealer must have 0 cards");
    }

}