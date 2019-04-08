import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class CardTest {
    private Card card2Spade;
    private Card card2Spade1;
    private Card card3Hearts;
    private Card card8Diamond;
    private Deck deckTester;

    @Before
    public void instantiateCard(){
        card2Spade = new Card (Suit.SPADES,Rank.TWO, null);
        card2Spade1 = new Card (Suit.SPADES,Rank.TWO, null);
        card3Hearts = new Card (Suit.HEARTS,Rank.THREE, null);
        card8Diamond = new Card (Suit.DIAMONDS,Rank.EIGHT, null);
        deckTester = new Deck();
    }

    @Test
    public void getSuit() {
        assertEquals(Suit.SPADES, card2Spade.getSuit()); //Test if the getSuit method get the correct Suit based on the card inserted
    }

    @Test
    public void getRank() {
        assertEquals(Rank.TWO, card2Spade.getRank()); //Test if the getSuit method get the correct Rank based on the card i
    }

    @Test
    public void compareTo() {
        int suitDiff = card2Spade.getSuit().compareTo(card3Hearts.getSuit());
        assertEquals(1, suitDiff); //Test the difference in the Suit
        int rankDiff = card2Spade.getRank().compareTo(card3Hearts.getRank());
        assertEquals(-1, rankDiff); //Test the difference in the Rank;
        int suitDiff1 = card2Spade.getSuit().compareTo(card8Diamond.getSuit());
        assertEquals(2, suitDiff1); //Test the difference in the Suit
        int rankDiff1 = card2Spade.getRank().compareTo(card8Diamond.getRank());
        assertEquals(-6, rankDiff1); //Test the difference in the Rank;
    }

    @Test
    public void equals() {
        assertTrue(card2Spade.equals(card2Spade1)); //Test the equals method
    }


    @Test
    public void isPointCard() {
        for (Card c : deckTester.getDeck()) {
            if (c.isPointCard()){
                System.out.println(c + "\t" + c.getPoint()); //Test whether the point for each card is correct
            }

        }
    }
}