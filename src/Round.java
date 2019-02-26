import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Round {

    // index 0 is always Human Player
    private static ArrayList<Player> listOfPlayers;
    private boolean heartsBroken;
    private int roundNum = 1;
    private int leadingPlayer;

    public Round() {
        listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new Player(false));
        listOfPlayers.add(new Player(true));
        listOfPlayers.add(new Player(true));
        listOfPlayers.add(new Player(true));
    }

    public int getRoundNum() {
        return roundNum;
    }

    public void nextRound() {
        roundNum++;
    }

    public int getHighestPoint() {
        int highestPoint = 0;

        for (Player p : listOfPlayers) {
            if (p.getTotalPoints() > highestPoint) {
                highestPoint = p.getTotalPoints();
            }
        }

        return highestPoint;
    }

    public void startRound() {
        Deck d = new Deck();
        d.shuffle();
        Player player = null;

        for (int i = 0; i < d.getDeck().size(); i++) {
            Card currentCard = d.getDeck().get(i);

            do {
                int n = new Random().nextInt(4);
                player = listOfPlayers.get(n);
            } while (player.getHandSize() == 13);

            player.getHand().addCard(currentCard);
        }

        System.out.printf("YOUR HAND> %s%n", listOfPlayers.get(0).getHand());

        for (int i = 1; i < listOfPlayers.size(); i++) {
            System.out.printf("COM%d HAND> %s%n", i, listOfPlayers.get(i).getHand());
        }
    }


    // INCOMPLETE
    public void exchangeCards() {
        HumanExchangeCards();

    }

    public void HumanExchangeCards() {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= 3; i++) {
            System.out.println("Select" + i + " card to be Pass");
            System.out.print("Select the Suite: ");
            String suit = sc.nextLine();
            System.out.print("Select the Rank: ");
            String rank = sc.nextLine();

        }
    }

    public void ComputerExchangeCards(ArrayList<Player> listOfPlayers, int roundNo) {
        for (int i = 1; i < listOfPlayers.size(); i++) {
            Player player = listOfPlayers.get(i);
            Hand hands = player.getHand();
            hands.sort();
            Player player2 = listOfPlayers.get((i + 1) % 4);
            Hand hands2 = player2.getHand();
            for (int t = hands.getNumberOfCards() - 1; t >= 10; t--) {
                Card currentCard = hands.getCard(t);
                hands.removeCard(currentCard);
                hands.addCard(currentCard);
            }
        }
    }


    public void tallyPoints(Set set, Player player1) {
        for (Player player: listOfPlayers){
            if (player == player1){
                player1.setPointsFromCurrentRound(set.getPoints());
            }
        }
    }

    public boolean hasGameEnded(){
        for (Player player: listOfPlayers){
            if (player.getTotalPoints() >= 100){
                return true;
            }
        }
        return false;
    }

}