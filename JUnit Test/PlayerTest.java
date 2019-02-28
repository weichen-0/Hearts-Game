import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void Numberofcard(){
        Player player1 = new Player("Player1",true);
        Hand hand1 = new Hand();
        int numberofcard = hand1.getNumberOfCards();
        //assertEquals(13, numberofcard);
    }

}