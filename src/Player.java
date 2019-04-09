import java.util.List;
import java.util.Objects;

/**
 * Representation of Players in a Hearts game
 *
 */
public abstract class Player {

    private String name;
    private Card playedCard;
    private int pointsFromCurrentRound = 0;
    private int totalPoints = 0;
    private Hand hand = new Hand();


    public Player(String name) {
    //    this.isPlayer = isPlayer;
        this.name = name;
    }

    public String getName() {
      return name;
    }

    //  public boolean getIsPlayer(){
    //    return isPlayer;
    //  }

    public int getPointsFromCurrentRound() { return pointsFromCurrentRound; }

    public int getTotalPoints() {
    return totalPoints;
    }

    public void addToPointsFromCurrentRound(int points) { // points from set
    pointsFromCurrentRound += points;
    }

    public void addToTotalPoints(int points) { // points from round
        totalPoints += points;
    }

    public void resetPointsFromCurrentRound() {
        pointsFromCurrentRound = 0;
    }

    //  public void tallyTotalPoints(int totalPoints) {
    //    this.totalPoints = totalPoints;
    //  }

    public Hand getHand() { return hand; }

    public int getHandSize() { return hand.getNumberOfCards(); }

    /**
     * Compares two players to determine if they have the same name.
     * This is not the same as the use of <code>equals</code> which compares
     * two objects for equality.
     *
     * @param playerObject the other card
     * @return <code>true</code> if the two players have the same name
     * values, <code>false</code> if they do not.
     */
    @Override
    public boolean equals(Object playerObject) {
        if (!(playerObject instanceof Player)) {
            return false;
        }
        Player anotherPlayer = (Player) playerObject;
        return name == anotherPlayer.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public abstract List<Card> choose3CardsToPass();

    public abstract Card chooseCardToPlay(Set set, boolean isHeartsBroken);

    public Card getPlayedCard(){
        return playedCard;
    }

    public void setPlayedCard(Card playedCard){
        this.playedCard = playedCard;
    }
}
