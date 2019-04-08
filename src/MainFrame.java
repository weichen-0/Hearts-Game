package com.hearts;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import com.hearts.util.*;
import com.hearts.game.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    private Map<Integer,Player> playerMap;
    @SuppressWarnings("unused")
    private Game game;
    private JPanel contentPane,e,w,s,n,slist;
    private JLabel emsg,wmsg,nmsg,smsg;
    private JPanel eput,sput,wput,nput;
    private JPanel command;
    private JButton Go;
    private JPanel spanel;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        n = new JPanel();
        contentPane.add(n, BorderLayout.NORTH);
        n.setLayout(new BorderLayout(0, 0));
        nput = new JPanel();
        nput.setPreferredSize(new Dimension(10, 110));
        n.add(nput, BorderLayout.SOUTH);
        nput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        nmsg = new JLabel("North");
        nmsg.setHorizontalAlignment(SwingConstants.CENTER);
        n.add(nmsg, BorderLayout.NORTH);
        s = new JPanel();
        contentPane.add(s, BorderLayout.SOUTH);
        s.setLayout(new BorderLayout(0, 0));
        smsg = new JLabel("South");
        smsg.setHorizontalAlignment(SwingConstants.CENTER);
        s.add(smsg, BorderLayout.SOUTH);
        slist = new JPanel();
        slist.setPreferredSize(new Dimension(10, 110));
        s.add(slist, BorderLayout.CENTER);
        slist.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        w = new JPanel();
        contentPane.add(w, BorderLayout.WEST);
        w.setLayout(new BorderLayout(0, 0));

        wput = new JPanel();
        wput.setPreferredSize(new Dimension(70, 10));
        w.add(wput, BorderLayout.EAST);
        wput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        wmsg = new JLabel("West");
        w.add(wmsg, BorderLayout.WEST);

        e = new JPanel();
        contentPane.add(e, BorderLayout.EAST);
        e.setLayout(new BorderLayout(0, 0));

        eput = new JPanel();
        eput.setPreferredSize(new Dimension(70, 10));
        e.add(eput, BorderLayout.WEST);
        eput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        emsg = new JLabel("East");
        e.add(emsg, BorderLayout.EAST);

        JPanel desktop = new JPanel();
        contentPane.add(desktop, BorderLayout.CENTER);
        desktop.setLayout(new BorderLayout(0, 0));

        spanel = new JPanel();
        desktop.add(spanel, BorderLayout.SOUTH);
        spanel.setLayout(new BorderLayout(0, 0));

        sput = new JPanel();
        spanel.add(sput);
        sput.setPreferredSize(new Dimension(10, 110));
        sput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        command = new JPanel();
        spanel.add(command, BorderLayout.SOUTH);
        command.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        Game game = new Game("Blah");
        playerMap = game.getPlayers();
        repaint();

        Go = new JButton("OK");
        command.add(Go);
        Go.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Card> pokers = playerMap.get(Player.South).getHandlist();
                List<Card> plist = new ArrayList<Card>();
                for(Card p:pokers){
                    if(p.isChoosed()){
                        plist.add(p);
                    }
                }
                try {
                    if(master.getMax()!=null){
                        Player temp=master.getMax();
                        master.setMax(null);
                        master.clearRoundCase();
                        temp.Do(master, null);
                    }else playerMap.get(Player.South).Do(master, plist);
                } catch (IllegalMoveException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getTitle(), e1.getType());
                } finally{
                    repaint();
                    if(playerMap.get(Player.South).getHandlist().isEmpty()){
                        //something to replace here. We took it out for weird characters
                        playerMap=master.newGame();
                        repaint();
                    }
                }
            }
        });
    }

    @Override
    public void repaint() {
        super.repaint();
        slist.removeAll();
        Player splayer = playerMap.get(1);
        for (Card c : splayer.getHand()) {
            slist.add(new CardPanel(c));
        }

        nput.removeAll();
        Card nc = playerMap.get(3).getPlayedCard();
        nput.add(new CardPanel(nc));
        nmsg.setText("Player 3: " + playerMap.get(3).getName() + "Points: " + playerMap.get(3).getScore());

        sput.removeAll();
        smsg.setText("Player: " + splayer.getName() + "Points: " + splayer.getScore());
        Card sc = splayer.getPlayedCard();
        sput.add(new CardPanel(sc));

        eput.removeAll();
        Card ec = playerMap.get(4).getPlayedCard();
        eput.add(new CardPanel(ec));
        emsg.setText("Player 4: " + playerMap.get(4).getName() + "Points: " + playerMap.get(4).getScore());

        wput.removeAll();
        Card wc = playerMap.get(2).getPlayedCard();
        wput.add(new CardPanel(wc));
        wmsg.setText("Player 2: " + playerMap.get(2).getName() + "Points: " + playerMap.get(2).getScore());

        validate();
    }
}
