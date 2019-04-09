import java.util.*;

public class Game {

    private Player[] listOfPlayers = new Player[4];
    private boolean isHeartsBroken = false;
    private int roundNum = 1;
    private int setNum = 1;
    private boolean passedCards = (roundNum % 4 == 0);
    private int lastWinningPlayerIndex;
    private Set currentSet = null;

    public Game(int numOfPlayers) {
        for (int i = 0; i < 4; i++) {
            if (i < numOfPlayers) {
                listOfPlayers[i] = new HumanPlayer("PLAYER" + (i + 1));
                continue;
            }
            listOfPlayers[i] = new ComPlayer("COM" + (i + 1 - numOfPlayers));
        }
    }

    public Set getCurrentSet(){
        return currentSet;
    }

    public void unsetPlayedCards(){
        for(Player p : listOfPlayers){
            p.setPlayedCard(null);
        }
    }

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

    public void makePlayerMove(Card cardPlayed) throws IllegalMoveException{ // int startPlayerIndex) {
        Player player = listOfPlayers[0]; //human player
        System.out.printf("%nSET %d, CARD #%d%n", setNum, currentSet.getNumOfCardsInSet() + 1);
        System.out.print("\t");
//        printAlignedOptions(player.getHand().getNumberOfCards());
        System.out.printf("%s Hand > %s%n", player.getName(), player.getHand());
        System.out.printf("Current Set contains > %s%n", currentSet.getSetCards());
        System.out.printf("PLAYER1 picked %s%n", cardPlayed);
        GameRegulator.validateCardPlayed(player, cardPlayed, currentSet, isHeartsBroken);
        currentSet.addCardToSet(cardPlayed, 0);
        player.setPlayedCard(cardPlayed);
        if (cardPlayed.isPointCard()) isHeartsBroken = true;
        player.getHand().removeCard(cardPlayed);
    }

    public void makeComputerMoves() throws UserMessageException{
        if(setNum > 13){ // end of round
            return;
        }
        int startPlayerIndex = (currentSet.getPlayerNumLastPlayed() + 1) % 4;
        if(currentSet.getNumOfCardsInSet() == 0){
            if(setNum == 1){
                startPlayerIndex = GameRegulator.getStartPlayerIndex(listOfPlayers); // two of clubs
            }else {
                startPlayerIndex = lastWinningPlayerIndex; // winner of previous set
            }
        }

        for (int i = startPlayerIndex; currentSet.getNumOfCardsInSet() < 4; i++) {
            if(i % 4 == 0){ //human player
//                throw new UserMessageException("Your Turn.", "");
                return;
            }
            System.out.printf("%nSET %d, CARD #%d%n", setNum, currentSet.getNumOfCardsInSet() + 1);
            Player player = listOfPlayers[i % 4];
            Card cardPlayed = chooseCardToPlay(player, currentSet);
            currentSet.addCardToSet(cardPlayed, i % 4);
            if (cardPlayed.isPointCard()) isHeartsBroken = true;
            player.getHand().removeCard(cardPlayed);
        }
        int numCardsInSet = currentSet.getNumOfCardsInSet();
        if(numCardsInSet == 4){
            int winningPlayerIndex = currentSet.getWinningPlayerIndex();
            Player winningPlayerOfSet = listOfPlayers[winningPlayerIndex];
            System.out.printf("%nCards played this Set > %s%n", currentSet);
            tallyPointsForSet(currentSet, winningPlayerOfSet);
            printRoundScoreBoard();
            lastWinningPlayerIndex = winningPlayerIndex;
            int totalPointsInSet = currentSet.getTotalPointsInSet();
            currentSet = new Set();
            setNum += 1;

            if (setNum == 14) {
                tallyPointsForRound();
                if(getHighestScore() < 100) {
                    roundNum += 1; //TODO check if addition at this loc is correct
                    passedCards = (roundNum % 4 == 0);
                    String message = winningPlayerOfSet.getName() + " won this set. ";
                    if(roundNum % 4 == 0) {
                        message += "No passing of cards required for the next round.";
                    }else{
                        message += "To start the next round, select 3 cards to pass.";
                    }
                    throw new UserMessageException(message, "End of Round");
                }
            }

            throw new UserMessageException(winningPlayerOfSet.getName() + " won this set. (" + totalPointsInSet + " Points)", "End of Set");
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
//            printAlignedOptions(p.getHandSize());
            p.getHand().sortBySuit();
            if (p instanceof HumanPlayer) {
                System.out.printf("YOUR hand > %s%n", p.getHand());
            } else
                System.out.printf("%s hand > %s%n", p.getName(), p.getHand());
        }
    }

//    private void printAlignedOptions(int numOfCards) {
//        String output = "\t\t";
//
//        for (int i = 1; i <= numOfCards; i++) {
//            if (i > 10) {
//                output += "\t[" + i + "]";
//                continue;
//            }
//            output += "\t\t[" + i + "]";
//        }
//        System.out.println(output);
//    }

    private void printOverallScoreBoard() {
        System.out.printf("[OVERALL SCOREBOARD]%n");
        for (Player p : listOfPlayers) {
            System.out.printf("%s score > %d%n", p.getName(), p.getTotalPoints());
        }
        System.out.println();
    }

    private void printRoundScoreBoard() {
        System.out.printf("[ROUND %d SCOREBOARD]%n", roundNum);
        for (Player p : listOfPlayers) {
            System.out.printf("%s score > %d%n", p.getName(), p.getPointsFromCurrentRound());
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
        return passedCards;
    }

    public void passCards(List<Card> cards) {
        int passOrder = roundNum % 4;

        if (passOrder == 0) {
            System.out.printf("No cards will be passed for Round %d%n%n", roundNum);
        }

        // Ensure every player chooses cards to be removed first (so no card is passed twice)
        List<List<Card>> chosenCardsForEachPlayer = new ArrayList<>();
        for (Player player : listOfPlayers) {
            if(player instanceof ComPlayer){
                chosenCardsForEachPlayer.add(player.choose3CardsToPass());
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

    private Card chooseCardToPlay(Player player, Set set) {
        int numCardsOnHand = player.getHand().getNumberOfCards();
//        printAlignedOptions(numCardsOnHand);
        return player.chooseCardToPlay(set, isHeartsBroken);
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

    public Player[] getListOfPlayers(){
        return listOfPlayers;
    }

}