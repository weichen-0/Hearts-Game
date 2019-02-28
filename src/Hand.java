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

    private ArrayList<Card> hand = new ArrayList<>();


    /**
     * Adds a card to this hand.
     *
     * @param card card to be added to the current hand.
     */
    public void addCard(Card card) {
        hand.add(card);
        sortBySuit();
    }

    public void addCards(ArrayList<Card> cardList) {
        for (Card card : cardList) {
            hand.add(card);
        }
        sortBySuit();
    }

    public ArrayList<Card> getCardList() {
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

    public void removeCards(ArrayList<Card> cardList) {
        for (Card card : cardList) {
            removeCard(card);
        }
    }

    /**
     * Removes the card at the specified index from the hand.
     *
     * @param index poisition of the card to be removed.
     * @return the card removed from the hand, or the null reference if
     * the index is out of bounds.
     */
    public Card removeCard(int index) {
        return hand.remove(index);
    }


    /**
     * Removes all the cards from the hand, leaving an empty hand.
     */
    public void discardHand() {
        hand.clear();
    }


    /**
     * The number of cards held in the hand.
     *
     * @return number of cards currently held in the hand.
     */
    public int getNumberOfCards() {
        return hand.size();
    }


    /**
     * Sorts the card in the hand.
     * Sort is performed according to the order specified in the {@link Card} class.
     */
    public void sortBySuit() {
        Collections.sort(hand);
    }

    public ArrayList<Card> getCardsSortedByRank() {
        ArrayList<Card> cardList = new ArrayList<>(hand);
        Collections.sort(cardList, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                return card1.getRank().compareTo(card2.getRank());
            }
        });
        return new ArrayList<>(cardList);
    }


    /**
     * Checks to see if the hand is empty.
     *
     * @return <code>true</code> is the hand is empty.
     */
    public boolean isEmpty() {
        return hand.isEmpty();
    }


    /**
     * Determines whether or not the hand contains the specified card.
     *
     * @param card the card being searched for in the hand.
     * @return <code>true</code> if the card is present in the hand.
     */
    public boolean containsCard(Card card) {
        return hand.contains(card);
    }


    /**
     * Searches for the first instance of the specified card in the hand.
     *
     * @param card card being searched for.
     * @return position index of card if found, or <code>-1</code> if not found.
     */
    public int findCardIndex(Card card) {
        return hand.indexOf(card); //TODO: raise exception if -1
    }

    public Card findCard(Card card) {
        return hand.get(findCardIndex(card)); //TODO: raise exception if card doesn't exist
    }


    /**
     * Compares two hands.
     *
     * @param otherHandObject the hand being compared.
     * @return < 0 if this hand is less than the other hand, 0 if the two hands are
     * the same, or > 0 if this hand is greater then the other hand.
     */
//  public int compareTo(Object otherHandObject) {
//    Hand otherHand = (Hand) otherHandObject;
//    return evaluateHand() - otherHand.evaluateHand();
// }


    /**
     * Evaluates the hand.  Must be defined in the subclass that implements the hand
     * for the game being written by the client programmer.
     *
     * @return an integer corresponding to the rating of the hand.
     */

    //public abstract int evaluateHand();


    /**
     * o
     * Returns a description of the hand.
     *
     * @return a list of cards held in the hand.
     */
    public String toString() {
        String output = "";

        for (Card card : hand) {
            output += card.toString() + ", ";
        }

        return "[" + output.substring(0, output.length() - 2) + "]";
    }


    /**
     * Replaces the specified card with another card.  Only the first
     * instance of the targeted card is replaced.  No action occurs if
     * the targeted card is not present in the hand.
     *
     * @return <code>true</code> if the replacement occurs.
     */
    public boolean replaceCard(Card oldCard, Card replacementCard) {
        int location = findCardIndex(oldCard);
        if (location < 0)
            return false;
        hand.set(location, replacementCard);
        return true;
    }

    public boolean hasSuit(Suit suit) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getSuit().isEquals(suit)) {
                return true;
            }
        }
        return false;
    }

    public Card getNextHighestComCard(Suit leadingSuit, Card card, boolean isFirstSet) {
        // assume method only gets called by chooseCardToPlayer in Round class
        // only called in situations where player is non-first player

        if (hasSuit(leadingSuit)) return getNextHighestCard(leadingSuit, card);

        // returned card is of different suit from leadingSuit
        List<Card> cardsSortedByRank = getCardsSortedByRank();
        int totalSize = cardsSortedByRank.size();

        if(!isFirstSet) return cardsSortedByRank.get(totalSize - 1);

        for (int i = totalSize - 1; i >= 0; i--) {
            Card cardPlayed = cardsSortedByRank.get(i);
            if (!cardPlayed.isPointCard()) {
                return cardPlayed;
            }
        }
        return cardsSortedByRank.get(totalSize - 1);
    }

    public Card getSmallestComCard(boolean heartsBroken) {
        // assume method only gets called by chooseCardToPlayer in Round class
        // only called in situations where player is first player

        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO, null);
        if (containsCard(twoClubs)) return twoClubs;

        // if player's deck doesn't contain two clubs this is not the first set
        // any point card can be played, if heartsBroken == true
        List<Card> cardsSortedByRank = getCardsSortedByRank();
        if (heartsBroken) return cardsSortedByRank.get(0);

        for (Card card : cardsSortedByRank) {
            if (!card.getSuit().isEquals(Suit.HEARTS)) {
                return card;
            }
        }
        System.out.println("getSmallestComCard() returned null");
        return null;
    }


    public Card getNextHighestCard(Suit suit, Card highestCard) {
        // this method is only called if hand has at least one card with the same suit as highestCard
        Card smallestLegalCard = null;
        for (int i = hand.size() - 1; i >= 0; i--) {
            Card card = hand.get(i);
            if (!card.getSuit().isEquals(suit)) continue;
            if (highestCard == null || card.getRank().compareTo(highestCard.getRank()) < 0) return card;
            smallestLegalCard = card;
        }
        return smallestLegalCard;
    }
}