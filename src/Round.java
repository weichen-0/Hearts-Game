import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
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
        listOfPlayers.add(new Player("YOUR", true));
        listOfPlayers.add(new Player("COM1", false));
        listOfPlayers.add(new Player("COM2", false));
        listOfPlayers.add(new Player("COM3", false));
    }

    public void printScoreBoard() {
        System.out.printf("[CURRENT SCOREBOARD]%n");
        for (Player p : listOfPlayers) {
            System.out.printf("%s SCORE > %d%n", p.getName(), p.getTotalPoints());
        }
        System.out.println();
    }

    public int getPassOrder() {
        return roundNum % 4;
    }

    public void nextRound() {
        roundNum++;
    }

    public int getHighestScore() {
        int highestPoint = 0;

        for (Player p : listOfPlayers) {
            if (p.getTotalPoints() > highestPoint) {
                highestPoint = p.getTotalPoints();
            }
        }

        return highestPoint;
    }

    public void startRound() {
        printScoreBoard();

        System.out.printf("~~~ ROUND %d ~~~%n", roundNum);
        System.out.printf("Dealing cards...%n%n");

        distributeCard();
        printAllHands();

    }

    public void distributeCard() {
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
    }

    public void printAllHands() {
        printAlignedOptions();

        System.out.printf("YOUR HAND > %s%n", listOfPlayers.get(0).getHand());

        for (int i = 1; i < listOfPlayers.size(); i++) {
            System.out.printf("COM%d HAND > %s%n", i, listOfPlayers.get(i).getHand());
        }
    }

    public void printAlignedOptions() {
        String output = "              ";

        for (int i = 1; i <= 14 - roundNum; i++) {
            output += "[" + i + "]";

            if (i == 3 | i == 6) {
                output += "     ";

            } else output += "    ";
        }
        System.out.println(output);
    }

    // INCOMPLETE
    public void exchangeCards() {
        switch (getPassOrder()) {
            case 1:
                
        }

    }

    public ArrayList<Card> getPlayerExchangeCards() {

        String[] order = new String[]{"first", "second", "third"};
        ArrayList<Card> passList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            System.out.printf("Please select %s card to pass%n", order[i]);
            System.out.print("Input card index: ");
            passList.add(chooseCard());
        }

        return passList;
    }

    public Card chooseCard() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (sc.hasNextInt()) {
                int index = sc.nextInt();

                if (index > 0 && index < 14) {
                    Hand playerHand = listOfPlayers.get(0).getHand();
                    return playerHand.getCard(index - 1);
                } else {
                    System.out.println("Index must be between 1 and 13 (inclusive)");
                }

            } else System.out.println("Index must be an integer");
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

}