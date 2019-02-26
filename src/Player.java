import java.util.ArrayList;

public class Player {

  private String name;
  private boolean isComputer;
  private int pointsFromCurrentRound = 0;
  private int totalPoints = 0;
  private Hand hand = new Hand();

  public Player(String name, boolean isComputer) {
    this.isComputer = isComputer;
    this.name = name;
  }

  public String getName() {
      return name;
  }

  public boolean isComputer(){
    return this.isComputer;
  }

  public int getPointsFromCurrentRound() {
    return pointsFromCurrentRound;
  }

  public void setPointsFromCurrentRound(int pointsFromCurrentRound) {
    this.pointsFromCurrentRound = pointsFromCurrentRound;
  }

  public int getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(int totalPoints) {
    this.totalPoints = totalPoints;
  }

  public Hand getHand() { return hand; }

  public void sortHand() { hand.sort(); }

  public int getHandSize() { return hand.getNumberOfCards(); }

}
