//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class PlayerTest {
//
//    @Test
//    public void getName() {
//        Player player1 = new Player("Player1", true);
//        assertEquals("Player1", player1.getName());
//    }
//    @Test
//    public void getIsPlayer() {
//        Player player1 = new Player("Player1", true);
//        assertTrue(player1.getIsPlayer());
//    }
//
//    @Test
//    public void resetPointsFromCurrentRound() {
//        Player player1 = new Player("Player1", true);
//        player1.resetPointsFromCurrentRound();
//        assertEquals(0,player1.getPointsFromCurrentRound());
//    }
//
//    @Test
//    public void addToPointsFromCurrentRound() {
//        Player player1 = new Player("Player1", true);
//        player1.addToPointsFromCurrentRound(10);
//        assertEquals(10,player1.getPointsFromCurrentRound());
//    }
//
//    @Test
//    public void addToTotalPoints() {
//        Player player1 = new Player("Player1", true);
//        player1.addToTotalPoints(20);
//        assertEquals(20, player1.getTotalPoints());
//    }
//
//    @Test
//    public void getHand() {
//        Player player1 = new Player("Player1", true);
//        Card c1 = new Card(Suit.SPADES, Rank.EIGHT, null);
//        player1.getHand().addCard(c1);
//        assertEquals(c1, player1.getHand().getCard(0));
//    }
//
//    @Test
//    public void getHandSize() {
//        Player player1 = new Player("Player1", true);
//        Card c1 = new Card(Suit.SPADES, Rank.EIGHT, null);
//        player1.getHand().addCard(c1);
//        assertEquals(1, player1.getHandSize());
//    }
//}
