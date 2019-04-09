//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.Assert.*;
//
//public class ComPlayerTest {
//    @Test
//    public void choose3CardsToPass() {
//        ComPlayer player1 = new ComPlayer("Test");
//        Hand hand1 = player1.getHand();
//        List<Card> cardList = new ArrayList<>();
//        cardList.add(new Card(Suit.SPADES, Rank.THREE));
//        cardList.add(new Card(Suit.SPADES, Rank.FOUR));
//        cardList.add(new Card(Suit.SPADES, Rank.FIVE));
//        cardList.add(new Card(Suit.SPADES, Rank.TWO));
//        hand1.addCards(cardList);
//        List<Card> cardTestList = player1.choose3CardsToPass();
//        assertEquals(3, cardTestList.size()); //Test whether it remove 3 cards
//        assertEquals(cardTestList.get(2), hand1.getCardsSortedByRank().get(cardList.size() - 1)); //Test did it take our the biggest Rank
//    }
//
//    @Test
//    public void chooseCardToPlay() {
//        ComPlayer player1 = new ComPlayer("Test");
//        Hand hand1 = player1.getHand();
//        List<Card> cardList = new ArrayList<>();
//        cardList.add(new Card(Suit.SPADES, Rank.THREE));
//        cardList.add(new Card(Suit.HEARTS, Rank.FIVE));
//        hand1.addCards(cardList);
//
//        Set round1 = new Set();
//        round1.addCardToSet(new Card(Suit.SPADES, Rank.TWO),1);
//        round1.addCardToSet(new Card(Suit.SPADES, Rank.FOUR),1);
//
//        Set round2 = new Set();
//
//        Set round3 = new Set();
//        round1.addCardToSet(new Card(Suit.SPADES, Rank.TWO),1);
//        round1.addCardToSet(new Card(Suit.SPADES, Rank.FOUR),1);
//        round1.addCardToSet(new Card(Suit.SPADES, Rank.THREE),1);
//
//        assertEquals(player1.chooseCardToPlay(round3, true), cardList.get(0));
//        assertEquals(player1.chooseCardToPlay(round2, false), cardList.get(0));
//        assertEquals(player1.chooseCardToPlay(round1, false), cardList.get(0)); //Based on the set choose the same suit to play, smallest card
//    }
//
//}