package hearts.util;

import hearts.model.*;

import java.util.Comparator;

/**
 *  Class that compares cards by their suit followed by their rank.
 */
public class CardSuitComparator implements Comparator<Card> {

    /**
     * Compares 2 Card objects to see if first Card is less than, equal to,
     * or greater than the second Card based on suit followed by rank.
     *
     * @param card1 first Card object
     * @param card2 second Card object
     * @return suitDiff integer if Cards are of different suit
     * else rankDiff integer if Cards are of same suit
     */
    @Override
    public int compare(Card card1, Card card2) {
        int suitDiff = card1.getSuit().compareTo(card2.getSuit());
        int rankDiff = card1.getRank().compareTo(card2.getRank());
        if (suitDiff != 0)
            return suitDiff;
        else
            return rankDiff;
    }
}

