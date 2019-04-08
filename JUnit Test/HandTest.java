import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class HandTest {
    private Hand handTest;

    @Before
    public void instantiateHand(){
        handTest = new Hand();
    }

    @Test
    public void addCard() {
        assertEquals(0, handTest.getNumberOfCards()); //Hand start off with 0 card test the Add method, (1 card at a go)
        Card cardAHearts = new Card(Suit.HEARTS, Rank.ACE, null);
        handTest.addCard(cardAHearts);
        assertEquals(1, handTest.getNumberOfCards());
    }

    @Test
    public void addCards() {
        assertEquals(0, handTest.getNumberOfCards()); //Hand start off with 0 card test the Add method, (>1 card at a go)
        List<Card> list = new Deck().getDeck();
        handTest.addCards(list);
        assertEquals(52, handTest.getNumberOfCards());
    }

    @Test
    public void getCard() {
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO, null); //Test method to get a card from the hand
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR, null);
        handTest.addCard(card2Hearts);
        handTest.addCard(card4Hearts);
        assertEquals(card2Hearts, handTest.getCard(0));
    }

    @Test
    public void removeCard() {
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO, null); //Test method to remove a card that is being specify
        handTest.addCard(card2Hearts);
        assertEquals(1, handTest.getNumberOfCards());
        handTest.removeCard(card2Hearts);
        assertEquals(0, handTest.getNumberOfCards());
    }

    @Test
    public void removeCards() {
        assertEquals(0, handTest.getNumberOfCards()); //Method will remove a list of cards from the Hand
        List<Card> list = new Deck().getDeck();
        handTest.addCards(list);
        assertEquals(52, handTest.getNumberOfCards());

        List<Card> cardsToRemove = new ArrayList<>();
        cardsToRemove.add(new Card(Suit.HEARTS, Rank.TWO, null));
        cardsToRemove.add(new Card(Suit.CLUBS, Rank.TWO, null));
        cardsToRemove.add(new Card(Suit.SPADES, Rank.TWO, null));
        handTest.removeCards(cardsToRemove); //remove 3 cards

        assertEquals(49, handTest.getNumberOfCards());
    }

    @Test
    public void removeCardByIndex() {
        ArrayList<Card> cardsToRemove = new ArrayList<>();
        cardsToRemove.add(new Card(Suit.HEARTS, Rank.TWO, null));
        cardsToRemove.add(new Card(Suit.CLUBS, Rank.TWO, null));
        cardsToRemove.add(new Card(Suit.SPADES, Rank.TWO, null));
        handTest.addCards(cardsToRemove);

//        for (Card c: handTest.getCardList()) {
        for (int i = 0; i < handTest.getCardList().size(); i++) {
            System.out.println("index : " + i + " -> " + handTest.getCard(i));
        }
        System.out.println("=== index 2 removed ===");

        handTest.removeCard(2);
        for (int i = 0; i < handTest.getCardList().size(); i++) {
            System.out.println("index : " + i + " -> " + handTest.getCard(i));
        }
    }

    @Test
    public void containsCard() {
        ArrayList<Card> listOfCards = new ArrayList<>(); //Add a list of card into a hand
        listOfCards.add(new Card(Suit.SPADES, Rank.THREE, null));
        listOfCards .add(new Card(Suit.SPADES, Rank.FOUR, null));
        listOfCards .add(new Card(Suit.HEARTS, Rank.TWO, null));
        listOfCards .add(new Card(Suit.CLUBS, Rank.TWO, null));
        listOfCards .add(new Card(Suit.SPADES, Rank.TWO, null));
        handTest.addCards(listOfCards );
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO, null); //Check if the hand contains the card
        assertTrue(handTest.containsCard(card2Hearts));
    }

    @Test
    public void hasSuit(){
        Card cardAHearts = new Card(Suit.HEARTS, Rank.ACE, null);
        handTest.addCard(cardAHearts);
        assertTrue(handTest.hasSuit(Suit.HEARTS));
    }

//    @Test
//    public void findCardIndex() {
//        Hand handTest = new Hand();
//        ArrayList<Card> listofcard = new ArrayList<>();
//        listofcard.add(new Card(Suit.SPADES, Rank.THREE, null));
//        listofcard.add(new Card(Suit.SPADES, Rank.FOUR, null));
//        listofcard.add(new Card(Suit.HEARTS, Rank.TWO, null));
//        listofcard.add(new Card(Suit.CLUBS, Rank.TWO, null));
//        listofcard.add(new Card(Suit.SPADES, Rank.TWO, null));
//        handTest.addCards(listofcard);
//        Card c1 = new Card(Suit.HEARTS, Rank.TWO, null);
//        assertEquals(1, handTest.findCardIndex(c1));
//    }
    //Todo







}