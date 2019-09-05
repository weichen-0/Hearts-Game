package JUnitTest.tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;
import hearts.model.*;
import hearts.model.Set;

public class SetTest {
    private Set setTest;
    @Before
    public void instantiateSet(){
        setTest = new hearts.model.Set();
    }

    @Test
    public void getSetCards() {
        hearts.model.Card card2Spade = new hearts.model.Card(hearts.model.Suit.SPADES,hearts.model.Rank.TWO);
        hearts.model.Card card3Hearts = new hearts.model.Card(hearts.model.Suit.HEARTS, hearts.model.Rank.THREE);
        setTest.addCardToSet(card2Spade, 1);
        setTest.addCardToSet(card3Hearts, 2);
        List<hearts.model.Card> test = setTest.getSetCards();
        assertEquals(2, test.size()); //Test if it is able to retrieve all the cards in the set
    }

    @Test
    public void getLeadingSuit() {
        hearts.model.Card card2Spade = new hearts.model.Card(hearts.model.Suit.SPADES,hearts.model.Rank.TWO);
        hearts.model.Card card3Hearts = new hearts.model.Card(hearts.model.Suit.HEARTS, hearts.model.Rank.THREE);
        setTest.addCardToSet(card2Spade, 1);
        setTest.addCardToSet(card3Hearts, 2);
        assertEquals(hearts.model.Suit.SPADES, setTest.getLeadingSuit());
    }

    @Test
    public void addCardToSet() {
        assertEquals(0, setTest.getNumOfCardsInSet()); //The initial set got 0 cards
        hearts.model.Card card2Spade = new hearts.model.Card(hearts.model.Suit.SPADES,hearts.model.Rank.TWO);
        setTest.addCardToSet(card2Spade, 1);
        assertEquals(1, setTest.getNumOfCardsInSet()); //Test the function if the number of cards had been added successfully
    }

    @Test
    public void getWinningCardIndex() {
        hearts.model.Card card2Spade = new hearts.model.Card(hearts.model.Suit.SPADES,hearts.model.Rank.TWO);
        hearts.model.Card card3Spade = new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.THREE);
        hearts.model.Card card4Spade= new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FOUR);
        hearts.model.Card card5Spade = new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FIVE);
        setTest.addCardToSet(card2Spade,1);
        setTest.addCardToSet(card3Spade,2);
        setTest.addCardToSet(card4Spade,3);
        setTest.addCardToSet(card5Spade,4);

        assertEquals(3, setTest.getWinningCardIndex()); //Test the highest card index from the set
    }

    @Test
    public void getWinningCardIndexWithTrump() {
        Card card7Hearts = new Card(Suit.HEARTS, Rank.SEVEN);
        Card cardKHearts = new Card(Suit.HEARTS, Rank.KING);
        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
        Card card6Clubs = new Card(Suit.CLUBS, Rank.SIX);
        setTest.addCardToSet(card7Hearts, 1);
        setTest.addCardToSet(cardKHearts, 2);
        setTest.addCardToSet(card3Spade, 3);
        setTest.addCardToSet(card6Clubs, 4);
        assertEquals(1, setTest.getWinningCardIndex());
    }

    @Test
    public void getWinningCard() {
        hearts.model.Card card2Spade = new hearts.model.Card(hearts.model.Suit.SPADES,hearts.model.Rank.TWO);
        hearts.model.Card card3Spade = new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.THREE);
        hearts.model.Card card4Spade= new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FOUR);
        hearts.model.Card card5Spade = new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FIVE);
        setTest.addCardToSet(card2Spade,1);
        setTest.addCardToSet(card3Spade,2);
        setTest.addCardToSet(card4Spade,3);
        setTest.addCardToSet(card5Spade,4);

        assertEquals(card5Spade, setTest.getWinningCard());
    }

    @Test
    public void getTotalPointInSet(){
        hearts.model.Card card2Spade = new hearts.model.Card(hearts.model.Suit.SPADES,hearts.model.Rank.TWO);
        hearts.model.Card card3Spade = new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.THREE);
        hearts.model.Card card3Hearts= new hearts.model.Card(hearts.model.Suit.HEARTS, hearts.model.Rank.FOUR);
        hearts.model.Card card5Spade = new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FIVE);
        setTest.addCardToSet(card2Spade,1);
        setTest.addCardToSet(card3Spade,2);
        setTest.addCardToSet(card3Hearts,3);
        setTest.addCardToSet(card5Spade,4);

        int total = 0;
        total += card2Spade.getPoint();
        total += card3Spade.getPoint();
        total += card3Hearts.getPoint();
        total += card5Spade.getPoint();

        assertEquals(1, setTest.getTotalPointsInSet());
    }

    @Test
    public void getPlayerNumLastPlayed(){
        hearts.model.Card card2Spade = new hearts.model.Card(hearts.model.Suit.SPADES,hearts.model.Rank.TWO);
        hearts.model.Card card3Spade = new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.THREE);
        hearts.model.Card card3Hearts= new hearts.model.Card(hearts.model.Suit.HEARTS, hearts.model.Rank.FOUR);
        hearts.model.Card card5Spade = new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FIVE);
        setTest.addCardToSet(card2Spade,1);
        setTest.addCardToSet(card5Spade,4);
        setTest.addCardToSet(card3Spade,2);
        setTest.addCardToSet(card3Hearts,3);


        assertEquals(3, setTest.getPlayerNumLastPlayed());

    }
}