import java.util.Comparator;

public class CardRankComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        int suitDiff = card1.getSuit().compareTo(card2.getSuit());
        int rankDiff = card1.getRank().compareTo(card2.getRank());
        if (rankDiff != 0)
            return rankDiff;
        else
            return suitDiff;
    }
}

//        new Comparator<Card>() {
//            @Override
//            public int compare(Card card1, Card card2) {
//                return card1.getRank().compareTo(card2.getRank());
//            }
//        });