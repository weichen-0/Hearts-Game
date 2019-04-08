import java.util.List;

public class ComPlayer extends Player {

    public ComPlayer(String name) {
        super(name);
    }

    public List<Card> choose3CardsToPass() {
        Hand playerHand = getHand();
        List<Card> cardsSortedByRank = playerHand.getCardsSortedByRank();
        int numCardsOnHand = cardsSortedByRank.size();

        //choose 3 largest cards to pass
        return cardsSortedByRank.subList(numCardsOnHand-3, numCardsOnHand);
    }

    public Card chooseCardToPlay(Set set, boolean isHeartsBroken) {
        Hand playerHand = getHand();
        int numCardsOnHand = playerHand.getNumberOfCards();
        Card cardPlayed = null;

        System.out.printf("%s Hand > %s%n", getName(), playerHand);
        System.out.printf("Set contains > %s%n", set.getSetCards());

        int totalCardsInSet = set.getNumOfCardsInSet();
        Suit leadingSuit = set.getLeadingSuit();
        boolean isFirstSet = numCardsOnHand == 13;

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
        return cardPlayed;
    }

    private Card getNextHighestCard(Suit leadingSuit, Card card, boolean isFirstSet) {
        // assume method only gets called by chooseCardToPlayer in Round class
        // only called in situations where player is non-first player
        Hand hand = getHand();

        if (hand.hasSuit(leadingSuit)) {
            Card nextHighestCard = null;
            for (int i = hand.getNumberOfCards() - 1; i >= 0; i--) {
                Card tempCard = hand.getCard(i);
                if (!tempCard.getSuit().isEquals(leadingSuit)) continue;
                if (tempCard == null || tempCard.getRank().compareTo(tempCard.getRank()) < 0) return tempCard;
                nextHighestCard = tempCard;
            }
            return nextHighestCard;
        }

        // returned card is of different suit from leadingSuit
        List<Card> cardsSortedByRank = hand.getCardsSortedByRank();
        int totalSize = cardsSortedByRank.size();

        if(!isFirstSet) return cardsSortedByRank.get(totalSize - 1);

        for (int i = totalSize - 1; i >= 0; i--) {
            Card cardPlayed = cardsSortedByRank.get(i);
            if (!cardPlayed.isPointCard()) {
                return cardPlayed;
            }
        }
        return cardsSortedByRank.get(totalSize - 1);
    }

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
            if (!card.getSuit().isEquals(Suit.HEARTS)) {
                return card;
            }
        }
        System.out.println("getSmallestComCard() returned null");
        return null;
    }
}
