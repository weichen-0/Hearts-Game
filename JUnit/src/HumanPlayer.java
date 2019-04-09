import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    public List<Card> choose3CardsToPass() {
        return new ArrayList<Card>();
    }


    public Card chooseCardToPlay(Set set, boolean isHeartsBroken) {
        return new Card(null, null);
    }


//    public List<Card> choose3CardsToPass() {
//        Hand playerHand = getHand();
//        String[] order = new String[]{"first", "second", "third"};
//        List<Card> passList = new ArrayList<>(); // Ints
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println();
//        for (int i = 0; i < 3; i++) {
//            System.out.printf("Please select %s card to pass%n", order[i]);
//            Card passCard = null;
//
//            while (passCard == null) {
//                System.out.print("Enter card index: ");
//                int index;
//
//                try {
//                    index = Integer.parseInt(sc.nextLine());
//                } catch (NumberFormatException e) {
//                    System.out.println("Index must be an integer");
//                    continue;
//                }
//
//                if (index < 1 || index > 13) {
//                    System.out.println("Index must be between 1 and 13 (inclusive)");
//                    continue;
//                }
//
//                passCard = playerHand.getCard(index - 1);
//
//                if (passList.contains(passCard)) {
//                    System.out.println("Index has already been selected");
//                    passCard = null;
//
//                } else {
//                    passList.add(passCard);
//                }
//            }
//            System.out.println();
//        }
//        return passList;
//    }

//    public Card chooseCardToPlay(Set set, boolean isHeartsBroken) {
//        Hand playerHand = getHand();
//        int numCardsOnHand = playerHand.getNumberOfCards();
//        Card cardPlayed = null;
//
//        System.out.printf("Your Hand > %s%n", playerHand);
//        System.out.printf("Set contains > %s%n", set.getSetCards());
//        Scanner sc = new Scanner(System.in);
//
//        while (cardPlayed == null) {
//            System.out.print("Enter card index > ");
//            int index;
//
//            try {
//                index = Integer.parseInt(sc.nextLine());
//            } catch (NumberFormatException e) {
//                System.out.println("Index must be an integer");
//                continue;
//            }
//
//            if (index < 1 || index > numCardsOnHand) {
//                System.out.printf("Index must be between 1 and %d (inclusive)%n", numCardsOnHand);
//                continue;
//            }
//
//            cardPlayed = playerHand.getCard(index - 1);
//        }
//        setHasPassedCards(true);
//        return cardPlayed;
//    }


}
