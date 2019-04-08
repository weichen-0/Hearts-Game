import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ComPlayerTest {
    @Test
    public void choose3CardsToPass(){
        ComPlayer player1 = new ComPlayer("Test");
        Hand hand1 = player1.getHand();
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADES,Rank.THREE, null));
        cardList.add(new Card(Suit.SPADES,Rank.FOUR, null));
        cardList.add(new Card(Suit.SPADES,Rank.FIVE, null));
        cardList.add(new Card(Suit.SPADES,Rank.TWO, null));
        hand1.addCards(cardList);
        List<Card> cardTestList = player1.choose3CardsToPass();
        assertEquals(3, cardTestList.size()); //Test whether it remove 3 cards
        assertEquals(cardTestList.get(2), hand1.getCardsSortedByRank().get(cardList.size()-1)); //Test did it take our the biggest Rank
    }
    @Test
    public void chooseCardToPlay(){
        ComPlayer player1 = new ComPlayer("Test");
        Hand hand1 = player1.getHand();
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADES,Rank.THREE, null));
        cardList.add(new Card(Suit.HEARTS,Rank.FIVE, null));
        hand1.addCards(cardList);

        Set round1 = new Set();
        round1.addCardToSet(new Card(Suit.SPADES,Rank.TWO, null));
        round1.addCardToSet(new Card(Suit.SPADES,Rank.FOUR, null));

        Set round2 = new Set();

        Set round3 = new Set();
        round1.addCardToSet(new Card(Suit.SPADES,Rank.TWO, null));
        round1.addCardToSet(new Card(Suit.SPADES,Rank.FOUR, null));
        round1.addCardToSet(new Card(Suit.SPADES,Rank.THREE, null));

        assertEquals(player1.chooseCardToPlay(round3, true), cardList.get(0));
        assertEquals(player1.chooseCardToPlay(round2, false), cardList.get(0));
        assertEquals(player1.chooseCardToPlay(round1, false), cardList.get(0)); //Based on the set choose the same suit to play, smallest card
    }
//    @Test
//    public void getSmallestComCard() {
//        Hand handTest = new Hand();
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
//    }

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
}