import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Round {

    // index 0 is always Human Player
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
        Deck d = new Deck();
        d.shuffle();
        Random random = new Random();
        int currentPlayer = 0;

        for (int i = 0; i < d.getDeck().size(); i++) {
            Card currentCard = (Card) d.getDeck().get(i);
            int n = random.nextInt(4);
            while (listOfPlayers.get(n).getHand().getNumberOfCards() == 13) {
                n = random.nextInt(4);
            }
            listOfPlayers.get(n).getHand().addCard(currentCard);
        }

        listOfPlayers.get(0).getHand().sort();
        System.out.println(listOfPlayers.get(0).getHand());

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