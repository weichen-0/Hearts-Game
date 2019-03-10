public class Player {

  private String name;
  private boolean isPlayer;
  private int pointsFromCurrentRound = 0;
  private int totalPoints = 0;
  private Hand hand = new Hand();

  public Player(String name, boolean isPlayer) {
    this.isPlayer = isPlayer;
    this.name = name;
  }

  public String getName() {
      return name;
  }

  public boolean getIsPlayer(){
    return isPlayer;
  }

  public int getPointsFromCurrentRound() { return pointsFromCurrentRound }

  public void resetPointsFromCurrentRound() {
    pointsFromCurrentRound = 0;
  }

  public void addToPointsFromCurrentRound(int points) { // points from set
    this.pointsFromCurrentRound += points;
  }

  public int getTotalPoints() {
    return totalPoints;
  }

  public void addToTotalPoints(int points) { // points from round
    this.totalPoints += points;
  }

  public void tallyTotalPoints(int totalPoints) {
    this.totalPoints = totalPoints;
  }

  public Hand getHand() { return hand; }

  public int getHandSize() { return hand.getNumberOfCards(); }

}
