//import org.junit.Test;
//import java.util.*;
//
//import static org.junit.Assert.*;
//
//public class HandTest {
//    @Test
//    public void addCard() {
//        Hand handTest = new Hand();
//        Card card1 = new Card(Suit.HEARTS, Rank.ACE, null);
//        handTest.addCard(card1);
//        assertEquals(1, handTest.getNumberOfCards());
//    }
//
//    @Test
//    public void addCards() {
//        Hand handTest = new Hand();
//        assertEquals(0, handTest.getNumberOfCards());
//        List<Card> list = new Deck().getDeck();
//        handTest.addCards(list);
//        assertEquals(52, handTest.getNumberOfCards());
//    }
//
//    @Test
//    public void getCard() {
//        Hand hand1 = new Hand();
//        Card c1 = new Card(Suit.HEARTS, Rank.TWO, null);
//        hand1.addCard(c1);
//        assertEquals(c1, hand1.getCard(0));
//    }
//
//    @Test
//    public void removeCard() {
//        Hand hand1 = new Hand();
//        Card c1 = new Card(Suit.HEARTS, Rank.TWO, null);
//        hand1.addCard(c1);
//        assertEquals(1, hand1.getNumberOfCards());
//        hand1.removeCard(c1);
//        assertEquals(0, hand1.getNumberOfCards());
//    }
//
//    @Test
//    public void removeCards() {
//        Hand handTest = new Hand();
//        assertEquals(0, handTest.getNumberOfCards());
//        List<Card> list = new Deck().getDeck();
//        handTest.addCards(list);
//        assertEquals(52, handTest.getNumberOfCards());
//
//        List<Card> cardsToRemove = new ArrayList<>();
//        cardsToRemove.add(new Card(Suit.HEARTS, Rank.TWO, null));
//        cardsToRemove.add(new Card(Suit.CLUBS, Rank.TWO, null));
//        cardsToRemove.add(new Card(Suit.SPADES, Rank.TWO, null));
//        handTest.removeCards(cardsToRemove);
//        // removes 3 cards
//
//        assertEquals(49, handTest.getNumberOfCards());
//    }
//
//    @Test
//    public void removeCardByIndex() {
//        Hand handTest = new Hand();
//
//        ArrayList<Card> cardsToRemove = new ArrayList<>();
//        cardsToRemove.add(new Card(Suit.HEARTS, Rank.TWO, null));
//        cardsToRemove.add(new Card(Suit.CLUBS, Rank.TWO, null));
//        cardsToRemove.add(new Card(Suit.SPADES, Rank.TWO, null));
//        handTest.addCards(cardsToRemove);
//
////        for (Card c: handTest.getCardList()) {
//        for (int i = 0; i < handTest.getCardList().size(); i++) {
//            System.out.println("index : " + i + " -> " + handTest.getCard(i));
//        }
//        System.out.println("=== index 2 removed ===");
//
//        handTest.removeCard(2);
//        for (int i = 0; i < handTest.getCardList().size(); i++) {
//            System.out.println("index : " + i + " -> " + handTest.getCard(i));
//        }
//    }
//
//    @Test
//    public void containsCard() {
//        Hand handTest = new Hand();
//        ArrayList<Card> listofcard = new ArrayList<>();
//        listofcard.add(new Card(Suit.SPADES, Rank.THREE, null));
//        listofcard.add(new Card(Suit.SPADES, Rank.FOUR, null));
//        listofcard.add(new Card(Suit.HEARTS, Rank.TWO, null));
//        listofcard.add(new Card(Suit.CLUBS, Rank.TWO, null));
//        listofcard.add(new Card(Suit.SPADES, Rank.TWO, null));
//        handTest.addCards(listofcard);
//        Card c1 = new Card(Suit.HEARTS, Rank.TWO, null);
//        assertTrue(handTest.containsCard(c1));
//    }
//
////    @Test
////    public void findCardIndex() {
////        Hand handTest = new Hand();
////        ArrayList<Card> listofcard = new ArrayList<>();
////        listofcard.add(new Card(Suit.SPADES, Rank.THREE, null));
////        listofcard.add(new Card(Suit.SPADES, Rank.FOUR, null));
////        listofcard.add(new Card(Suit.HEARTS, Rank.TWO, null));
////        listofcard.add(new Card(Suit.CLUBS, Rank.TWO, null));
////        listofcard.add(new Card(Suit.SPADES, Rank.TWO, null));
////        handTest.addCards(listofcard);
////        Card c1 = new Card(Suit.HEARTS, Rank.TWO, null);
////        assertEquals(1, handTest.findCardIndex(c1));
////    }
//
//
////    @Test
////    public void replaceCard() {
////        Hand handTest = new Hand();
////        ArrayList<Card> listofcard = new ArrayList<>();
////        Card c2 = new Card(Suit.HEARTS, Rank.TWO, null);
////        Card c3 = new Card(Suit.CLUBS, Rank.TWO, null);
////        listofcard.add(c2);
////        listofcard.add(c3);
////        handTest.addCards(listofcard);
////        Card c1 = new Card(Suit.HEARTS, Rank.FOUR, null);
////        assertTrue(handTest.replaceCard(c2, c1));
////    }
//
//
//    @Test
//    public void getNextHighestComCard() {
//
//        Hand handTest = new Hand();
//
//        handTest.addCard(new Card(Suit.SPADES, Rank.EIGHT, null));
//        handTest.addCard(new Card(Suit.SPADES, Rank.FOUR, null));
//        handTest.addCard(new Card(Suit.SPADES, Rank.JACK, null));
//
//        Card eightOfSpades = new Card(Suit.SPADES, Rank.EIGHT, null);
//        Card currentCard = new Card(Suit.SPADES, Rank.TEN, null);
//        assertEquals(eightOfSpades, handTest.getNextHighestComCard(Suit.SPADES, currentCard, true));
//
//        System.out.println(handTest.getNextHighestComCard(Suit.SPADES, currentCard, true));
//    }
//
//    @Test
//    public void getSmallestComCard() {
//        Hand handTest = new Hand();
//
//        handTest.addCard(new Card(Suit.SPADES, Rank.EIGHT, null));
//        handTest.addCard(new Card(Suit.SPADES, Rank.JACK, null));
//
//        Card eightOfSpades = new Card(Suit.SPADES, Rank.EIGHT, null);
////        assertEquals(eightOfSpades, handTest.getSmallestComCard(false));
//        System.out.println(handTest.getSmallestComCard(false) + "\n ==== ");
//
//        handTest.addCard(new Card(Suit.SPADES, Rank.FOUR, null));
////        Card currentCard = new Card(Suit.SPADES, Rank.TEN, null);
//        System.out.println(handTest.getSmallestComCard(false));
//
//
//    }
//
//    @Test
//    public void getNextHighestCard() {
//        Hand handTest = new Hand();
//
//        handTest.addCard(new Card(Suit.SPADES, Rank.SEVEN, null));
//        handTest.addCard(new Card(Suit.SPADES, Rank.FOUR, null));
//
//        Card eightOfSpades = new Card(Suit.SPADES, Rank.EIGHT, null);
//        System.out.println(handTest.getNextHighestCard(Suit.SPADES, eightOfSpades ) + "\n ==== ");
//
//        handTest.addCard(new Card(Suit.SPADES, Rank.SIX, null));
//        Card sevenofSpades = new Card(Suit.SPADES, Rank.SEVEN, null);
//        System.out.println(handTest.getNextHighestCard(Suit.SPADES, sevenofSpades ));
//    }
//}