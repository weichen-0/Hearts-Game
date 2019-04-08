import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private Game testGame;

    @Before
    public void Instantiate(){
        testGame = new Game(4);
        testGame.startRound();
    }
    @Test
    public void startRound(){
        Player[] player = testGame.getListOfPlayers();
        assertEquals(13, player[0].getHandSize()); //Test the distribution of cards
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








}