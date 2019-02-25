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
    System.out.print("Select 1 card to be Pass");


  }

  public void ComputerExchangeCards(ArrayList<Player> listOfPlayers, int roundNo){
      for (int i = 1; i < listOfPlayers.size(); i++) {
          Player player = listOfPlayers.get(i);
          Hand hands = player.getHand();
          hands.sort();
          Player player2 = listOfPlayers.get((i+1)%4);
          Hand hands2 = player2.getHand();
              for (int t = hands.getNumberOfCards()-1; t >= 10; t--){
                  Card currentCard = hands.getCard(t);
                  hands.removeCard(currentCard);
                  hands.addCard(currentCard);
              }
          }
  }

  public void tallyPoints(int points, Player player) {

  }


}