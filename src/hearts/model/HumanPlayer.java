package hearts.model;

import java.util.List;

/**
 * Sub-class of Player.
 * Inherits all accessible methods and attributes of Player.
 * Has 1 additional method specific to ComPlayer.
 */
public class HumanPlayer extends Player {

    /**
     * Constructs a HumanPlayer with the given name.
     * @param name player name.
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Deselect all the previously selected cards in player's hand.
     */
    public void deselectCardsInHand(){
        List<Card> cards = getHand().getCardList();
        for(Card c : cards){
            if(c.isSelected()) c.toggleSelected();
        }
    }
}
