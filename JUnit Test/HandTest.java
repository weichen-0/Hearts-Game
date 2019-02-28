import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {
    @Test
    public void testhand(){
        Hand hand1 = new Hand();
        int result = hand1.getNumberOfCards();
        //assertEquals(13, result);
    }


}