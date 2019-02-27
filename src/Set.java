import java.util.ArrayList;

public class Set {
    private ArrayList<Card> cards = new ArrayList<>();
    private static boolean isHeartBroken = false;

    public ArrayList<Card> getCards(){
        return cards;
    }

    public Suit getLeadingSuit() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.get(0).getSuit();
    }

    public Card getLargetCard() {
        Card largestCard = null;
        Card firstCard = cards.get(0);
        for (Card card : cards) {
            if (card.getSuit().isEquals(firstCard.getSuit()) && card.getRank().compareTo(firstCard.getRank()) > 0) {
                largestCard = card;
            }
        }
        return largestCard;
    }

    public int getCardsCount(){
        return cards.size();
    }

    public boolean addCardToSet(Player player, Card cardPlayed) {

        // Check if player truly has card that it wants to play
        Hand playerHand = player.getHand();
        if (!playerHand.containsCard(cardPlayed)) {
            System.out.println("Selected card does not exist in your hand");
            return false;
        }

        // Check if point cards are being played for first set
        // If player has 13 cards, this is the first set.
        boolean isFirstSet = player.getHand().getCardList().size() == 13;
        Suit suitPlayed = cardPlayed.getSuit();
        Rank rankPlayed = cardPlayed.getRank();
        if (isFirstSet) {
            // First card must be two clubs
            if (cards.size() == 0 && (!suitPlayed.isEquals(Suit.CLUBS) || !rankPlayed.isEquals(Rank.TWO))){
                System.out.println("You must start with Two of Clubs in the first set");
                return false;
            }
            // Cannot play point cards in the first set
            if (suitPlayed.isEquals(Suit.SPADES) && cardPlayed.getRank().equals(Rank.QUEEN)) {
                System.out.println("You cannot play Queen of Spades in the first set");
                return false;
            } else if (suitPlayed.isEquals(Suit.HEARTS)) {
                System.out.println("You cannot play any Hearts card in the first set");
                return false;
            }
        }

        // Extra validation for non-first cards in the set
        if(cards.size() > 0){
            // Check if player chooses card that follows leading suit, if available.
            Suit firstSuit = getLeadingSuit();
            if (!suitPlayed.isEquals(firstSuit)) {
                if (playerHand.hasSuit(firstSuit)) {
                    System.out.println("You must play a card of the leading suit if available");
                    return false;
                } else if (suitPlayed.isEquals(Suit.HEARTS)) {
                    isHeartBroken = true;
                }
            }
        }

        // Extra validation for Hearts cards
        if (suitPlayed.isEquals(Suit.HEARTS) && !isHeartBroken) {
            System.out.println("Heart suit has not been broken yet");
            return false;
        }

        cards.add(cardPlayed);
        return true;
    }

    public int getHighestCardIndex() {
        int index = 0;
        Card highestCard = cards.get(0); //TODO: raise exception if there aren't 4 cards in set

        for (int i = 1; i < cards.size(); i++) {
            Card current = cards.get(i);
            Suit currentSuit = current.getSuit();
            Suit highestCardSuit = highestCard.getSuit();

            if (highestCardSuit.isEquals(currentSuit) && highestCard.getRank().compareTo(current.getRank()) < 0) {
                highestCard = current;
                index = i;
            }
        }
        return index;
    }


    public int getPoints() {
        int setPoint = 0;

        for (Card card : cards) {
            setPoint += card.getPoint();
        }
        System.out.println();
        return setPoint;
    }

    public void printCardsInSet() {
        System.out.printf("%nCards played this Set > [");
        for (Card card : cards) {
            System.out.printf("%s:%d ", card, card.getPoint());
        }
        System.out.println("]");
    }
}
