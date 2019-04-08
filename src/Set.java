import java.util.ArrayList;
import java.util.List;

public class Set {
    private List<Card> cards = new ArrayList<>();
    private int playerNumLastPlayed = -1;

    public List<Card> getSetCards() { return cards; }


    public Suit getLeadingSuit() {
        //TODO: raise exception if there isn't at least 1 card in set
        if (cards.size() == 0) {
            return null;
        }
        return cards.get(0).getSuit();
    }

    public int getNumOfCardsInSet() {
        return cards.size();
    }

    public void addCardToSet(Card cardPlayed, int playerNum) {
        playerNumLastPlayed = playerNum;
        cards.add(cardPlayed);
    }

    public int getHighestCardIndex() {
        int index = 0;
        Card highestCard = cards.get(0); //TODO: raise exception if there isn't at least 1 card in set

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

    public Card getWinningCard() { return cards.get(getHighestCardIndex()); }

    public int getWinningPlayerIndex() {
        int winningCardIndex = getHighestCardIndex();
        return (winningCardIndex + playerNumLastPlayed + 1) % 4;
    }

    public int getTotalPointsInSet() {
        int setPoint = 0;
        for (Card card : cards) {
            setPoint += card.getPoint();
        }
        return setPoint;
    }

    public String toString() {
        String output = "[";
        for (Card card : cards) {
            output += String.format("%s:%d ", card, card.getPoint());
        }
        output += "]";
        return output;
    }

    public int getPlayerNumLastPlayed() {
        return playerNumLastPlayed;
    }
}
