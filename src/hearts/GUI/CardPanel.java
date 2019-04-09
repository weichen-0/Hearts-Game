package hearts.GUI;

import hearts.model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class CardPanel extends JPanel{
    public static final double D_WIDTH =73, D_HEIGHT = 96; // card dimensions
    private boolean canSelect;
    private Card card;
    private Image image = Toolkit.getDefaultToolkit().getImage("images/cards/blank.gif");;

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
    public Card getCard() {
        return card;
    }
    public void setCard(Card card) {
        this.card = card;
    }
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
