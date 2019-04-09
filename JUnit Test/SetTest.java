//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//import java.util.*;
//public class SetTest {
//    private Set setTest;
//    @Before
//    public void instantiateSet(){
//        setTest = new Set();
//    }
//
//    @Test
//    public void getSetCards() {
//        Card card2Spade = new Card(Suit.SPADES,Rank.TWO);
//        Card card3Hearts = new Card(Suit.HEARTS, Rank.THREE);
//        setTest.addCardToSet(card2Spade, 1);
//        setTest.addCardToSet(card3Hearts, 2);
//        List<Card> test = setTest.getSetCards();
//        assertEquals(2, test.size()); //Test if it is able to retrieve all the cards in the set
//    }
//
//    @Test
//    public void getLeadingSuit() {
//        Card card2Spade = new Card(Suit.SPADES,Rank.TWO);
//        Card card3Hearts = new Card(Suit.HEARTS, Rank.THREE);
//        setTest.addCardToSet(card2Spade, 1);
//        setTest.addCardToSet(card3Hearts, 2);
//        assertEquals(Suit.SPADES, setTest.getLeadingSuit());
//    }
//
//    @Test
//    public void addCardToSet() {
//        assertEquals(0, setTest.getNumOfCardsInSet()); //The initial set got 0 cards
//        Card card2Spade = new Card(Suit.SPADES,Rank.TWO);
//        setTest.addCardToSet(card2Spade, 1);
//        assertEquals(1, setTest.getNumOfCardsInSet()); //Test the function if the number of cards had been added successfully
//    }
//
//    @Test
//    public void getHighestCardIndex() {
//        Card card2Spade = new Card(Suit.SPADES,Rank.TWO);
//        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
//        Card card4Spade= new Card(Suit.SPADES, Rank.FOUR);
//        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
//        setTest.addCardToSet(card2Spade,1);
//        setTest.addCardToSet(card3Spade,2);
//        setTest.addCardToSet(card4Spade,3);
//        setTest.addCardToSet(card5Spade,4);
//
//        assertEquals(3, setTest.getHighestCardIndex()); //Test the highest card index from the set
//    }
//
//    @Test
//    public void getWinningCard() {
//        Card card2Spade = new Card(Suit.SPADES,Rank.TWO);
//        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
//        Card card4Spade= new Card(Suit.SPADES, Rank.FOUR);
//        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
//        setTest.addCardToSet(card2Spade,1);
//        setTest.addCardToSet(card3Spade,2);
//        setTest.addCardToSet(card4Spade,3);
//        setTest.addCardToSet(card5Spade,4);
//
//        assertEquals(card5Spade, setTest.getWinningCard());
//    }
//
//    @Test
//    public void getWinningPlayerIndex(){
//        Card card2Spade = new Card(Suit.SPADES,Rank.TWO);
//        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
//        Card card4Spade= new Card(Suit.SPADES, Rank.FOUR);
//        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
//        setTest.addCardToSet(card2Spade,1);
//        setTest.addCardToSet(card3Spade,2);
//        setTest.addCardToSet(card4Spade,3);
//        setTest.addCardToSet(card5Spade,4);
//
//        assertEquals(0, setTest.getWinningPlayerIndex());
//    }
//
//    @Test
//    public void getTotalPointInSet(){
//        Card card2Spade = new Card(Suit.SPADES,Rank.TWO);
//        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
//        Card card3Hearts= new Card(Suit.HEARTS, Rank.FOUR);
//        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
//        setTest.addCardToSet(card2Spade,1);
//        setTest.addCardToSet(card3Spade,2);
//        setTest.addCardToSet(card3Hearts,3);
//        setTest.addCardToSet(card5Spade,4);
//
//        int total = 0;
//        total += card2Spade.getPoint();
//        total += card3Spade.getPoint();
//        total += card3Hearts.getPoint();
//        total += card5Spade.getPoint();
//
//        assertEquals(1, setTest.getTotalPointsInSet());
//    }
//
//    @Test
//    public void getPlayerNumLastPlayed(){
//        Card card2Spade = new Card(Suit.SPADES,Rank.TWO);
//        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
//        Card card3Hearts= new Card(Suit.HEARTS, Rank.FOUR);
//        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
//        setTest.addCardToSet(card2Spade,1);
//        setTest.addCardToSet(card5Spade,4);
//        setTest.addCardToSet(card3Spade,2);
//        setTest.addCardToSet(card3Hearts,3);
//
//
//        assertEquals(3, setTest.getPlayerNumLastPlayed());
//
//    }
//}