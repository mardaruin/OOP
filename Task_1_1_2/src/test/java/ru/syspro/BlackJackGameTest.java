package ru.syspro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BlackJackGameTest {

    private List<Card> fixedDeck;
    private Scanner mockScanner;
    private BlackJackGame game;

    @BeforeEach
    void setup() {
        fixedDeck = new ArrayList<>();
        fixedDeck.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        fixedDeck.add(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        fixedDeck.add(new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN));
        fixedDeck.add(new Card(Card.Suit.SPADES, Card.Rank.TWO));

        mockScanner = new Scanner("1\n0");

        game = new BlackJackGame(mockScanner, new Deck());
        //game = new BlackJackGame(mockScanner, new Deck(fixedDeck));
    }

    @Test
    void testPlayOneRound() {
        game.play();

        assertNotNull(game.getPlayer().getHand());
        assertNotNull(game.getDealer().getHand());
    }

    @Test
    void testInitialDeal() {
        game.resetHands();
        game.setDealInitialCards();

        assertEquals(2, game.getPlayer().getHand().getCardsCopy().size());
        assertEquals(2, game.getDealer().getHand().getCardsCopy().size());
    }

    @Test
    void testBlackjackVictory() {
        game = new BlackJackGame(mockScanner, new Deck(fixedDeck));

        game.play();
        assertTrue(game.getPlayer().hasBlackjack());
        assertFalse(game.getDealer().hasBlackjack());
        assertEquals(1, game.playerWin);
    }

    @Test
    void testBustScenario() {
        game.getPlayer().receiveCard(new Card(Card.Suit.HEARTS, Card.Rank.NINE));
        game.getPlayer().receiveCard(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        game.getPlayer().receiveCard(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE));

        assertTrue(game.getPlayer().busted());
    }

    @Test
    void testDetermineWinner() {
        game.getPlayer().score = 18;
        game.getDealer().score = 16;
        game.getDealer().setMockScore();
        game.getPlayer().setMockScore();

        game.determineWinner();
        assertEquals(1, game.playerWin);
        assertEquals(0, game.dealerWin);
    }

    @Test
    void testHandlePlayerTurn_Scenario() {
        List<Card> fixedDeck = new ArrayList<>();
        fixedDeck.add(new Card(Card.Suit.HEARTS, Card.Rank.EIGHT));
        fixedDeck.add(new Card(Card.Suit.CLUBS, Card.Rank.FOUR));
        fixedDeck.add(new Card(Card.Suit.DIAMONDS, Card.Rank.SIX));
        fixedDeck.add(new Card(Card.Suit.SPADES, Card.Rank.TWO));
        fixedDeck.add(new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN));
        fixedDeck.add(new Card(Card.Suit.SPADES, Card.Rank.NINE));

        Scanner mockScanner = new Scanner("1\n1\n0");

        game = new BlackJackGame(mockScanner, new Deck(fixedDeck));

        game.resetHands();
        game.setDealInitialCards();

        game.handlePlayerTurn();

        Hand playerHand = game.getPlayer().getHand();
        assertEquals(4, playerHand.getCardsCopy().size());
        assertEquals(28, game.getPlayer().getScore());
        assertTrue(game.getPlayer().busted());

        game.determineWinner();
        assertEquals(0, game.playerWin);
        assertEquals(1, game.dealerWin);
    }

}