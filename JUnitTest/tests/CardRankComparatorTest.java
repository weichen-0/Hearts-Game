package JUnitTest.tests;

import hearts.model.Card;
import hearts.model.Rank;
import hearts.model.Suit;
import hearts.util.CardRankComparator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CardRankComparatorTest {
    private CardRankComparator cardRankComparator;

    @Before
    public void instantiateHand(){
        cardRankComparator = new CardRankComparator();
    }

    @Test
    public void compareSameSuitLowerRank(){
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO);
        int result = cardRankComparator.compare(card4Hearts, card2Hearts);
        assertTrue(result > 0); // one rank is higher
    }

    @Test
    public void compareSameSuitHigherRank(){
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO);
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        int result = cardRankComparator.compare(card2Hearts, card4Hearts);
        assertTrue(result < 0); // one rank is higher
    }

    @Test
    public void compareSameRankHigherSuit(){
        Card card5Hearts = new Card(Suit.HEARTS, Rank.FIVE);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        int result = cardRankComparator.compare(card5Hearts, card5Spade);
        assertTrue(result < 0); // same rank so compare suits, hearts smaller than spades
    }

    @Test
    public void compareSameSuitSameRank(){
        Card cardKDiamonds = new Card(Suit.DIAMONDS, Rank.KING);
        int result = cardRankComparator.compare(cardKDiamonds, cardKDiamonds);
        assertTrue(result == 0); // exactly same suit and rank
    }
}