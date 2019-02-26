import javax.swing.*;
import java.util.ArrayList;

public class Set {
    private ArrayList<Card> cardList = new ArrayList<>();
    private boolean isHeartBroken;

    public Set(boolean isHeartBroken) {
        this.isHeartBroken = isHeartBroken;
    }

    public boolean addCard(Player player, Card anotherCard) {
        Hand playerHand = player.getHand();
        if (!playerHand.containsCard(anotherCard)) {
            return false;
        }

        Suit anotherSuit = anotherCard.getSuit();
        if (cardList.size() == 0 && !isHeartBroken) {
            if (anotherSuit.isEquals(Suit.HEARTS)) {
                return false;
            }
            cardList.add(anotherCard);
            return true;
        }

        Suit firstSuit = cardList.get(0).getSuit();
        if (!anotherSuit.isEquals(firstSuit) && playerHand.hasSuit(firstSuit)) {
            return false;
        } else {
            cardList.add(anotherCard);
            return true;
        }
    }

    public int getHighestCard() {
        if (cardList.size() != 4) {
            return -1;
        }

        int index = 0;
        Card highestCard = cardList.get(0);

        for (int i = 1; i < cardList.size(); i++) {
            Card current = cardList.get(i);
            Suit currentSuit = current.getSuit();

            Suit highestCardSuit = highestCard.getSuit();

            if (highestCardSuit.getSymbol().equals(currentSuit.getSymbol()) && highestCard.getRank().compareTo(current.getRank()) < 0) {
                    highestCard = current;
                    index = i;
            }

        }

        return index;
    }



    public int getPoints() {
        int totalPoint = 0;

        for (Card card : cardList) {
            totalPoint += card.getPoint();
        }

        return totalPoint;
    }
}
