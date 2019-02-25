import javax.swing.*;
import java.util.ArrayList;

public class Set {
    private ArrayList<Card> cardList = new ArrayList<>();

    public void addCard(Card card) {
        cardList.add(card);
    }

    public int getHighestCard() {
        if (cardList.size() != 4) {
            return -1;
        }

        int index = 0;
        Card highestCard = cardList.get(0);
        Suit highestCardSuit = highestCard.getSuit();

        for (int i = 1; i < cardList.size(); i++) {
            Card current = cardList.get(i);
            Suit currentSuit = current.getSuit();

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
