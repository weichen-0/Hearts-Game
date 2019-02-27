import java.util.*;

import static java.lang.Integer.parseInt;

public class Round {

    // index 0 is always Human Player
    private static ArrayList<Player> listOfPlayers;
    private boolean heartsBroken; //TODO: keep? not keep?
    private int roundNum = 1;
    private int leadingPlayer; //TODO: remove. not necessary

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

    public void printRoundScoreBoard() {
        System.out.printf("[SCOREBOARD FOR ROUND SO FAR]%n");
        for (Player p : listOfPlayers) {
            if (p.getIsPlayer()) {
                System.out.printf("YOUR SCORE > %d%n", p.getPointsFromCurrentRound());
            } else System.out.printf("%s SCORE > %d%n", p.getName(), p.getPointsFromCurrentRound());
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
        System.out.printf("Dealing cards...%n%n");

        distributeCard();
        printAllHands();
        passCards();

        System.out.println("Displaying everyone's hands...");
        printAllHands();

        //first round logic: decide first player
        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO, null); // to update image
        int startPlayerIndex = 0;
        for (; startPlayerIndex < 4; startPlayerIndex++) {
            Hand playerhand = listOfPlayers.get(startPlayerIndex).getHand();
            if (playerhand.containsCard(twoClubs)) {
                break;
            }
        }

        for (int setCount = 1; setCount <= 13; setCount++) {
            Set set = new Set();
            for (int i = startPlayerIndex; i < startPlayerIndex + 4; i++) {
                Player player = listOfPlayers.get(i % 4);
                System.out.printf("%nWe are now in Set %d, Card #%d%n", setCount, i-startPlayerIndex+1);
                System.out.printf("Player '%s', please select card to play%n", player.getName());

                boolean cardPlayedIsValid = false;
                while (!cardPlayedIsValid) {
                    Card cardPlayed = chooseCardToPlay(player, set);
                    System.out.printf("Player '%s' picked %s%n", player.getName(), cardPlayed);
                    if (set.addCardToSet(player, cardPlayed)) {
                        cardPlayedIsValid = true;
                        player.getHand().removeCard(cardPlayed);
                    }else{
                        System.out.println("Invalid card played. Try again \n");
                    }
                }
            }
            startPlayerIndex = (startPlayerIndex + set.getHighestCardIndex()) % 4;
            Player winningPlayerOfSet = listOfPlayers.get(startPlayerIndex);
            tallyPoints(set, winningPlayerOfSet);
            printRoundScoreBoard();
        }
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
                System.out.printf("%s passed 3 cards to %s - %s%n", passingPlayer.getName(), receivingPlayer.getName(),
                        cardsToPass.toString());
            }

        }
        System.out.printf("%nAll cards have been passed.%n%n");
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


    public Card chooseCardToPlay(Player player, Set set) {
        Hand playerHand = player.getHand();
        int numCardsOnHand = playerHand.getCardList().size();

        printAlignedOptions();
        System.out.printf("Your Hand: %s%n", playerHand.getCardList());
        System.out.printf("Set contains: %s%n", set.getCards());
        Scanner sc = new Scanner(System.in);

        Card cardPlayed = null;

        while (cardPlayed == null) {
            System.out.print("Enter card index: ");
            int index = 0;

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

//        boolean isFirstSet = numCardsOnHand.size() == 13;
//
//        // First set's first card must be two clubs
//        if(isFirstSet && set.getCardsCount() == 0){
//            Card twoClubs = new Card(Suit.CLUBS, Rank.TWO, null);
////            if(!playerHand.containsCard(twoClubs))
////                throw Exception("Chosen first player should have two clubs");
//            return playerHand.findCard(twoClubs);
//        }
//
//        // Must follow
    }

    public void tallyPoints(Set set, Player player) {
//        for (Player p: listOfPlayers){
//            if (p == player){
//                player1.setPointsFromCurrentRound(set.getPoints());
//            }
//        }
        player.addToPointsFromCurrentRound(set.getPoints());
    }

}