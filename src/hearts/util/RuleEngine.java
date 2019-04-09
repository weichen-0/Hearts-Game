package hearts.util;

import hearts.model.*;
import hearts.exception.*;

/**
 * Class that ensures game play adheres to Hearts game rules
 */
public class RuleEngine {

    /**
     * Checks if the card played by player is a valid action
     * If first set, ensures player selects 2 of Club if it is in hand and prevents player from playing any point cards
     * Else if player is the first player in the set, ensures player does not play any hearts if hearts is not broken
     * Else if player is not the first player in the set, ensures player plays a card of the leading suit if available
     *
     * @param player player doing the action
     * @param cardPlayed card selected by player
     * @param set current set in this Round
     * @param isHeartBroken whether hearts has been broken in this Round
     * @throws IllegalMoveException if card played is not a valid action
     */
    public static void validateCardPlayed(Player player, Card cardPlayed, Set set, boolean isHeartBroken) throws IllegalMoveException {

        Hand playerHand = player.getHand();
        if (!playerHand.hasSuit(Suit.SPADES) && !playerHand.hasSuit(Suit.CLUBS) && !playerHand.hasSuit(Suit.DIAMONDS)) {
            return; // player only has hearts cards; can only play hearts.
        }

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
        if (suitPlayed.compareTo(Suit.HEARTS) == 0 && !isHeartBroken) {
            String errorMsg = "Heart suit has not been broken yet. Try again.";
            System.out.println(errorMsg);
            throw new IllegalMoveException(errorMsg, "Invalid Move");
        }
    }


    /**
     * Returns the index of the starting player (with the 2 of Clubs in hand) at the start of every round
     *
     * @param listOfPlayers list of the 4 players
     * @return -1 if none of the players have a 2 of Clubs in hand,
     * otherwise return the index of the starting player
     */
    public static int getStartPlayerIndex(Player[] listOfPlayers) {
        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO);
        int index = -1;
        for (; index < 4; index++) {
            Hand playerHand = listOfPlayers[index].getHand();
            if (playerHand.containsCard(twoClubs)) {
                break;
            }
        }
        return index;
    }
}
