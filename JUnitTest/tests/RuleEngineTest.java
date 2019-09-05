package JUnitTest.tests;

import hearts.model.*;
import hearts.util.*;
import hearts.exception.*;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RuleEngineTest {
    @Test
    public void getStartPlayerIndex(){
        RuleEngine regulatorTest = new RuleEngine();
        Game gametest = new Game(4);
        Player[] player = gametest.getListOfPlayers();
        Hand hand1 = player[0].getHand();
        Hand hand2 = player[1].getHand();
        Hand hand3 = player[2].getHand();
        Hand hand4 = player[3].getHand();

        hand1.addCard(new Card(Suit.DIAMONDS, Rank.TWO));
        hand2.addCard(new Card(Suit.HEARTS, Rank.TWO));
        hand3.addCard(new Card(Suit.CLUBS, Rank.TWO));
        hand4.addCard(new Card(Suit.SPADES, Rank.TWO));

        assertEquals(2, regulatorTest.getStartPlayerIndex(player)); //To test the function to locate the 2 clubs and return the player index
    }

    @Test
    public void validateCardPlayedFirstSetNoPointCardQueenSpades(){
        RuleEngine regulatorTest = new RuleEngine();
        Game gametest = new Game(4);
        Player[] players = gametest.getListOfPlayers();

        int playerNum = 0;
        Hand playerHand = players[playerNum].getHand();
        Card cardQueenSpades = new Card(Suit.SPADES, Rank.QUEEN);

        // is first set, playerhand has 13 cards
        playerHand.addCard(new Card(Suit.SPADES, Rank.ACE));
        playerHand.addCard(new Card(Suit.SPADES, Rank.TWO));
        playerHand.addCard(new Card(Suit.SPADES, Rank.THREE));
        playerHand.addCard(new Card(Suit.SPADES, Rank.FOUR));
        playerHand.addCard(new Card(Suit.SPADES, Rank.FIVE));
        playerHand.addCard(new Card(Suit.SPADES, Rank.SIX));
        playerHand.addCard(new Card(Suit.SPADES, Rank.SEVEN));
        playerHand.addCard(new Card(Suit.SPADES, Rank.EIGHT));
        playerHand.addCard(new Card(Suit.SPADES, Rank.NINE));
        playerHand.addCard(new Card(Suit.SPADES, Rank.TEN));
        playerHand.addCard(new Card(Suit.SPADES, Rank.JACK));
        playerHand.addCard(new Card(Suit.SPADES, Rank.KING));
        playerHand.addCard(cardQueenSpades);

        Set firstSet = new Set();
        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO);
        firstSet.addCardToSet(twoClubs, playerNum);

        assertThrows(IllegalMoveException.class, () -> {
            regulatorTest.validateCardPlayed(players[0], cardQueenSpades, firstSet, false);
        });
    }

    @Test
    public void validateCardPlayedFirstSetNoPointCardHearts(){
        RuleEngine regulatorTest = new RuleEngine();
        Game gametest = new Game(4);
        Player[] players = gametest.getListOfPlayers();

        int playerNum = 0;
        Hand playerHand = players[playerNum].getHand();
        Card cardJackHearts = new Card(Suit.HEARTS, Rank.JACK);

        // is first set, playerhand has 13 cards
        playerHand.addCard(new Card(Suit.SPADES, Rank.ACE));
        playerHand.addCard(new Card(Suit.SPADES, Rank.TWO));
        playerHand.addCard(new Card(Suit.SPADES, Rank.THREE));
        playerHand.addCard(new Card(Suit.SPADES, Rank.FOUR));
        playerHand.addCard(new Card(Suit.SPADES, Rank.FIVE));
        playerHand.addCard(new Card(Suit.SPADES, Rank.SIX));
        playerHand.addCard(new Card(Suit.SPADES, Rank.SEVEN));
        playerHand.addCard(new Card(Suit.SPADES, Rank.EIGHT));
        playerHand.addCard(new Card(Suit.SPADES, Rank.NINE));
        playerHand.addCard(new Card(Suit.SPADES, Rank.TEN));
        playerHand.addCard(new Card(Suit.SPADES, Rank.JACK));
        playerHand.addCard(new Card(Suit.SPADES, Rank.KING));
        playerHand.addCard(cardJackHearts);

        Set firstSet = new Set();
        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO);
        firstSet.addCardToSet(twoClubs, playerNum);

        assertThrows(IllegalMoveException.class, () -> {
            regulatorTest.validateCardPlayed(players[0], cardJackHearts, firstSet, false);
        });
    }

    @Test
    public void validateCardPlayedHeartsNotBroken(){
        RuleEngine regulatorTest = new RuleEngine();
        Game gametest = new Game(4);
        Player[] players = gametest.getListOfPlayers();

        Card aceHearts = new Card(Suit.HEARTS, Rank.ACE);
        Card aceClubs = new Card(Suit.CLUBS, Rank.ACE);
        int playerNum = 0;
        Hand playerHand = players[playerNum].getHand();
        playerHand.addCard(aceHearts);
        playerHand.addCard(aceClubs);

        Set firstSet = new Set();
        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO);
        firstSet.addCardToSet(twoClubs, playerNum);

        assertThrows(IllegalMoveException.class, () -> {
            regulatorTest.validateCardPlayed(players[0], aceHearts, firstSet, false);
        });
    }

    @Test
    public void validateCardPlayedHeartsBrokenCanPlayHearts(){
        RuleEngine regulatorTest = new RuleEngine();
        Game gametest = new Game(4);
        Player[] players = gametest.getListOfPlayers();

        Card aceHearts = new Card(Suit.HEARTS, Rank.ACE);
        Card aceClubs = new Card(Suit.CLUBS, Rank.ACE);
        int playerNum = 0;
        Hand playerHand = players[playerNum].getHand();
        playerHand.addCard(aceHearts);
        playerHand.addCard(aceClubs);

        Set notFirstSet = new Set();
        Card twoDiamonds = new Card(Suit.DIAMONDS, Rank.TWO);
        notFirstSet.addCardToSet(twoDiamonds, playerNum);
    }
}