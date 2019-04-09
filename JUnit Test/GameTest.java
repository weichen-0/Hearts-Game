import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

import hearts.model.*;

public class GameTest {
    private Game testGame;

    @Before
    public void Instantiate(){
        testGame = new Game(4);
        //testGame.startRound();
    }

    @Test
    public void getHighestScore(){
        Player[] player = testGame.getListOfPlayers(); //Test function on getting highest score from all 4 players
        player[0].addToTotalPoints(50);
        player[1].addToTotalPoints(20);
        player[2].addToTotalPoints(10);
        player[3].addToTotalPoints(0);
        assertEquals(50, testGame.getHighestScore());
    }

    @Test
    public void getWinner(){
        Player[] player = testGame.getListOfPlayers(); //Test the function on getting the player with the lowest score
        player[0].addToTotalPoints(50);
        player[1].addToTotalPoints(20);
        player[2].addToTotalPoints(10);
        player[3].addToTotalPoints(0);
        assertEquals(player[3].getName(), testGame.getWinner().getName());
    }

    @Test
    public void tallyPointsForRound(){
        Player[] player = testGame.getListOfPlayers();
        player[0].addToPointsFromCurrentRound(26);
        testGame.tallyPointsForRound();
        assertEquals(26, player[1].getTotalPoints()); //if shoot the moon the other player will add 26
        assertEquals(0, player[1].getPointsFromCurrentRound()); //reset round score
    }

    @Test
    public void passCards(){
        Player[] player = testGame.getListOfPlayers();
        Hand hand1 = player[0].getHand();
        List<Card> hand1CardList = new ArrayList<>();
        hand1CardList.add(new Card(Suit.HEARTS, Rank.ACE));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.TWO));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.THREE));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.FOUR));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.FIVE));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.SIX));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.SEVEN));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.EIGHT));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.NINE));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.TEN));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.JACK));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.QUEEN));
        hand1CardList.add(new Card(Suit.HEARTS, Rank.KING));
        hand1.addCards(hand1CardList);
        List<Card> pass1round = new ArrayList<>();
        pass1round.add(new Card(Suit.HEARTS, Rank.ACE));
        pass1round.add(new Card(Suit.HEARTS, Rank.JACK));
        pass1round.add(new Card(Suit.HEARTS, Rank.KING));
        assertEquals(13, player[0].getHandSize()); //check after passing card did player receive back the same number of card
    }









}