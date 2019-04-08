//import org.junit.Test;
//
//import javax.swing.*;
//
//import static org.junit.Assert.*;
//
//public class CardTest {
//
//    @Test
//    public void getSuit() {
//        Card cardTest = new Card(Suit.SPADES,Rank.TWO, null);
//        assertEquals(Suit.SPADES, cardTest.getSuit());
//    }
//
//    @Test
//    public void getRank() {
//        Card twoOfSpade = new Card(Suit.SPADES,Rank.TWO, null);
//        assertEquals(Rank.TWO, twoOfSpade.getRank());
//    }
//
//    @Test
//    public void compareTo() {
//        Card twoOfSpade = new Card(Suit.SPADES,Rank.TWO, null);
//        Card threeOfHearts = new Card(Suit.HEARTS, Rank.THREE, null);
//        int suitDiff = twoOfSpade.getSuit().compareTo(threeOfHearts.getSuit());
//        assertEquals(1, suitDiff);
//        int rankDiff = twoOfSpade.getRank().compareTo(threeOfHearts.getRank());
//        assertEquals(-1, rankDiff);
//    }
//
//    @Test
//    public void equals() {
//        Card twoOfSpade = new Card(Suit.SPADES,Rank.TWO, null);
//        Card threeOfSpade = new Card(Suit.SPADES, Rank.TWO, null);
//        assertTrue(twoOfSpade.equals(threeOfSpade));
//    }
//
//
//    @Test
//    public void isPointCard() {
//        Deck deckTester = new Deck();
//
//        for (Card c : deckTester.getDeck()) {
//
//            if (c.isPointCard()){
//                System.out.println(c + "\t" + c.getPoint());
//            }
//
//        }
//    }
//}