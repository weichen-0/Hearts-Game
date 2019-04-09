package hearts.model;

import hearts.util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Represents the basic functionality of a hand of cards.
 * Extensions of this class will provide the
 * definition of what constitutes a hand for that game and how hands are compared
 * to one another by overriding the <code>compareTo</code> method.
 *
 * @author John K. Estell
 * @version 1.0
 */
public class Hand {
    private List<Card> hand = new ArrayList<>();

    /**
     * Adds a card to this hand.
     * @param card card to be added to the current hand.
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Adds a list of cards to this hand.
     * @param cardList list of cards to be added to the current hand.
     */
    public void addCards(List<Card> cardList) {
        for (Card card : cardList) {
            hand.add(card);
        }
    }

    /**
     * Gets the list of cards in this hand.
     * @return list of cards in the this hand.
     */
    public List<Card> getCardList() {
        sortBySuit();
        return hand;
    }

    /**
     * Obtains the card stored at the specified location in the hand.  Does not
     * remove the card from the hand.
     *
     * @param index position of card to be accessed.
     * @return the card of interest, or the null reference if the index is out of
     * bounds.
     */
    public Card getCard(int index) {
        return hand.get(index);
    }

    /**
     * Removes the specified card from the current hand.
     *
     * @param card the card to be removed.
     * @return the card removed from the hand, or null if the card
     * was not present in the hand.
     */
    public Card removeCard(Card card) {
        int index = hand.indexOf(card);
        if (index < 0)
            return null;
        else
            return hand.remove(index);
    }

    /**
     * Removes a list of cards from the current hand.
     * @param cardList the list of cards to be removed
     */
    public void removeCards(List<Card> cardList) {
        for (Card card : cardList) {
            removeCard(card);
        }
    }

    /**
     * Removes the card at the specified index from the hand.
     * @param index position of the card to be removed.
     * @return the card removed from the hand, or the null reference if
     * the index is out of bounds.
     */
    public Card removeCard(int index) {
        return hand.remove(index);
    }


    /**
     * The number of cards held in the hand.
     * @return number of cards currently held in the hand.
     */
    public int getNumberOfCards() { return hand.size(); }


    /**
     * Sorts the card in the hand.
     * Sort is performed according to the order specified in the {@link Card} class.
     */
    public void sortBySuit() {
        Collections.sort(hand, new CardSuitComparator());
    }

    public List<Card> getCardsSortedByRank() {
        List<Card> tempHand = new ArrayList<>(hand);
        Collections.sort(tempHand, new CardRankComparator());
        return tempHand;
    }

    /**
     * Determines whether or not the hand contains the specified card.
     * @param card the card being searched for in the hand.
     * @return <code>true</code> if the card is present in the hand, else <code>false</code>
     */
    public boolean containsCard(Card card) {
        return hand.contains(card);
    }

    /**
     * Returns a description of the hand.
     * @return a list of cards held in the hand.
     */
    public String toString() {
        return hand.toString();
    }

    /**
     * Checks if the hand has any cards of the given suit
     * @param suit the suit being searched for in the hand
     * @return <code>true</code> if any card with the given suit is present in the hand,
     * otherwise return <code>false</code>
     */
    public boolean hasSuit(Suit suit) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getSuit().compareTo(suit) == 0) {
                return true;
            }
        }
        return false;
    }
}