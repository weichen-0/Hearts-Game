package JUnitTest.tests;

import hearts.model.Card;
import hearts.model.Rank;
import hearts.model.Suit;
import hearts.util.CardSuitComparator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CardSuitComparatorTest {
    private CardSuitComparator cardSuitComparator;

    @Before
    public void instantiateHand(){
        cardSuitComparator = new CardSuitComparator();
    }

    @Test
    public void compareSameRankLowerSuit(){
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        Card card4Clubs = new Card(Suit.CLUBS, Rank.FOUR);
        int result = cardSuitComparator.compare(card4Hearts, card4Clubs);
        assertTrue(result > 0); // clubs lower than hearts
    }

    @Test
    public void compareSameRankHigherSuit(){
        Card card5Hearts = new Card(Suit.HEARTS, Rank.FIVE);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        int result = cardSuitComparator.compare(card5Hearts, card5Spade);
        assertTrue(result < 0); // spades higher than hearts
    }

    @Test
    public void compareSameSuitHigherRank(){
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO);
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        int result = cardSuitComparator.compare(card2Hearts, card4Hearts);
        assertTrue(result < 0); // equal suits but rank 4 greater than 2
    }

    @Test
    public void compareSameSuitSameRank(){
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        int result = cardSuitComparator.compare(card4Hearts, card4Hearts);
        assertTrue(result == 0); // exactly same suit and rank
    }
}