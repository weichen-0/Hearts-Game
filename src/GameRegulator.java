
public class GameRegulator {

    public static boolean cardPlayedIsValid(Player player, Card cardPlayed, Set set, boolean isHeartBroken) {

        // Check if player truly has card that it wants to play
        Hand playerHand = player.getHand();
        if (!playerHand.containsCard(cardPlayed)) {
            System.out.println("Selected card does not exist in your hand. Try again.");
            return false;
        }

        // Check if point cards are being played for first set
        // If player has 13 cards, this is the first set.
        boolean isFirstSet = player.getHand().getCardList().size() == 13;
        Suit suitPlayed = cardPlayed.getSuit();
        Rank rankPlayed = cardPlayed.getRank();
        int setSize = set.getNumOfCardsInSet();

        if (isFirstSet) {
            // First card must be two clubs
            if (setSize == 0 && (!suitPlayed.isEquals(Suit.CLUBS) || !rankPlayed.isEquals(Rank.TWO))){
                System.out.println("You must start with Two of Clubs in the first set. Try again.");
                return false;
            }
            // Cannot play point cards in the first set
            if (suitPlayed.isEquals(Suit.SPADES) && cardPlayed.getRank().equals(Rank.QUEEN)) {
                System.out.println("You cannot play Queen of Spades in the first set. Try again.");
                return false;
            } else if (suitPlayed.isEquals(Suit.HEARTS)) {
                System.out.println("You cannot play any Hearts card in the first set. Try again.");
                return false;
            }
        }

        // Extra validation for non-first cards in the set
        if(setSize > 0) {
            // Check if player chooses card that follows leading suit, if available.
            Suit firstSuit = set.getLeadingSuit();
            if (!suitPlayed.isEquals(firstSuit)) {
                if (playerHand.hasSuit(firstSuit)) {
                    System.out.println("You must play a card of the leading suit if available. Try again.");
                    return false;
                } else if (suitPlayed.isEquals(Suit.HEARTS)) {
                    isHeartBroken = true;
                }
            }
        }

        // Extra validation for Hearts cards
        if (suitPlayed.isEquals(Suit.HEARTS) && !isHeartBroken) {
            System.out.println("Heart suit has not been broken yet. Try again.");
            return false;
        }
        return true;
    }

    public static int getStartPlayerIndex(Player[] listOfPlayers) {
        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO, null); // to update image
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
