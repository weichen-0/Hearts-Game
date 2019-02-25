import java.util.ArrayList;
import java.util.Scanner;

public class Round {
  private static ArrayList<Player> listOfPlayers;
  private boolean heartsBroken;
  private int setNo;
  private int roundNo;
  private int leadingPlayer;

  public Round() {
    listOfPlayers = new ArrayList<>();
    listOfPlayers.add(new Player(false));
    listOfPlayers.add(new Player(true));
    listOfPlayers.add(new Player(true));
    listOfPlayers.add(new Player(true));
  }

  public void startRound() {

  }

  public void HumanExchangeCards() {
    Scanner sc = new Scanner(System.in);
    System.out.print("");

  }

  public void ComputerExchangeCards(){
      for (int i = 0; ) {
        if (player.isComputer()){
            Hand hands = player.getHand();
            hands.sort();
            for (int t = 0; i < 3; i++){
                hands.removeCard(i);
        }
      }
  }

  public void tallyPoints(int points, Player player) {

  }


}