package JUnitTest.tests;

import hearts.model.Card;
import hearts.model.Deck;
import hearts.model.Rank;
import hearts.model.Suit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardTest {

    @Test
    public void getSuit() {
        Card cardTest = new Card(Suit.SPADES, Rank.TWO);
        assertEquals(Suit.SPADES, cardTest.getSuit());
    }

    @Test
    public void getRank() {
        Card twoOfSpade = new Card(Suit.SPADES, Rank.TWO);
        assertEquals(Rank.TWO, twoOfSpade.getRank());
    }

    @Test
    public void equals() {
        Card twoOfSpade = new Card(Suit.SPADES, Rank.TWO);
        Card threeOfSpade = new Card(Suit.SPADES, Rank.TWO);
        assertTrue(twoOfSpade.equals(threeOfSpade));
    }

    @Test
    public void queenSpadesIsPointCard() {
        Card queenSpades = new Card(Suit.SPADES, Rank.QUEEN);
        assertTrue(queenSpades.isPointCard());
    }

    @Test
    public void heartsIsPointCard() {
        Card aceHearts = new Card(Suit.HEARTS, Rank.ACE);
        assertTrue(aceHearts.isPointCard());
    }

    @Test
    public void clubsIsNotPointCard() {
        Card aceClubs = new Card(Suit.CLUBS, Rank.ACE);
        assertTrue(!aceClubs.isPointCard());
    }

    @Test
    public void diamondsIsNotPointCard() {
        Card aceDiamonds = new Card(Suit.DIAMONDS, Rank.ACE);
        assertTrue(!aceDiamonds.isPointCard());
    }

    @Test
    public void aceSpadesIsNotPointCard() {
        Card aceSpades = new Card(Suit.SPADES, Rank.ACE);
        assertTrue(!aceSpades.isPointCard());
    }
}