import java.util.List;

/**
 * Sub-class of Player
 * Inherits all accessible methods and attributes of Player
 * Has 2 additional methods specific to ComPlayer
 */
public class ComPlayer extends Player {

    /**
     * Constructs a ComPlayer with the given name
     * @param name player name
     */
    public ComPlayer(String name) { super(name); }

    /**
     * Select 3 largest cards to pass to another Player
     * @return a list of 3 cards to be pass
     */
    public List<Card> choose3CardsToPass() {
        Hand playerHand = getHand();
        List<Card> cardsSortedByRank = playerHand.getCardsSortedByRank();
        int numCardsOnHand = cardsSortedByRank.size();

        //choose 3 largest cards to pass
        return cardsSortedByRank.subList(numCardsOnHand - 3, numCardsOnHand);
    }

    /**
     * Select a card to play in the set
     * If ComPlayer is first player in set, it picks the smallest card that adheres to game rules
     * If ComPlayer is last player, pick the largest card.
     * Otherwise, pick the largest rank card it owns of the same suit that does not exceed the winning card in set if possible
     * NOTE: if it is void of the suit led, pick the card of highest rank available that adheres to game rules
     * @param set current set in the game
     * @param isHeartsBroken whether hearts has been broken in the round
     * @return
     */
    public Card chooseCardToPlay(Set set, boolean isHeartsBroken) {
        Hand playerHand = getHand();
        int numCardsOnHand = playerHand.getNumberOfCards();
        Card cardPlayed;

        System.out.printf("%s Hand > %s%n", getName(), playerHand);
        System.out.printf("Current Set contains > %s%n", set.getSetCards());

        int totalCardsInSet = set.getNumOfCardsInSet();
        Suit leadingSuit = set.getLeadingSuit();
        boolean isFirstSet = numCardsOnHand == 13; //if ComPlayer has 13 cards in hand, current set is first set

        switch (totalCardsInSet) {
            case 0: // COM is first player
                cardPlayed = getSmallestComCard(isHeartsBroken);
                break;
            case 3: // COM is last player
                cardPlayed = getNextHighestCard(leadingSuit, null, isFirstSet);
                break;
            default:
                Card winningCard = set.getWinningCard();
                cardPlayed = getNextHighestCard(leadingSuit, winningCard, isFirstSet);
                break;
        }
        System.out.printf("%s picked %s%n", getName(), cardPlayed);
        this.setPlayedCard(cardPlayed);
        return cardPlayed;
    }

    /**
     * Private helper method that gets the next highest card to play if ComPlayer is not the first player in the set
     * If ComPlayer is last player, return highest card of leadingSuit in hand, if available.
     * Else (ComPlayer is second or third player)
     * If ComPlayer has leading suit, return highest ranked card of that suit that does not exceed the winning card
     * where possible. If no leading suit then return highest ranked card available that adheres to game rules
     * @param leadingSuit suit of first card played in set
     * @param isFirstSet whether current set is the first set in the round
     * @return card to be played
     */
    private Card getNextHighestCard(Suit leadingSuit, Card winningCard, boolean isFirstSet) {
        // this method only gets called by chooseCardToPlayer in Round class
        // only called in situations where player is non-first player

        Hand hand = getHand();
        List<Card> cardsSortedByRank = hand.getCardsSortedByRank();
        int totalSize = cardsSortedByRank.size();

        if(winningCard == null){ // COM is the last player, so get the highest card
            for (int i = totalSize - 1; i >= 0; i--) {
                Card cardPlayed = cardsSortedByRank.get(i);
                // if COM's hand has suit must play card that shares the leading suit
                if (hand.hasSuit(leadingSuit) && cardPlayed.getSuit().compareTo(leadingSuit) != 0) continue;
                return cardPlayed;
            }
        }

        // ComPlayer is second or third player
        // if have leading suit, pick largest rank card of that suit that does not exceed the winning card
        if(hand.hasSuit(leadingSuit)){
            Card nextHighestRankedCard = null;
            for (int i = 0; i < totalSize; i++) {
                Card cardPlayed = cardsSortedByRank.get(i);
                if (cardPlayed.getSuit().compareTo(leadingSuit) != 0) continue;
                if (cardPlayed.getRank().compareTo(winningCard.getRank()) > 0) {
                    // previously saved card is largest rank card
                    if(nextHighestRankedCard == null) return cardPlayed;
                    return nextHighestRankedCard;
                }
                nextHighestRankedCard = cardPlayed;
            }
            return nextHighestRankedCard;
        }

        // if no leading suit, pick highest rank available that adheres to game rules
        if(isFirstSet){
            for (int i = totalSize - 1; i >= 0; i--) {
                Card cardPlayed = cardsSortedByRank.get(i);
                if (!cardPlayed.isPointCard()) { // since first set, play highest card that is not a point card
                    return cardPlayed;
                }
            }
        }
        return cardsSortedByRank.get(totalSize - 1); // can play point card if not first set
    }

    /**
     * Private helper method that gets the smallest card to play if ComPlayer is first player in set
     * If ComPlayer has 2 Clubs in hand, return 2 Clubs
     * Else if hearts is broken, return card of smallest rank in hand
     * Else return card of smallest rank that is not a heart.
     * @param heartsBroken whether hearts has been broken in the round
     * @return card to be played
     */
    private Card getSmallestComCard(boolean heartsBroken) {
        // assume method only gets called by chooseCardToPlayer in Round class
        // only called in situations where player is first player
        Hand hand = getHand();

        Card twoClubs = new Card(Suit.CLUBS, Rank.TWO);
        if (hand.containsCard(twoClubs)) return twoClubs;

        // if player's deck doesn't contain two clubs this is not the first set
        // any point card can be played, if heartsBroken == true
        List<Card> cardsSortedByRank = hand.getCardsSortedByRank();
        if (heartsBroken) return cardsSortedByRank.get(0);

        for (Card card : cardsSortedByRank) {
            if (card.getSuit().compareTo(Suit.HEARTS) != 0) {
                return card;
            }
        }
        System.out.println("getSmallestComCard() returned null");
        return null;
    }
}
