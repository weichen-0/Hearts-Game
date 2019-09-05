package hearts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a set in a Hearts game.
 * A set includes 4 cards, 1 from each player and there are 13 sets in a round.
 * Has 2 default attributes, a list of cards and the index of player who last played a card.
 */
public class Set {

    // default instance variable for set
    // no constructor method required
    private List<Card> cards = new ArrayList<>();
    private int playerNumLastPlayed = -1;

    /**
     * Returns the list of cards currently in the set.
     * @return the list of cards in the set.
     */
    public List<Card> getSetCards() { return cards; }

    /**
     * Returns the index of the player who last played a card.
     * @return the index of last player.
     */
    public int getPlayerNumLastPlayed() {
        return playerNumLastPlayed;
    }

    /**
     * Returns the first card played in the set.
     * @return null if there are no cards in the set, otherwise return the first card played in the set
     */
    public Suit getLeadingSuit() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.get(0).getSuit();
    }

    /**
     * Returns the number of cards currently in the set.
     * @return the number of cards currently in the set.
     */
    public int getNumOfCardsInSet() {
        return cards.size();
    }

    /**
     * Adds a card played into the set.
     * @param cardPlayed the card played.
     * @param playerNum the index of the player who played the card.
     */
    public void addCardToSet(Card cardPlayed, int playerNum) {
        playerNumLastPlayed = playerNum;
        cards.add(cardPlayed);
    }

    /**
     * Returns the index of the card that wins the set.
     * @return -1 if there are no cards in the set.
     * otherwise, return the index of the winning card.
     */
    public int getWinningCardIndex() {
        if (getNumOfCardsInSet() == 0) {
            return -1;
        }
        int index = 0;
        Card highestCard = cards.get(0);

        for (int i = 1; i < cards.size(); i++) {
            Card current = cards.get(i);
            Suit currentSuit = current.getSuit();
            Suit highestCardSuit = highestCard.getSuit();

            if (highestCardSuit.compareTo(currentSuit) == 0 && highestCard.getRank().compareTo(current.getRank()) < 0) {
                highestCard = current;
                index = i;
            }
        }
        return index;
    }

    /**
     * Returns the Card object that wins the set.
     * @return null if there are no cards in the set, otherwise return the winning card.
     */
    public Card getWinningCard() {
        int index = getWinningCardIndex();
        if (index == -1) {
            return null;
        }
        return cards.get(index);
    }

    /**
     * Returns the total points of cards in the set.
     * @return the total points of cards in the set.
     */
    public int getTotalPointsInSet() {
        int setPoint = 0;
        for (Card card : cards) {
            setPoint += card.getPoint();
        }
        return setPoint;
    }

    /**
     * String representation of all cards in the set and the respective card points.
     * @return string representation of a set.
     */
    public String toString() {
        String output = "[";
        for (Card card : cards) {
            output += String.format("%s = %d, ", card, card.getPoint());
        }
        output = output.substring(0, output.length() - 2) + "]";
        return output;
    }
}
