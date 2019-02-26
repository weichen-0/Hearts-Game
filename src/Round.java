import java.sql.SQLOutput;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Round {

    // index 0 is always Human Player
    private static ArrayList<Player> listOfPlayers;
    private boolean heartsBroken;
    private int roundNum = 1;
    private int leadingPlayer;

    public Round() {
        listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new Player("PLAYER", true));
        listOfPlayers.add(new Player("COM1", false));
        listOfPlayers.add(new Player("COM2", false));
        listOfPlayers.add(new Player("COM3", false));
    }

    public void printScoreBoard() {
        System.out.printf("[CURRENT SCOREBOARD]%n");
        for (Player p : listOfPlayers) {
            if (p.getIsPlayer()) {
                System.out.printf("YOUR SCORE > %d%n", p.getTotalPoints());
            } else System.out.printf("%s SCORE > %d%n", p.getName(), p.getTotalPoints());
        }
        System.out.println();
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

        System.out.printf("--- ROUND %d ---%n", roundNum);
        System.out.printf("Dealing cards...%n%n");

        distributeCard();
        printAllHands();
        passCards();
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
        for (Player p : listOfPlayers) {
            if (p.getIsPlayer()) {
                System.out.printf("YOUR HAND > %s%n", p.getHand());
            } else System.out.printf("%s HAND > %s%n", p.getName(), p.getHand());
        }
    }

    public void printAlignedOptions() {
        String output = "              ";

        for (int i = 1; i <= 14 - roundNum; i++) {
            output += "[" + i + "]";

            if (i >= 10) {
                output += "   ";

            } else output += "    ";
        }
        System.out.println(output);
    }

    public void passCards() {
        int passOrder = roundNum % 4;

        if (passOrder == 0) return;

        // Ensure every player chooses cards to be removed first (so no card is passed twice)
        ArrayList<ArrayList<Card>> chosenCardsForEachPlayer = new ArrayList<>();
        for(Player player : listOfPlayers){
            chosenCardsForEachPlayer.add(choose3CardsToPass(player));
        }

        for(int i = 0; i < listOfPlayers.size(); i++){
            Player passingPlayer = listOfPlayers.get(i);
            ArrayList<Card> cardsToPass = chosenCardsForEachPlayer.get(i);
            Player receivingPlayer = null;
            switch (passOrder) {
                case 1:
                    receivingPlayer = listOfPlayers.get((i + 1) % 4);
                    break;
                case 2:
                    receivingPlayer = listOfPlayers.get((i + 3) % 4);
                    break;
                case 3:
                    receivingPlayer = listOfPlayers.get((i + 2) % 4);
            }
            transferCards(passingPlayer, receivingPlayer, cardsToPass);

            if (passingPlayer.getIsPlayer() || receivingPlayer.getIsPlayer()) {
                System.out.printf("%s passed 3 cards to %s - %s%n", passingPlayer.getName(), receivingPlayer.getName(),
                        cardsToPass.toString());
            }
        }
    }

    public void transferCards(Player passingPlayer, Player receivingPlayer, ArrayList<Card> cardsToPass) {
        passingPlayer.getHand().removeCards(cardsToPass);
        receivingPlayer.getHand().addCards(cardsToPass);
//        System.out.printf(passingPlayer.getName() + " passed 3 cards to " + receivingPlayer.getName() + ".");
//        System.out.println("The three cards are " + cardsToPass.toString());
//        printAllHands();
    }

    public ArrayList<Card> choose3CardsToPass(Player passingPlayer) {
        if (!passingPlayer.getIsPlayer()) {
            ArrayList<Card> cardList = new ArrayList<>(passingPlayer.getHand().getCardList());
            Collections.sort(cardList, new Comparator<Card>() {
                @Override
                public int compare(Card card1, Card card2) {
                    return -1 * card1.getRank().compareTo(card2.getRank());
                }
            });

            return new ArrayList<>(cardList.subList(0, 3));
        }

        Hand playerHand = passingPlayer.getHand();
        String[] order = new String[]{"first", "second", "third"};
        ArrayList<Card> passList = new ArrayList<>(); // Ints
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.printf("%nPlease select %s card to pass%n", order[i]);
            Card passCard = null;

            while (passCard == null) {
                System.out.print("Enter card index: ");
                int index = 0;

                try {
                    index = parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Index must be an integer");
                    continue;
                }

                if (index < 1 || index > 13) {
                    System.out.println("Index must be between 1 and 13 (inclusive)");
                    continue;
                }

                passCard = playerHand.getCard(index - 1);

                if (passList.contains(passCard)) {
                    System.out.println("Index has already been selected");
                    passCard = null;

                } else {
                    passList.add(passCard);
                }
            }
        }
        return passList;
    }

    public void tallyPoints(Set set, Player player1) {
        for (Player player: listOfPlayers){
            if (player == player1){
                player1.setPointsFromCurrentRound(set.getPoints());
            }
        }
    }

}