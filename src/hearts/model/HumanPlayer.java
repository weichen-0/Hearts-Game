package hearts.model;

import java.util.List;

/**
 * Sub-class of hearts.model.Player
 * Inherits all accessible methods and attributes of hearts.model.Player
 * No methods specific to hearts.model.HumanPlayer at the moment - to be added in future development
 */
public class HumanPlayer extends Player {

    /**
     * Constructs a hearts.model.HumanPlayer with the given name
     * @param name player name
     */
    public HumanPlayer(String name) {
        super(name);
    }

    public void deselectCardsInHand(){
        List<Card> cards = getHand().getCardList();
        for(Card c : cards){
            if(c.isSelected()) c.toggleSelected();
        }
    }
}
