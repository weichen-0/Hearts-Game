import org.junit.Test;
import static org.junit.Assert.*;

public class CardRankComparatorTest {
    @Test
    public void compare(){
        CardRankComparator c = new CardRankComparator();
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO);
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        int result = c.compare(card2Hearts, card4Hearts);
        assertEquals(-2, result); //Test if same suit the difference rank

        Card card5Hearts = new Card(Suit.HEARTS, Rank.FIVE);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        int result1 = c.compare(card5Hearts, card5Spade);
        assertEquals(-1, result1); //Test if same rank the different suit
    }

}