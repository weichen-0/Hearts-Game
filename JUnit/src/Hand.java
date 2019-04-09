// Hand.java - John K. Estell - 8 May 2003
// last modified: 23 Febraury 2004
// Implementation of a abstract hand of playing cards.  
// Uses the Card class.  Requires subclass for specifying
// the specifics of what constitutes the evaluation of a hand 
// for the game being implemented.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
//public abstract class Hand implements Comparable {

public class Hand {
    private List<Card> hand = new ArrayList<>();

    /**
     * Adds a card to this hand.
     *
     * @param card card to be added to the current hand.
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Adds a list of cards to this hand.
     *
     * @param cardList list of cards to be added to the current hand.
     */
    public void addCards(List<Card> cardList) {
        for (Card card : cardList) {
            hand.add(card);
        }
    }

    /**
     * Gets the list of cards in this hand.
     *
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

    public void removeCards(List<Card> cardList) {
        for (Card card : cardList) {
            removeCard(card);
        }
    }

    /**
     * Removes the card at the specified index from the hand.
     *
     * @param index position of the card to be removed.
     * @return the card removed from the hand, or the null reference if
     * the index is out of bounds.
     */
    public Card removeCard(int index) {
        return hand.remove(index);
    }


//    /**
//     * Removes all the cards from the hand, leaving an empty hand.
//     */
//    public void discardHand() {
//        hand.clear();
//    }


    /**
     * The number of cards held in the hand.
     *
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


//    /**
//     * Checks to see if the hand is empty.
//     *
//     * @return <code>true</code> is the hand is empty.
//     */
//    public boolean isEmpty() {
//        return hand.isEmpty();
//    }


    /**
     * Determines whether or not the hand contains the specified card.
     *
     * @param card the card being searched for in the hand.
     * @return <code>true</code> if the card is present in the hand.
     */
    public boolean containsCard(Card card) {
        return hand.contains(card);
    }


//    /**
//     * Searches for the first instance of the specified card in the hand.
//     *
//     * @param card card being searched for.
//     * @return position index of card if found, or <code>-1</code> if not found.
//     */
//    public int findCardIndex(Card card) {
//        return hand.indexOf(card); //TODO: raise exception if -1
//    }

//    public Card findCard(Card card) {
//        return hand.get(findCardIndex(card)); //TODO: raise exception if card doesn't exist
//    }

//    /**
//     * Compares two hands.
//     *
//     * @param otherHandObject the hand being compared.
//     * @return < 0 if this hand is less than the other hand, 0 if the two hands are
//     * the same, or > 0 if this hand is greater then the other hand.
//     */
//  public int compareTo(Object otherHandObject) {
//    Hand otherHand = (Hand) otherHandObject;
//    return evaluateHand() - otherHand.evaluateHand();
// }


//    /**
//     * Evaluates the hand.  Must be defined in the subclass that implements the hand
//     * for the game being written by the client programmer.
//     *
//     * @return an integer corresponding to the rating of the hand.
//     */

//    public abstract int evaluateHand();


    /**
     * o
     * Returns a description of the hand.
     *
     * @return a list of cards held in the hand.
     */
    public String toString() {
        return hand.toString();
    }


//    /**
//     * Replaces the specified card with another card.  Only the first
//     * instance of the targeted card is replaced.  No action occurs if
//     * the targeted card is not present in the hand.
//     *
//     * @return <code>true</code> if the replacement occurs.
//     */
//    public boolean replaceCard(Card oldCard, Card replacementCard) {
//        int location = findCardIndex(oldCard);
//        if (location < 0)
//            return false;
//        hand.set(location, replacementCard);
//        return true;
//    }

    public boolean hasSuit(Suit suit) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getSuit().isEquals(suit)) {
                return true;
            }
        }
        return false;
    }
}