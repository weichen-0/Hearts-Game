import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    private Player[] players;
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
        w.setLayout(new BorderLayout(0, 0));
        w.setPreferredSize(new Dimension(350, 100));
        contentPane.add(w, BorderLayout.WEST);

        wput = new JPanel();
        wput.setPreferredSize(new Dimension(75, 10));
        w.add(wput, BorderLayout.EAST);
        wput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        wmsg = new JLabel("West");
        wmsg.setVerticalAlignment(SwingConstants.TOP);
        w.add(wmsg, BorderLayout.WEST);

        e = new JPanel();
        e.setLayout(new BorderLayout(0, 0));
        e.setPreferredSize(new Dimension(350, 100));
        contentPane.add(e, BorderLayout.EAST);

        eput = new JPanel();
        eput.setPreferredSize(new Dimension(75, 10));
        e.add(eput, BorderLayout.WEST);
        eput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        emsg = new JLabel("East");
        emsg.setVerticalAlignment(SwingConstants.TOP);
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

        Game game = new Game(1);
        game.initRound();

        players = game.getListOfPlayers();
        repaint();


        Go = new JButton("OK");
        command.add(Go);
        Go.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Card> cardsInHand = players[0].getHand().getCardList();
                List<Card> plist = new ArrayList<>();
                for(Card p : cardsInHand){
                    if(p.isSelected()){
                        plist.add(p);
                    }
                }
//                try {
//                    if(master.getMax()!=null){
//                        Player temp=master.getMax();
//                        master.setMax(null);
//                        master.clearRoundCase();
//                        temp.Do(master, null);
//                    }else playerMap.get(Player.South).Do(master, plist);
//                } catch (IllegalMoveException e1) {
//                    JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getTitle(), e1.getType());
//                } finally{
//                    repaint();
//                    if(playerMap.get(Player.South).getHandlist().isEmpty()){
//                        //something to replace here. We took it out for weird characters
//                        playerMap=master.newGame();
//                        repaint();
//                    }
//                }
            }
        });
    }

    @Override
    public void repaint() {
        super.repaint();
        slist.removeAll();
        Player splayer = players[0];
        Card c = null;
        for (Card card : splayer.getHand().getCardList()) {
            slist.add(new CardPanel(card, true));
        }
        nput.removeAll();
//        Card nc = playerMap.get(3).getPlayedCard();
        nput.add(new CardPanel(c, false));
        nmsg.setText("<html><b>" + players[2].getName() + " (" + players[2].getHandSize() + " Cards) </b> | Round Points: " + players[2].getPointsFromCurrentRound() + " | Total Points: " + players[2].getTotalPoints() + "</html>");

        sput.removeAll();
        smsg.setText("<html><b>" + players[0].getName() + " (" + players[2].getHandSize() + " Cards) </b> | Round Points: " + players[0].getPointsFromCurrentRound() + " | Total Points: " + players[0].getTotalPoints() + "</html>");
//        Card sc = splayer.getPlayedCard();
        sput.add(new CardPanel(c, false));

        eput.removeAll();
//        Card ec = playerMap.get(4).getPlayedCard();
        eput.add(new CardPanel(c, false));
        emsg.setText("<html><div style='text-align: center;'><b>" + players[3].getName() + " (" + players[2].getHandSize() + " Cards) </b><br/><br/>Round Points: " + players[3].getPointsFromCurrentRound() + "<br/>Total Points: " + players[3].getTotalPoints() + "</div></html>");

        wput.removeAll();
//        Card wc = playerMap.get(2).getPlayedCard();
        wput.add(new CardPanel(c, false));
        wmsg.setText("<html><div style='text-align: center;'><b>" + players[1].getName() + " (" + players[2].getHandSize() + " Cards) </b><br/><br/>Round Points: " + players[1].getPointsFromCurrentRound() + "<br/>Total Points: " + players[1].getTotalPoints() + "</div></html>");

        validate();
    }
}