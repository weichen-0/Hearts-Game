import java.util.*;

import static java.lang.Integer.parseInt;

public class Round {

    // index 0 is always Human Player
    private static ArrayList<Player> listOfPlayers;
    private boolean isHeartsBroken = false;
    private int roundNum = 1;

    public Round() {
        listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new Player("COM1", false));
        listOfPlayers.add(new Player("COM2", false));
        listOfPlayers.add(new Player("COM3", false));
        listOfPlayers.add(new Player("PLAYER", true));
        System.out.println();
    }

    public void printScoreBoard() {
        System.out.printf("[OVERALL SCOREBOARD]%n");
        for (Player p : listOfPlayers) {
            if (p.getIsPlayer()) {
                System.out.printf("YOUR score > %d%n", p.getTotalPoints());
            } else
                System.out.printf("%s score > %d%n", p.getName(), p.getTotalPoints());
        }
        System.out.println();
    }

    public void printRoundScoreBoard() {
        System.out.printf("[ROUND %d SCOREBOARD]%n", roundNum);
        for (Player p : listOfPlayers) {
            if (p.getIsPlayer()) {
                System.out.printf("YOUR score > %d%n", p.getPointsFromCurrentRound());
            } else
                System.out.printf("%s score > %d%n", p.getName(), p.getPointsFromCurrentRound());
        }
        System.out.println();
    }

    public void nextRound() {
        roundNum++;
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

    public void startRound() {
        printScoreBoard();

        System.out.printf("--- ROUND %d ---%n", roundNum);
        System.out.printf("Dealing cards...%n");

        distributeCard();
        System.out.printf("All players have been dealt 13 cards%n%n");
        printAllHands();
        passCards();

        System.out.println("Displaying everyone's hands...");
        printAllHands();

        //first round logic: decide first player
        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO, null); // to update image
        int startPlayerIndex = 0;
        for (; startPlayerIndex < 4; startPlayerIndex++) {
            Hand playerHand = listOfPlayers.get(startPlayerIndex).getHand();
            if (playerHand.containsCard(twoClubs)) {
                break;
            }
        }

        for (int setCount = 1; setCount <= 13; setCount++) {
            Set set = new Set();
            for (int i = startPlayerIndex; i < startPlayerIndex + 4; i++) {
                Player player = listOfPlayers.get(i % 4);
                System.out.printf("%nSET %d, CARD #%d%n", setCount, i-startPlayerIndex+1);
                System.out.printf("%s, please select card to play%n", player.getName());

                boolean cardPlayedIsValid = false;
                while (!cardPlayedIsValid) {
                    Card cardPlayed = chooseCardToPlay(player, set);
                    System.out.printf("%s picked %s%n", player.getName(), cardPlayed);
                    if (set.addCardToSet(player, cardPlayed, isHeartsBroken)) {
                        cardPlayedIsValid = true;
                        if (cardPlayed.isPointCard()) isHeartsBroken = true;
                        player.getHand().removeCard(cardPlayed);
                    }else{
                        System.out.printf("Invalid card played. Try again %n");
                    }
                }
            }
            startPlayerIndex = (startPlayerIndex + set.getHighestCardIndex()) % 4;
            Player winningPlayerOfSet = listOfPlayers.get(startPlayerIndex);
            set.printCardsInSet();
            tallyPointsForSet(set, winningPlayerOfSet);
            printRoundScoreBoard();
        }
        System.out.printf("========== End of Round %d ==========%n%n", roundNum);
        tallyPointsForRound();
        roundNum++;
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
        for (Player p : listOfPlayers) {
            printAlignedOptions(p.getHandSize());
            if (p.getIsPlayer()) {
                System.out.printf("YOUR hand > %s%n", p.getHand());
            } else
                System.out.printf("%s hand > %s%n", p.getName(), p.getHand());
        }
    }

    public void printAlignedOptions(int numOfCards) {
        String output = "                ";

        for (int i = 1; i <= numOfCards; i++) {
            output += "[" + i + "]  ";

            if (i >= 11) {
                output += "   ";
            } else if (i % 5 == 0) {
                output += "     ";

            } else output += "    ";
        }
        System.out.println(output);
    }

    public void passCards() {
        int passOrder = roundNum % 4;

        if (passOrder == 0) {
            System.out.printf("No cards will be passed for Round %d%n%n", roundNum);
            return;
        }

        // Ensure every player chooses cards to be removed first (so no card is passed twice)
        ArrayList<ArrayList<Card>> chosenCardsForEachPlayer = new ArrayList<>();
        for (Player player : listOfPlayers) {
            chosenCardsForEachPlayer.add(choose3CardsToPass(player));
        }

        for (int i = 0; i < listOfPlayers.size(); i++) {
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
                System.out.printf("%s passed 3 cards %s to %s%n", passingPlayer.getName(), cardsToPass.toString(), receivingPlayer.getName());
            }

        }
        System.out.printf("%nAll cards have been passed for Round %d%n%n", roundNum);
    }

    public void transferCards(Player passingPlayer, Player receivingPlayer, ArrayList<Card> cardsToPass) {
        passingPlayer.getHand().removeCards(cardsToPass);
        receivingPlayer.getHand().addCards(cardsToPass);
    }

    public ArrayList<Card> choose3CardsToPass(Player passingPlayer) {
        Hand playerHand = passingPlayer.getHand();
        if (!passingPlayer.getIsPlayer()) {
            ArrayList<Card> cardsSortedByRank = playerHand.getCardsSortedByRank();
            int numCardsOnHand = cardsSortedByRank.size();
            //choose 3 largest cards to pass
            return new ArrayList<>(cardsSortedByRank.subList(numCardsOnHand-3, numCardsOnHand));
        }

        String[] order = new String[]{"first", "second", "third"};
        ArrayList<Card> passList = new ArrayList<>(); // Ints
        Scanner sc = new Scanner(System.in);

        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.printf("Please select %s card to pass%n", order[i]);
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
            System.out.println();
        }
        return passList;
    }


    public Card chooseCardToPlay(Player player, Set set) {
        Hand playerHand = player.getHand();
        int numCardsOnHand = playerHand.getNumberOfCards();
        Card cardPlayed = null;

        if (!player.getIsPlayer()) {
            // start of temp printing lines
            printAlignedOptions(numCardsOnHand);
            System.out.printf("%s Hand > %s%n", player.getName(), playerHand.getCardList());
            System.out.printf("Set contains > %s%n", set.getCards());
            //end of lines

            int totalCardsInSet = set.getCardsCount();
            Suit leadingSuit = set.getLeadingSuit();
            boolean isFirstSet = numCardsOnHand == 13;

            switch (totalCardsInSet) {
                case 0: // COM is first player
                    cardPlayed = playerHand.getSmallestComCard(isHeartsBroken);
                    break;
                case 3: // COM is last player
                    cardPlayed = playerHand.getNextHighestComCard(leadingSuit, null, isFirstSet);
                    break;
                default:
                    Card winningCard = set.getWinningCard();
                    cardPlayed = playerHand.getNextHighestComCard(leadingSuit, winningCard, isFirstSet);
                    break;
            }
            return cardPlayed;
        }

        printAlignedOptions(numCardsOnHand);
        System.out.printf("Your Hand > %s%n", playerHand.getCardList());
        System.out.printf("Set contains > %s%n", set.getCards());
        Scanner sc = new Scanner(System.in);

        while (cardPlayed == null) {
            System.out.print("Enter card index > ");
            int index;

            try {
                index = parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Index must be an integer");
                continue;
            }

            if (index < 1 || index > numCardsOnHand) {
                System.out.printf("Index must be between 1 and %d (inclusive)%n", numCardsOnHand);
                continue;
            }

            cardPlayed = playerHand.getCard(index - 1);
        }
        return cardPlayed;
    }

    public void tallyPointsForSet(Set set, Player player) {
        int pointsInSet = set.getPoints();
        String title;

        if (player.getIsPlayer()) {
            title = "You";
        } else {
            title = player.getName();
        }

        System.out.printf("%s won this set and got %d points%n%n", title, pointsInSet);

        player.addToPointsFromCurrentRound(pointsInSet);
    }

    public void tallyPointsForRound() {
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

}