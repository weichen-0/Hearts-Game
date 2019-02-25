public class Player {

    private boolean isComputer;
    private int pointsFromCurrentRound = 0;
    private int totalPoints = 0;
    private Hand hand;

    public Player(boolean isComputer) {
        this.isComputer = isComputer;
        hand = new Hand();
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
}
