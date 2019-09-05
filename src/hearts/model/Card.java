package hearts.model;

import java.awt.*;
import java.util.Objects;

/**
 * Representation of a single playing card. A card consists of a suit value
 * (e.g. hearts, spades), a rank value (e.g. ace, 7, king), and an image of
 * the front of the card.  A card object is immutable; once instantiated, the
 * values cannot change.
 *
 *
 */
public class Card {

    // instance variables for card
    private Suit suitValue;
    private Rank rankValue;
    private Image cardImage;
    private boolean isSelected = false;
    private int point;

    /**
     * Creates a new playing card.
     *
     * @param suitValue the suit value of this card.
     * @param rankValue the rank value of this card.
     */
    public Card(Suit suitValue, Rank rankValue) {
        this.cardImage = Toolkit.getDefaultToolkit().getImage("images/cards/" + rankValue.getSymbol() + suitValue.getSymbol() + ".gif");
        this.suitValue = suitValue;
        this.rankValue = rankValue;

        if (suitValue.compareTo(Suit.SPADES) == 0 && rankValue.compareTo(Rank.QUEEN) == 0) {
            this.point = 13;
        } else if (suitValue.compareTo(Suit.HEARTS) == 0) {
            this.point = 1;
        } else this.point = 0;
    }

    /**
     * Returns the image of the card.
     *
     * @return a gif image representing the card.
     */
    public Image getImage() {
        return cardImage;
    }

    /**
     * Returns the suit of the card.
     *
     * @return a Suit constant representing the suit value of the card.
     */
    public Suit getSuit() {
        return suitValue;
    }

    /**
     * Returns the rank of the card.
     *
     * @return a Rank constant representing the rank value of the card.
     */
    public Rank getRank() {
        return rankValue;
    }

    /**
     * Returns a description of this card.
     *
     * @return the name of the card.
     */
    public String toString() { return rankValue.toString() + " of " + suitValue.toString(); }

    /**
     * Compares two cards to determine if they have the same value.
     * This is not the same as the use of <code>equals</code> which compares
     * two objects for equality.
     *
     * @param cardObject the other card.
     * @return <code>true</code> if the two cards have the same rank and suit
     * values, <code>false</code> if they do not.
     */
    @Override
    public boolean equals(Object cardObject) {
        if (!(cardObject instanceof Card)) {
            return false;
        }
        Card card = (Card) cardObject;
        return rankValue == card.rankValue && suitValue == card.suitValue;
    }

    /**
     * Returns a hash code value for this Card.
     *
     * @return hash code value of this Card.
     */
    @Override
    public int hashCode() {
        return Objects.hash(suitValue, rankValue);
    }

    /**
     * Returns the point value for this Card.
     *
     * @return point value of this Card.
     */
    public int getPoint() {
        return point;
    }

    /**
     * Checks whether card is a point card.
     *
     * @return <code>true</code> if the card has points.
     * <code>false</code> if it does not.
     */
    public boolean isPointCard() { return point > 0; }

    /**
     * Checks whether card has been selected.
     *
     * @return <code>true</code> if the card is selected.
     * <code>false</code> if it is not.
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * If card has been selected, unselect it.
     * If card has not been selected, select it.
     */
    public void toggleSelected() {
        isSelected = !isSelected;
    }
}