import java.util.*;

public class Game {

    private Player[] listOfPlayers = new Player[4];
    private boolean isHeartsBroken = false;
    private int roundNum = 1;

    public Game(int numOfPlayers) {
        for (int i = 0; i < 4; i++) {
            if (i < numOfPlayers) {
                listOfPlayers[i] = new HumanPlayer("PLAYER" + (i + 1));
                continue;
            }
            listOfPlayers[i] = new ComPlayer("COM" + (i + 1 - numOfPlayers));
        }
    }

    public void initRound() {
        printOverallScoreBoard();

        System.out.printf("========== Start of Round %d ==========%n%n", roundNum);
        System.out.println("Dealing cards...");

        distributeCard();
        System.out.println("All players have been dealt 13 cards\n");
        printAllHands();

        return;
    }

    public int startSet(int setCount, int startPlayerIndex) {
        Set set = new Set();
        for (int i = startPlayerIndex; i < startPlayerIndex + 4; i++) {
            Player player = listOfPlayers[i % 4];
            System.out.printf("%nSET %d, CARD #%d%n", setCount, i - startPlayerIndex + 1);
            System.out.printf("%s, please select card to play%n", player.getName());

            boolean isValidCard = false;
            while (!isValidCard) {
                Card cardPlayed = chooseCardToPlay(player, set);
                System.out.printf("%s picked %s%n", player.getName(), cardPlayed);
                if (GameRegulator.cardPlayedIsValid(player, cardPlayed, set, isHeartsBroken)) {
                    isValidCard = true;
                    set.addCardToSet(cardPlayed);
                    if (cardPlayed.isPointCard()) isHeartsBroken = true;
                    player.getHand().removeCard(cardPlayed);
                }
            }
        }
        int winningPlayerIndex = (startPlayerIndex + set.getHighestCardIndex()) % 4;
        Player winningPlayerOfSet = listOfPlayers[winningPlayerIndex];
        System.out.printf("%nCards played this Set > %s%n", set);
        tallyPointsForSet(set, winningPlayerOfSet);
        printRoundScoreBoard();
        return winningPlayerIndex;
    }

    private void distributeCard() {
        Deck d = new Deck();
        d.shuffle();
        Player player = null;

        for (int i = 0; i < d.getSizeOfDeck(); i++) {
            Card currentCard = d.dealCard();

            do {
                int n = new Random().nextInt(4);
                player = listOfPlayers[n];
            } while (player.getHandSize() == 13);

            player.getHand().addCard(currentCard);
        }
    }

    private void printAllHands() {
        for (Player p : listOfPlayers) {
            printAlignedOptions(p.getHandSize());
            p.getHand().sortBySuit();
            if (p instanceof HumanPlayer) {
                System.out.printf("YOUR hand > %s%n", p.getHand());
            } else
                System.out.printf("%s hand > %s%n", p.getName(), p.getHand());
        }
    }

    private void printAlignedOptions(int numOfCards) {
        String output = "\t\t";

        for (int i = 1; i <= numOfCards; i++) {
            if (i > 10) {
                output += "\t[" + i + "]";
                continue;
            }
            output += "\t\t[" + i + "]";
        }
        System.out.println(output);
    }

    private void printOverallScoreBoard() {
        System.out.printf("[OVERALL SCOREBOARD]%n");
        for (Player p : listOfPlayers) {
            if (p instanceof HumanPlayer) {
                System.out.printf("YOUR score > %d%n", p.getTotalPoints());
            } else
                System.out.printf("%s score > %d%n", p.getName(), p.getTotalPoints());
        }
        System.out.println();
    }

    private void printRoundScoreBoard() {
        System.out.printf("[ROUND %d SCOREBOARD]%n", roundNum);
        for (Player p : listOfPlayers) {
            if (p instanceof HumanPlayer) {
                System.out.printf("YOUR score > %d%n", p.getPointsFromCurrentRound());
            } else
                System.out.printf("%s score > %d%n", p.getName(), p.getPointsFromCurrentRound());
        }
        System.out.println();
    }

    public int getHighestScore() {
        int highestScore = 0;

        for (Player p : listOfPlayers) {
            if (p.getTotalPoints() > highestScore) {
                highestScore = p.getTotalPoints();
            }
        }

        return highestScore;
    }

    public boolean passedCards() {
        int passOrder = roundNum % 4;

        if (passOrder == 0) {
            System.out.printf("No cards will be passed for Round %d%n%n", roundNum);
            return false;
        }

        // Ensure every player chooses cards to be removed first (so no card is passed twice)
        List<ArrayList<Card>> chosenCardsForEachPlayer = new ArrayList<>();
        for (Player player : listOfPlayers) {
            chosenCardsForEachPlayer.add(new ArrayList<>(player.choose3CardsToPass()));
        }

        for (int i = 0; i < listOfPlayers.length; i++) {
            Player passingPlayer = listOfPlayers[i];
            ArrayList<Card> cardsToPass = chosenCardsForEachPlayer.get(i);
            Player receivingPlayer = null;
            switch (passOrder) {
                case 1:
                    receivingPlayer = listOfPlayers[(i + 1) % 4];
                    break;
                case 2:
                    receivingPlayer = listOfPlayers[(i + 3) % 4];
                    break;
                case 3:
                    receivingPlayer = listOfPlayers[(i + 2) % 4];
            }
            transferCards(passingPlayer, receivingPlayer, cardsToPass);

            if (passingPlayer instanceof HumanPlayer || receivingPlayer instanceof HumanPlayer) {
                System.out.printf("%s passed 3 cards %s to %s%n", passingPlayer.getName(), cardsToPass.toString(), receivingPlayer.getName());
            }

        }
        System.out.printf("%nAll cards have been passed for Round %d%n%n", roundNum);
        System.out.println("Displaying everyone's hands...");
        printAllHands();
        return true;
    }

    private void transferCards(Player passingPlayer, Player receivingPlayer, ArrayList<Card> cardsToPass) {
        passingPlayer.getHand().removeCards(cardsToPass);
        receivingPlayer.getHand().addCards(cardsToPass);
    }

    private Card chooseCardToPlay(Player player, Set set) {
        int numCardsOnHand = player.getHand().getNumberOfCards();
        printAlignedOptions(numCardsOnHand);
        return player.chooseCardToPlay(set, isHeartsBroken);
    }

    private void tallyPointsForSet(Set set, Player player) {
        int pointsInSet = set.getTotalPointsInSet();
        System.out.printf("%s won this set and got %d points%n%n", player.getName(), pointsInSet);
        player.addToPointsFromCurrentRound(pointsInSet);
    }

    public void tallyPointsForRound() {
        System.out.printf("========== End of Round %d ==========%n%n", roundNum++);
        Player shootTheMoonPlayer = null;
        for(Player player : listOfPlayers){
            if(player.getPointsFromCurrentRound() == 26){
                shootTheMoonPlayer = player;
                break;
            }
        }

        for(Player player : listOfPlayers){
            if(shootTheMoonPlayer != null){
                if(player != shootTheMoonPlayer) {
                    player.addToTotalPoints(26);
                }
            }else{
                player.addToTotalPoints(player.getPointsFromCurrentRound());
            }
            player.resetPointsFromCurrentRound();
        }
    }

    public Player getWinner() {
        int lowestScore = Integer.MAX_VALUE;
        Player winner = null;

        for (Player p : listOfPlayers) {
            if (p.getTotalPoints() < lowestScore) {
                lowestScore = p.getTotalPoints();
                winner = p;
            }
        }
        return winner;
    }

    public Player[] getListOfPlayers(){
        return listOfPlayers;
    }

}