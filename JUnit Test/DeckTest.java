import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void getSizeOfDeck() {
        Deck decktest = new Deck();
        assertEquals(52, decktest.getSizeOfDeck());
    }
    @Test
    public void addCard() {
        Deck decktest = new Deck();
        assertEquals(52, decktest.getSizeOfDeck());
        Card c1 = new Card(Suit.HEARTS, Rank.ACE);
        decktest.addCard(c1);
        assertEquals(53, decktest.getSizeOfDeck());
    }
    @Test
    public void dealCard() {
        Deck deckTest = new Deck();
        assertEquals(52,deckTest.getSizeOfDeck());

        while (!deckTest.isEmpty()) {
            deckTest.dealCard();
        }
        assertNull(deckTest.dealCard());
    }

    @Test
    public void getNumberOfCardsRemaining() {
        Deck deck = new Deck();
        assertEquals(52, deck.getSizeOfDeck());

        while (!deck.isEmpty()) {
            deck.dealCard();
        }

        assertEquals(0, deck.getNumberOfCardsRemaining());
    }
}