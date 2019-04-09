package hearts.model;

import hearts.util.*;
import hearts.exception.*;

import java.util.*;

/**
 * Class that manages the entire Hearts game, overseeing other classes such as Round, Set, Player, etc.
 * Keeps track of the current round and set number in the game, and also the cards in the current set
 * Accounts for the 4 players, whether hearts has been broken in each round and the index of the last player who won the set
 */
public class Game {

    private Player[] listOfPlayers = new Player[4];
    private boolean isHeartsBroken = false;
    private int roundNum = 1;
    private int setNum = 1;
    private boolean passedCards = false;
    private int lastWinningPlayerIndex;
    private Set currentSet = null;

    /**
     * Constructs a game and its 4 Players
     * @param numOfHumanPlayers number of human players playing the game
     */
    public Game(int numOfHumanPlayers) {
        for (int i = 0; i < 4; i++) {
            if (i < numOfHumanPlayers) {
                listOfPlayers[i] = new HumanPlayer("PLAYER" + (i + 1));
                continue;
            }
            listOfPlayers[i] = new ComPlayer("COM" + (i + 1 - numOfHumanPlayers));
        }
    }

    /**
     * Returns the current set being played in the Round
     * @return the playing set
     */
    public Set getCurrentSet(){
        return currentSet;
    }

    /**
     * Returns the list of players in the game.
     * @return players list
     */
    public Player[] getListOfPlayers(){
        return listOfPlayers;
    }

    /**
     * Resets the last played card of each Player to null
     */
    public void unsetPlayedCards(){
        for(Player p : listOfPlayers){
            p.setPlayedCard(null);
        }
    }

    /**
     * Initialises a round in the game and distributes 13 cards to each player
     */
    public void initRound() {
        currentSet = new Set();
        setNum = 1;
        printOverallScoreBoard();

        System.out.printf("========== Start of Round %d ==========%n%n", roundNum);
        System.out.println("Dealing cards...");

        distributeCard();
        System.out.println("All players have been dealt 13 cards\n");
        printAllHands();
    }

    /**
     *
     * @param cardPlayed
     * @throws IllegalMoveException
     */
    public void makePlayerMove(Card cardPlayed) throws IllegalMoveException {
        HumanPlayer player = null;
        for(Player p : listOfPlayers){
            if(p instanceof HumanPlayer){
                player = (HumanPlayer) p;
            }
        }
        System.out.printf("%nSET %d, CARD #%d%n", setNum, currentSet.getNumOfCardsInSet() + 1);
        System.out.print("\t");
        System.out.printf("%s Hand > %s%n", player.getName(), player.getHand());
        System.out.printf("Current Set > %s%n", currentSet.getSetCards());
        System.out.printf("PLAYER1 picked %s%n", cardPlayed);
        try{
            RuleEngine.validateCardPlayed(player, cardPlayed, currentSet, isHeartsBroken);
        }catch(IllegalMoveException e){
            player.deselectCardsInHand(); // if illegal move was played deselect all cards before propagating error up
            throw e;
        }
        currentSet.addCardToSet(cardPlayed, 0);
        player.setPlayedCard(cardPlayed);
        if (cardPlayed.isPointCard()) isHeartsBroken = true;
        player.getHand().removeCard(cardPlayed);
    }

    /**
     *
     * @throws UserMessageException
     */
    public void makeComputerMoves() throws UserMessageException {
        if(setNum > 13) { // end of round
            return;
        }
        int startPlayerIndex = (currentSet.getPlayerNumLastPlayed() + 1) % 4;
        if(currentSet.getNumOfCardsInSet() == 0){
            if(setNum == 1){
                startPlayerIndex = RuleEngine.getStartPlayerIndex(listOfPlayers); // two of clubs
            }else {
                startPlayerIndex = lastWinningPlayerIndex; // winner of previous set
            }
        }

        for (int i = startPlayerIndex; currentSet.getNumOfCardsInSet() < 4; i++) {
            if(i % 4 == 0){ //human player
                return;
            }
            System.out.printf("%nSET %d, CARD #%d%n", setNum, currentSet.getNumOfCardsInSet() + 1);
            ComPlayer comPlayer = (ComPlayer) listOfPlayers[i % 4];
            Card cardPlayed = comPlayer.chooseCardToPlay(currentSet, isHeartsBroken);
            currentSet.addCardToSet(cardPlayed, i % 4);
            if (cardPlayed.isPointCard()) isHeartsBroken = true;
            comPlayer.getHand().removeCard(cardPlayed);
        }

        int numCardsInSet = currentSet.getNumOfCardsInSet();
        if(numCardsInSet == 4){
            int winningPlayerIndex = (currentSet.getWinningCardIndex() + currentSet.getPlayerNumLastPlayed() + 1) % 4;
            Player winningPlayerOfSet = listOfPlayers[winningPlayerIndex];
            System.out.printf("%nCards played this hearts.model.Set > %s%n", currentSet);
            tallyPointsForSet(currentSet, winningPlayerOfSet);
            printRoundScoreBoard();
            lastWinningPlayerIndex = winningPlayerIndex;
            int totalPointsInSet = currentSet.getTotalPointsInSet();
            currentSet = new Set();
            setNum += 1;

            if (setNum == 14) {
                tallyPointsForRound();
                if(getHighestScore() < 100) {
                    roundNum += 1;
                    passedCards = (roundNum % 4 == 0);
                    String message = winningPlayerOfSet.getName() + " won this set. (" + totalPointsInSet + " Points)\n";
                    switch (roundNum % 4) {
                        case (0): message += "No passing of cards required for Round " + roundNum + "."; break;
                        case (1): message += "To start Round " + roundNum + ", select 3 cards to pass to the left."; break;
                        case (2): message += "To start Round " + roundNum + ", select 3 cards to pass to the right."; break;
                        case (3): message += "To start Round " + roundNum + ", select 3 cards to pass to opposite player.";
                    }
                    throw new UserMessageException(message, "End of Round");
                }
            }
            String err_msg = winningPlayerOfSet.getName() + " won this set. (" + totalPointsInSet + " Points)";
            String title = "End of Set";
            if (getHighestScore() > 100) {
                err_msg += "\n" + getWinner().getName() + " won the game!";
                title = "Game Ended";
            }
            throw new UserMessageException(err_msg, title);
        }
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
            p.getHand().sortBySuit();
            System.out.printf("%s Hand > %s%n", p.getName(), p.getHand());
        }
    }

    private void printOverallScoreBoard() {
        System.out.printf("[OVERALL SCOREBOARD]%n");
        for (Player p : listOfPlayers) {
            System.out.printf("%s Score > %d%n", p.getName(), p.getTotalPoints());
        }
        System.out.println();
    }

    private void printRoundScoreBoard() {
        System.out.printf("[ROUND %d SCOREBOARD]%n", roundNum);
        for (Player p : listOfPlayers) {
            System.out.printf("%s Score > %d%n", p.getName(), p.getPointsFromCurrentRound());
        }
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

    public boolean hasPassedCards(){
        return passedCards || (roundNum % 4 == 0);
    }

    public void passCards(List<Card> cards) {
        int passOrder = roundNum % 4;

        if (passOrder == 0) {
            System.out.printf("No cards will be passed for Round %d%n%n", roundNum);
            return;
        }

        // Ensure every player chooses cards to be removed first (so no card is passed twice)
        List<List<Card>> chosenCardsForEachPlayer = new ArrayList<>();
        for (Player player : listOfPlayers) {
            if(player instanceof ComPlayer){
                ComPlayer comPlayer = (ComPlayer) player;
                chosenCardsForEachPlayer.add(comPlayer.choose3CardsToPass());
            }else{
                chosenCardsForEachPlayer.add(cards); // human player inputs cards to pass
            }
        }

        for (int i = 0; i < listOfPlayers.length; i++) {
            Player passingPlayer = listOfPlayers[i];
            List<Card> cardsToPass = chosenCardsForEachPlayer.get(i);
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
        passedCards = true;
    }

    private void transferCards(Player passingPlayer, Player receivingPlayer, List<Card> cardsToPass) {
        passingPlayer.getHand().removeCards(cardsToPass);
        receivingPlayer.getHand().addCards(cardsToPass);
    }

    private void tallyPointsForSet(Set set, Player player) {
        int pointsInSet = set.getTotalPointsInSet();
        System.out.printf("%s won this set and got %d points%n%n", player.getName(), pointsInSet);
        player.addToPointsFromCurrentRound(pointsInSet);
    }

    public void tallyPointsForRound() {
        System.out.printf("========== End of Round %d ==========%n%n", roundNum);
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
}