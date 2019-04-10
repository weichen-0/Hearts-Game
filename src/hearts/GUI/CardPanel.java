package hearts.GUI;

import hearts.model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * Class that represents the card on the GUI application display
 */
public class CardPanel extends JPanel{
    public static final double D_WIDTH =73, D_HEIGHT = 96; // card dimensions
    private boolean canSelect;
    private Card card;
    private Image image = Toolkit.getDefaultToolkit().getImage("images/cards/blank.gif");;

    /**
     * Constructs a CardPanel
     * @param card the card to be represented
     * @param canSelect whether the card in the panel is selectable
     */
    public CardPanel(Card card, boolean canSelect) {
        this.card = card;
        this.canSelect = canSelect;
        if(card != null)
            image = card.getImage();
        this.setPreferredSize(new Dimension((int) D_WIDTH, (int) D_HEIGHT));
        this.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                if(card != null)
                    card.toggleSelected();
                repaint();
            }
        });
    }

    /**
     * Gets the card that is being represented
     * @return card being represented
     */
    public Card getCard() {
        return card;
    }

    /**
     * Sets the card that is going to be represented
     * @param card card to be represented
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * Displays the card to be represented within the card panel
     * @param g the graphics context that will be used to draw on components
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(image,0, 0, getWidth(),getHeight(), this);
        if(canSelect && card != null && card.isSelected()){
            g.setColor(new Color(255,0,0,50));
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 4, 4);
        }
        g.setColor(new Color(0,0,0,128));
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 4, 4);
    }
}
