import org.junit.Test;

import static org.junit.Assert.*;

public class GameRegulatorTest {
    @Test
    public void getStartPlayerIndex(){
        GameRegulator regulatorTest = new GameRegulator();
        Game gametest = new Game(4);
        Player[] player = gametest.getListOfPlayers();
        Hand hand1 = player[0].getHand();
        Hand hand2 = player[1].getHand();
        Hand hand3 = player[2].getHand();
        Hand hand4 = player[3].getHand();

        hand1.addCard(new Card(Suit.DIAMONDS,Rank.TWO));
        hand2.addCard(new Card(Suit.HEARTS,Rank.TWO));
        hand3.addCard(new Card(Suit.CLUBS,Rank.TWO));
        hand4.addCard(new Card(Suit.SPADES,Rank.TWO));

        assertEquals(2, regulatorTest.getStartPlayerIndex(player)); //To test the function to locate the 2 clubs and return the player index
    }

}