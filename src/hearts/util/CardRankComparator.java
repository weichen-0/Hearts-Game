package hearts.util;

import hearts.model.*;

import java.util.Comparator;

/**
 *  Class that compares cards by their rank followed by their suit.
 */
public class CardRankComparator implements Comparator<Card> {

    /**
     * Compares 2 hearts.model.Card objects to see if first hearts.model.Card is less than, equal to,
     * or greater than the second hearts.model.Card based on rank followed by suit.
     *
     * @param card1 first hearts.model.Card object
     * @param card2 second hearts.model.Card object
     * @return rankDiff integer if Cards are of different rank
     * else suitDiff integer if Cards are of same rank
     */
    @Override
    public int compare(Card card1, Card card2) {
        int suitDiff = card1.getSuit().compareTo(card2.getSuit());
        int rankDiff = card1.getRank().compareTo(card2.getRank());
        if (rankDiff != 0)
            return rankDiff;
        else
            return suitDiff;
    }
}

