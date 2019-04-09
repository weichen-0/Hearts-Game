import java.util.*;

import hearts.controller.*;
import hearts.exception.*;
import hearts.GUI.*;
import hearts.model.*;
import hearts.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class AllTests {
    // attr. from DeskTest.java
    private Deck decktest;
    // attr. from GameTest.java
    private Game testGame;
    // attr. from HandTest.java
    private Hand handTest;
    // attr. from SetTest.java
    private Set setTest;

    // CardRankComparatorTest.java
    @Test
    public void compareRank() {
        CardRankComparator c = new CardRankComparator();
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO);
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        int result = c.compare(card2Hearts, card4Hearts);
        assertEquals(-2, result); // Test if same suit the difference rank

        Card card5Hearts = new Card(Suit.HEARTS, Rank.FIVE);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        int result1 = c.compare(card5Hearts, card5Spade);
        assertEquals(-1, result1); // Test if same rank the different suit
    }

    // CardSuitComparatorTest.java
    @Test
    public void compareSuit() {
        CardSuitComparator c = new CardSuitComparator();

        Card card5Hearts = new Card(Suit.HEARTS, Rank.FIVE);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        int result1 = c.compare(card5Hearts, card5Spade);
        assertEquals(-1, result1); // Test if same rank the different suit

        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO);
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        int result = c.compare(card2Hearts, card4Hearts);
        assertEquals(-2, result); // Test if same suit the difference rank
    }

    // CardTest.java
    @Test
    public void getSuit() {
        Card cardTest = new Card(Suit.SPADES, Rank.TWO);
        assertEquals(Suit.SPADES, cardTest.getSuit());
    }

    @Test
    public void getRank() {
        Card twoOfSpade = new Card(Suit.SPADES, Rank.TWO);
        assertEquals(Rank.TWO, twoOfSpade.getRank());
    }

    @Test
    public void equals() {
        Card twoOfSpade = new Card(Suit.SPADES, Rank.TWO);
        Card threeOfSpade = new Card(Suit.SPADES, Rank.TWO);
        assertTrue(twoOfSpade.equals(threeOfSpade));
    }

    @Test
    public void isPointCard() {
        Deck deckTester = new Deck();

        for (Card c : deckTester.getDeck()) {

            if (c.isPointCard()) {
                System.out.println(c + "\t" + c.getPoint());
            }

        }
    }

    // ComPlayerTest.java
    @Test
    public void choose3CardsToPass() {
        ComPlayer player1 = new ComPlayer("Test");
        Hand hand1 = player1.getHand();
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADES, Rank.THREE));
        cardList.add(new Card(Suit.SPADES, Rank.FOUR));
        cardList.add(new Card(Suit.SPADES, Rank.FIVE));
        cardList.add(new Card(Suit.SPADES, Rank.TWO));
        hand1.addCards(cardList);
        List<Card> cardTestList = player1.choose3CardsToPass();
        assertEquals(3, cardTestList.size()); // Test whether it remove 3 cards
        assertEquals(cardTestList.get(2), hand1.getCardsSortedByRank().get(cardList.size() - 1)); // Test did it take
                                                                                                  // our the biggest
                                                                                                  // Rank
    }

    @Test
    public void chooseCardToPlay() {
        ComPlayer player1 = new ComPlayer("Test");
        Hand hand1 = player1.getHand();
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADES, Rank.THREE));
        cardList.add(new Card(Suit.HEARTS, Rank.FIVE));
        hand1.addCards(cardList);

        Set round1 = new Set();
        round1.addCardToSet(new Card(Suit.SPADES, Rank.TWO), 1);
        round1.addCardToSet(new Card(Suit.SPADES, Rank.FOUR), 1);

        Set round2 = new Set();

        Set round3 = new Set();
        round1.addCardToSet(new Card(Suit.SPADES, Rank.TWO), 1);
        round1.addCardToSet(new Card(Suit.SPADES, Rank.FOUR), 1);
        round1.addCardToSet(new Card(Suit.SPADES, Rank.THREE), 1);

        assertEquals(player1.chooseCardToPlay(round3, true), cardList.get(0));
        assertEquals(player1.chooseCardToPlay(round2, false), cardList.get(0));
        assertEquals(player1.chooseCardToPlay(round1, false), cardList.get(0)); // Based on the set choose the same suit
                                                                                // to play, smallest card
    }

    // DeckTest.java
    @Before
    public void instantiateDeck() {
        decktest = new Deck();
    }

    @Test
    public void getSizeOfDeck() {
        assertEquals(52, decktest.getSizeOfDeck());
    }

    @Test
    public void deckAddCard() {
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
        assertEquals(52, decktest.getSizeOfDeck());
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

    // GameRegulatorTest.java
    @Test
    public void getStartPlayerIndex() {
        GameRegulator regulatorTest = new GameRegulator();
        Game gametest = new Game(4);
        Player[] player = gametest.getListOfPlayers();
        Hand hand1 = player[0].getHand();
        Hand hand2 = player[1].getHand();
        Hand hand3 = player[2].getHand();
        Hand hand4 = player[3].getHand();

        hand1.addCard(new Card(Suit.DIAMONDS, Rank.TWO));
        hand2.addCard(new Card(Suit.HEARTS, Rank.TWO));
        hand3.addCard(new Card(Suit.CLUBS, Rank.TWO));
        hand4.addCard(new Card(Suit.SPADES, Rank.TWO));

        assertEquals(2, regulatorTest.getStartPlayerIndex(player));
        // To test the function to locate the 2 clubs and return the player index
    }

    // GameTest.java
    @Before
    public void Instantiate() {
        testGame = new Game(4);
        // testGame.startRound();
    }

    @Test
    public void getHighestScore() {
        Player[] player = testGame.getListOfPlayers(); // Test function on getting highest score from all 4 players
        player[0].addToTotalPoints(50);
        player[1].addToTotalPoints(20);
        player[2].addToTotalPoints(10);
        player[3].addToTotalPoints(0);
        assertEquals(50, testGame.getHighestScore());
    }

    @Test
    public void getWinner() {
        Player[] player = testGame.getListOfPlayers(); // Test the function on getting the player with the lowest score
        player[0].addToTotalPoints(50);
        player[1].addToTotalPoints(20);
        player[2].addToTotalPoints(10);
        player[3].addToTotalPoints(0);
        assertEquals(player[3].getName(), testGame.getWinner().getName());
    }

    @Test
    public void tallyPointsForRound() {
        Player[] player = testGame.getListOfPlayers();
        player[0].addToPointsFromCurrentRound(26);
        testGame.tallyPointsForRound();
        assertEquals(26, player[1].getTotalPoints());
        // if shoot the moon the other player will add 26
        assertEquals(0, player[1].getPointsFromCurrentRound());
        // reset round score
    }

    @Test
    public void passCards() {
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
        assertEquals(13, player[0].getHandSize());
        // check after passing card did player receive back the same number of card
    }

    // HandTest.java
    @Before
    public void instantiateHand() {
        handTest = new Hand();
    }

    @Test
    public void handAddCard() {
        assertEquals(0, handTest.getNumberOfCards()); // Hand start off with 0 card test the Add method, (1 card at a
                                                      // go)
        Card cardAHearts = new Card(Suit.HEARTS, Rank.ACE);
        handTest.addCard(cardAHearts);
        assertEquals(1, handTest.getNumberOfCards());
    }

    @Test
    public void addCards() {
        assertEquals(0, handTest.getNumberOfCards()); // Hand start off with 0 card test the Add method, (>1 card at a
                                                      // go)
        List<Card> list = new Deck().getDeck();
        handTest.addCards(list);
        assertEquals(52, handTest.getNumberOfCards());
    }

    @Test
    public void getCard() {
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO); // Test method to get a card from the hand
        Card card4Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        handTest.addCard(card2Hearts);
        handTest.addCard(card4Hearts);
        assertEquals(card2Hearts, handTest.getCard(0));
    }

    @Test
    public void removeCard() {
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO); // Test method to remove a card that is being specify
        handTest.addCard(card2Hearts);
        assertEquals(1, handTest.getNumberOfCards());
        handTest.removeCard(card2Hearts);
        assertEquals(0, handTest.getNumberOfCards());
    }

    @Test
    public void removeCards() {
        assertEquals(0, handTest.getNumberOfCards()); // Method will remove a list of cards from the Hand
        List<Card> list = new Deck().getDeck();
        handTest.addCards(list);
        assertEquals(52, handTest.getNumberOfCards());

        List<Card> cardsToRemove = new ArrayList<>();
        cardsToRemove.add(new Card(Suit.HEARTS, Rank.TWO));
        cardsToRemove.add(new Card(Suit.CLUBS, Rank.TWO));
        cardsToRemove.add(new Card(Suit.SPADES, Rank.TWO));
        handTest.removeCards(cardsToRemove); // remove 3 cards

        assertEquals(49, handTest.getNumberOfCards());
    }

    @Test
    public void removeCardByIndex() {
        ArrayList<Card> cardsToRemove = new ArrayList<>();
        cardsToRemove.add(new Card(Suit.HEARTS, Rank.TWO));
        cardsToRemove.add(new Card(Suit.CLUBS, Rank.TWO));
        cardsToRemove.add(new Card(Suit.SPADES, Rank.TWO));
        handTest.addCards(cardsToRemove);

        // for (Card c: handTest.getCardList()) {
        for (int i = 0; i < handTest.getCardList().size(); i++) {
            System.out.println("index : " + i + " -> " + handTest.getCard(i));
        }
        System.out.println("=== index 2 removed ===");

        handTest.removeCard(2);
        for (int i = 0; i < handTest.getCardList().size(); i++) {
            System.out.println("index : " + i + " -> " + handTest.getCard(i));
        }
    }

    @Test
    public void containsCard() {
        ArrayList<Card> listOfCards = new ArrayList<>(); // Add a list of card into a hand
        listOfCards.add(new Card(Suit.SPADES, Rank.THREE));
        listOfCards.add(new Card(Suit.SPADES, Rank.FOUR));
        listOfCards.add(new Card(Suit.HEARTS, Rank.TWO));
        listOfCards.add(new Card(Suit.CLUBS, Rank.TWO));
        listOfCards.add(new Card(Suit.SPADES, Rank.TWO));
        handTest.addCards(listOfCards);
        Card card2Hearts = new Card(Suit.HEARTS, Rank.TWO); // Check if the hand contains the card
        assertTrue(handTest.containsCard(card2Hearts));
    }

    @Test
    public void hasSuit() {
        Card cardAHearts = new Card(Suit.HEARTS, Rank.ACE);
        handTest.addCard(cardAHearts);
        assertTrue(handTest.hasSuit(Suit.HEARTS));
    }

    // SetTest.java
    @Before
    public void instantiateSet() {
        setTest = new Set();
    }

    @Test
    public void getSetCards() {
        Card card2Spade = new Card(Suit.SPADES, Rank.TWO);
        Card card3Hearts = new Card(Suit.HEARTS, Rank.THREE);
        setTest.addCardToSet(card2Spade, 1);
        setTest.addCardToSet(card3Hearts, 2);
        List<Card> test = setTest.getSetCards();
        assertEquals(2, test.size()); // Test if it is able to retrieve all the cards in the set
    }

    @Test
    public void getLeadingSuit() {
        Card card2Spade = new Card(Suit.SPADES, Rank.TWO);
        Card card3Hearts = new Card(Suit.HEARTS, Rank.THREE);
        setTest.addCardToSet(card2Spade, 1);
        setTest.addCardToSet(card3Hearts, 2);
        assertEquals(Suit.SPADES, setTest.getLeadingSuit());
    }

    @Test
    public void addCardToSet() {
        assertEquals(0, setTest.getNumOfCardsInSet()); // The initial set got 0 cards
        Card card2Spade = new Card(Suit.SPADES, Rank.TWO);
        setTest.addCardToSet(card2Spade, 1);
        assertEquals(1, setTest.getNumOfCardsInSet()); // Test the function if the number of cards had been added
                                                       // successfully
    }

    @Test
    public void getHighestCardIndex() {
        Card card2Spade = new Card(Suit.SPADES, Rank.TWO);
        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
        Card card4Spade = new Card(Suit.SPADES, Rank.FOUR);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        setTest.addCardToSet(card2Spade, 1);
        setTest.addCardToSet(card3Spade, 2);
        setTest.addCardToSet(card4Spade, 3);
        setTest.addCardToSet(card5Spade, 4);

        assertEquals(3, setTest.getHighestCardIndex()); // Test the highest card index from the set
    }

    @Test
    public void getWinningCard() {
        Card card2Spade = new Card(Suit.SPADES, Rank.TWO);
        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
        Card card4Spade = new Card(Suit.SPADES, Rank.FOUR);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        setTest.addCardToSet(card2Spade, 1);
        setTest.addCardToSet(card3Spade, 2);
        setTest.addCardToSet(card4Spade, 3);
        setTest.addCardToSet(card5Spade, 4);

        assertEquals(card5Spade, setTest.getWinningCard());
    }

    @Test
    public void getWinningPlayerIndex() {
        Card card2Spade = new Card(Suit.SPADES, Rank.TWO);
        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
        Card card4Spade = new Card(Suit.SPADES, Rank.FOUR);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        setTest.addCardToSet(card2Spade, 1);
        setTest.addCardToSet(card3Spade, 2);
        setTest.addCardToSet(card4Spade, 3);
        setTest.addCardToSet(card5Spade, 4);

        assertEquals(0, setTest.getWinningPlayerIndex());
    }

    @Test
    public void getTotalPointInSet() {
        Card card2Spade = new Card(Suit.SPADES, Rank.TWO);
        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
        Card card3Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        setTest.addCardToSet(card2Spade, 1);
        setTest.addCardToSet(card3Spade, 2);
        setTest.addCardToSet(card3Hearts, 3);
        setTest.addCardToSet(card5Spade, 4);

        int total = 0;
        total += card2Spade.getPoint();
        total += card3Spade.getPoint();
        total += card3Hearts.getPoint();
        total += card5Spade.getPoint();

        assertEquals(1, setTest.getTotalPointsInSet());
    }

    @Test
    public void getPlayerNumLastPlayed() {
        Card card2Spade = new Card(Suit.SPADES, Rank.TWO);
        Card card3Spade = new Card(Suit.SPADES, Rank.THREE);
        Card card3Hearts = new Card(Suit.HEARTS, Rank.FOUR);
        Card card5Spade = new Card(Suit.SPADES, Rank.FIVE);
        setTest.addCardToSet(card2Spade, 1);
        setTest.addCardToSet(card5Spade, 4);
        setTest.addCardToSet(card3Spade, 2);
        setTest.addCardToSet(card3Hearts, 3);

        assertEquals(3, setTest.getPlayerNumLastPlayed());
    }

}