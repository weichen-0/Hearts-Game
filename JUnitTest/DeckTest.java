import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;


public class DeckTest {
    private Deck decktest;

    @Before
    public void instantiateDeck(){
        decktest = new Deck();
    }

    @Test
    public void getSizeOfDeck() {
        assertEquals(52, decktest.getSizeOfDeck());
    }

    @Test
    public void deckAddCard(){
        List<Card> list = new ArrayList<>();
        list = decktest.getDeck();
        assertEquals(52, decktest.getSizeOfDeck());
        Card c1 = new Card(Suit.HEARTS, Rank.ACE);
        Card card = list.get(0);
        list.remove(card);
        assertEquals(51, list.size());
        decktest.addCard(c1);
        assertEquals(52, decktest.getSizeOfDeck());
    }

    @Test
    public void dealCard() {
        assertEquals(52,decktest.getSizeOfDeck());
        assertEquals(52, decktest.getSizeOfDeck());

        while (!decktest.isEmpty()) {
            decktest.dealCard();
        }
            assertNull(decktest.dealCard());
        }

        @Test
        public void getNumberOfCardsRemaining() {
            assertEquals(52, decktest.getSizeOfDeck());

            while (!decktest.isEmpty()) {
                decktest.dealCard();
                assertEquals(52, decktest.getSizeOfDeck());
                while (!decktest.isEmpty()) {
                    decktest.dealCard();
                }

                assertEquals(0, decktest.getNumberOfCardsRemaining());
                assertEquals(0, decktest.getNumberOfCardsRemaining());
            }
        }

    @Test
    public void restoreDeck() {
        decktest.restoreDeck();
        assertEquals(52, decktest.getSizeOfDeck());
    }
}