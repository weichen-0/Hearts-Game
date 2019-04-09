
public class GameRegulator {

    public static void validateCardPlayed(Player player, Card cardPlayed, Set set, boolean isHeartBroken) throws IllegalMoveException {

        // Check if player truly has card that it wants to play
        Hand playerHand = player.getHand();
//        if (!playerHand.containsCard(cardPlayed)) {
//            System.out.println("Selected card does not exist in your hand. Try again.");
//            return false;
//        }

        // Check if point cards are being played for first set
        // If player has 13 cards, this is the first set.
        boolean isFirstSet = player.getHand().getCardList().size() == 13;
        Suit suitPlayed = cardPlayed.getSuit();
        Rank rankPlayed = cardPlayed.getRank();
        int setSize = set.getNumOfCardsInSet();

        if (isFirstSet) {
            // First card must be two clubs
            if (setSize == 0 && (suitPlayed.compareTo(Suit.CLUBS) != 0 || rankPlayed.compareTo(Rank.TWO) != 0)){
                String errorMsg = "You must start with Two of Clubs in the first set. Try again.";
                System.out.println(errorMsg);
                throw new IllegalMoveException(errorMsg, "Invalid Move");
            }
            // Cannot play point cards in the first set
            if (suitPlayed.compareTo(Suit.SPADES) == 0 && cardPlayed.getRank().equals(Rank.QUEEN)) {
                String errorMsg = "You cannot play Queen of Spades in the first set. Try again.";
                System.out.println(errorMsg);
                throw new IllegalMoveException(errorMsg, "Invalid Move");
            } else if (suitPlayed.compareTo(Suit.HEARTS) == 0) {
                String errorMsg = "You cannot play any Hearts card in the first set. Try again.";
                System.out.println(errorMsg);
                throw new IllegalMoveException(errorMsg, "Invalid Move");
            }
        }

        // Extra validation for non-first cards in the set
        if(setSize > 0) {
            // Check if player chooses card that follows leading suit, if available.
            Suit firstSuit = set.getLeadingSuit();
            if (suitPlayed.compareTo(firstSuit) != 0) {
                if (playerHand.hasSuit(firstSuit)) {
                    String errorMsg = "You must play a card of the leading suit if available. Try again.";
                    System.out.println(errorMsg);
                    throw new IllegalMoveException(errorMsg, "Invalid Move");
                } else if (suitPlayed.compareTo(Suit.HEARTS) == 0) {
                    isHeartBroken = true;
                }
            }
        }

        // Extra validation for Hearts cards
        if (playerHand.hasSuit(Suit.SPADES) || playerHand.hasSuit(Suit.CLUBS) || playerHand.hasSuit(Suit.DIAMONDS)) {
            if (suitPlayed.compareTo(Suit.HEARTS) == 0 && !isHeartBroken) {
                String errorMsg = "Heart suit has not been broken yet. Try again.";
                System.out.println(errorMsg);
                throw new IllegalMoveException(errorMsg, "Invalid Move");
            }
        }
    }

    public static int getStartPlayerIndex(Player[] listOfPlayers) {
        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO); // to update image
        int index = 0;
        for (; index < 4; index++) {
            Hand playerHand = listOfPlayers[index].getHand();
            if (playerHand.containsCard(twoClubs)) {
                break;
            }
        }
        return index;
    }
}
