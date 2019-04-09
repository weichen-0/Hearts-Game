//import org.junit.Before;
//import org.junit.Test;
//
//import java.hearts.util.ArrayList;
//import java.hearts.util.List;
//import static org.junit.Assert.*;
//
//public class ComPlayerTest {
//    @Test
//    public void choose3CardsToPass() {
//        hearts.model.ComPlayer player1 = new hearts.model.ComPlayer("Test");
//        hearts.model.Hand hand1 = player1.getHand();
//        List<hearts.model.Card> cardList = new ArrayList<>();
//        cardList.add(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.THREE));
//        cardList.add(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FOUR));
//        cardList.add(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FIVE));
//        cardList.add(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.TWO));
//        hand1.addCards(cardList);
//        List<hearts.model.Card> cardTestList = player1.choose3CardsToPass();
//        assertEquals(3, cardTestList.size()); //Test whether it remove 3 cards
//        assertEquals(cardTestList.get(2), hand1.getCardsSortedByRank().get(cardList.size() - 1)); //Test did it take our the biggest hearts.model.Rank
//    }
//
//    @Test
//    public void chooseCardToPlay() {
//        hearts.model.ComPlayer player1 = new hearts.model.ComPlayer("Test");
//        hearts.model.Hand hand1 = player1.getHand();
//        List<hearts.model.Card> cardList = new ArrayList<>();
//        cardList.add(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.THREE));
//        cardList.add(new hearts.model.Card(hearts.model.Suit.HEARTS, hearts.model.Rank.FIVE));
//        hand1.addCards(cardList);
//
//        hearts.model.Set round1 = new hearts.model.Set();
//        round1.addCardToSet(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.TWO),1);
//        round1.addCardToSet(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FOUR),1);
//
//        hearts.model.Set round2 = new hearts.model.Set();
//
//        hearts.model.Set round3 = new hearts.model.Set();
//        round1.addCardToSet(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.TWO),1);
//        round1.addCardToSet(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.FOUR),1);
//        round1.addCardToSet(new hearts.model.Card(hearts.model.Suit.SPADES, hearts.model.Rank.THREE),1);
//
//        assertEquals(player1.chooseCardToPlay(round3, true), cardList.get(0));
//        assertEquals(player1.chooseCardToPlay(round2, false), cardList.get(0));
//        assertEquals(player1.chooseCardToPlay(round1, false), cardList.get(0)); //Based on the set choose the same suit to play, smallest card
//    }
//
//}