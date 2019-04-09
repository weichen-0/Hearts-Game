package hearts.model;

import java.util.Objects;

/**
 * Representation of Players in a Hearts game
 * hearts.model.Player name - provided in constructor parameter
 * 4 other default instance variables:
 * playedCard = null
 * pointsFromCurrentRound, totalPoints = 0
 * hand (list of cards)
 */
public class Player {

    private String name;
    private Card playedCard;
    private int pointsFromCurrentRound = 0;
    private int totalPoints = 0;
    private Hand hand = new Hand();

    /**
     * Constructs a player of the given name
     * @param name player name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Returns name of the player
     * @return player name
     */
    public String getName() {
      return name;
    }

    /**
     * Returns total points earned by player in current round
     * @return points earned from current round
     */
    public int getPointsFromCurrentRound() { return pointsFromCurrentRound; }

    /**
     * Returns total points earned by player in the game
     * @return points earned from current game
     */
    public int getTotalPoints() { return totalPoints; }

    /**
     * Adds points earned by player in a set into the total points in current round
     * @param points points earned in a set
     */
    public void addToPointsFromCurrentRound(int points) {  pointsFromCurrentRound += points; }

    /**
     * Adds points earned by player in a round into the total points in current game
     * @param points points earned in a round
     */
    public void addToTotalPoints(int points) { totalPoints += points; }

    /**
     * Resets points earned by player in a round to zero
     */
    public void resetPointsFromCurrentRound() { pointsFromCurrentRound = 0; }

    /**
     * Return the hand of the player
     * @return list of cards in player's hand
     */
    public Hand getHand() { return hand; }

    /**
     * Return the hand size of the player
     * @return the number of cards in player's hand
     */
    public int getHandSize() { return hand.getNumberOfCards(); }

    /**
     * Compares two players to determine if they have the same name.
     * This is not the same as the use of <code>equals</code> which compares
     * two objects for equality.
     *
     * @param playerObject the other card
     * @return <code>true</code> if the two players have the same name,
     * <code>false</code> if they do not.
     */
    @Override
    public boolean equals(Object playerObject) {
        if (!(playerObject instanceof Player)) {
            return false;
        }
        Player anotherPlayer = (Player) playerObject;
        return name == anotherPlayer.getName();
    }

    /**
     * Returns hash code value of player object
     * @return hash code value of player object
     */
    @Override
    public int hashCode() { return Objects.hash(name); }

    /**
     * Returns the card that was last played by player
     * @return last card played by player
     */
    public Card getPlayedCard(){ return playedCard; }

    /**
     * Sets the last card played by player to the given card
     * @param playedCard card that was just played by player
     */
    public void setPlayedCard(Card playedCard){
        this.playedCard = playedCard;
    }
}
