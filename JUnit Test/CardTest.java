import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void getSuit() {
        Card cardTest = new Card(Suit.SPADES,Rank.TWO);
        assertEquals(Suit.SPADES, cardTest.getSuit());
    }

    @Test
    public void getRank() {
        Card twoOfSpade = new Card(Suit.SPADES,Rank.TWO);
        assertEquals(Rank.TWO, twoOfSpade.getRank());
    }


    @Test
    public void equals() {
        Card twoOfSpade = new Card(Suit.SPADES,Rank.TWO);
        Card threeOfSpade = new Card(Suit.SPADES, Rank.TWO);
        assertTrue(twoOfSpade.equals(threeOfSpade));
    }

    @Test
    public void isPointCard() {
        Deck deckTester = new Deck();

        for (Card c : deckTester.getDeck()) {

            if (c.isPointCard()){
                System.out.println(c + "\t" + c.getPoint());
            }

        }
    }
}