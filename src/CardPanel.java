package com.hearts.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CardPanel extends JPanel{
    private Card card;
    private Image image;
    /**
     * The dimensions of each card.
     */
    public static final double dw=67.5,dh=102.0,dx=67.5,dy=103.0;
    public CardPanel(Card card) {
        this.card = card;
        image=Toolkit.getDefaultToolkit().getImage("images/Card.png");
        this.setPreferredSize(new Dimension((int)dw, (int)dh));
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
                card.setChoseness(!card.isChosen());
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
        g.drawImage( image,0, 0, getWidth(),getHeight(),
                (int)(card.getSuitedValue()%13*dx),(int)(card.getSuitedValue()/13*dy),
                (int)(card.getSuitedValue()%13*dx+dw), (int)(card.getSuitedValue()/13*dy+dh),this);
        if(card.isChosen()){
            g.setColor(new Color(255,0,0,32));
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 4, 4);
        }
        g.setColor(new Color(0,0,0,128));
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 4, 4);

    }
    public boolean isChosen() {
        return card.isChosen();
    }
    public void setChoseness(boolean choosed) {
        card.setChoseness(choosed);
    }
    public boolean isEnabled() {
        return card.isEnabled();
    }
    public void setEnabled(boolean enable) {
        card.setEnabled(enable);
    }
}
