package hearts.GUI;

import hearts.model.*;
import hearts.controller.*;
import hearts.exception.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 */
public class GameGUI extends JFrame {

    // Controllers and hearts.model.Game Data
    private GameController gameCtrl;
    private Player[] players;

    // Panels
    private JPanel northCard;
    private JPanel southCard;
    private JPanel eastCard;
    private JPanel westCard;
    private JPanel cardsPanel;

    // Labels
    private JLabel northLabel;
    private JLabel southLabel;
    private JLabel eastLabel;
    private JLabel westLabel;

    /**
     * Launch the hearts.GUI application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameGUI frame = new GameGUI();
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
    public GameGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 250, 1200, 550);
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(8, 50, 8, 50));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel northPanel = new JPanel();
        contentPane.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new BorderLayout(0, 0));
        northCard = new JPanel();
        northCard.setPreferredSize(new Dimension(10, 110));
        northPanel.add(northCard, BorderLayout.SOUTH);
        northCard.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        northLabel = new JLabel("North");
        northLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northPanel.add(northLabel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        contentPane.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new BorderLayout(0, 0));
        southLabel = new JLabel("South");
        southLabel.setHorizontalAlignment(SwingConstants.CENTER);
        southPanel.add(southLabel, BorderLayout.SOUTH);
        cardsPanel = new JPanel();
        cardsPanel.setPreferredSize(new Dimension(10, 110));
        southPanel.add(cardsPanel, BorderLayout.CENTER);
        cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout(0, 0));
        eastPanel.setPreferredSize(new Dimension(350, 100));
        contentPane.add(eastPanel, BorderLayout.EAST);
        eastCard = new JPanel();
        eastCard.setPreferredSize(new Dimension(75, 10));
        eastPanel.add(eastCard, BorderLayout.WEST);
        eastCard.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        eastLabel = new JLabel("East");
        eastLabel.setVerticalAlignment(SwingConstants.TOP);
        eastPanel.add(eastLabel, BorderLayout.EAST);

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout(0, 0));
        westPanel.setPreferredSize(new Dimension(350, 100));
        contentPane.add(westPanel, BorderLayout.WEST);
        westCard = new JPanel();
        westCard.setPreferredSize(new Dimension(75, 10));
        westPanel.add(westCard, BorderLayout.EAST);
        westCard.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        westLabel = new JLabel("West");
        westLabel.setVerticalAlignment(SwingConstants.TOP);
        westPanel.add(westLabel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        southCard = new JPanel();
        southCard.setPreferredSize(new Dimension(10, 110));
        southCard.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout(0, 0));
        JPanel southCardPanel = new JPanel();
        centerPanel.add(southCardPanel, BorderLayout.SOUTH);
        southCardPanel.setLayout(new BorderLayout(0, 0));
        southCardPanel.add(southCard);

        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("Play!");
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.add(playButton);
        southCardPanel.add(buttonPanel, BorderLayout.SOUTH);

        // initialise game
        gameCtrl = new GameController();
        players = gameCtrl.startGame();
        JOptionPane.showMessageDialog(null, "Welcome to Hearts! To start Round 1, select 3 cards to pass to the left.",
                "Action Required", JOptionPane.INFORMATION_MESSAGE);
        repaint();
        playButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Card> cardsInHand = players[0].getHand().getCardList();
                List<Card> plist = new ArrayList<>();
                for (Card p : cardsInHand) {
                    if (p.isSelected()) {
                        plist.add(p);
                    }
                }
                try {
                    gameCtrl.executeMove(plist); // execute player's play
                } catch (IllegalMoveException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getTitle(), JOptionPane.ERROR_MESSAGE);
                } catch (UserMessageException e1) {
                    repaint();
                    JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getTitle(), JOptionPane.INFORMATION_MESSAGE);
                } finally {
                    try {
                        gameCtrl.executeComputerMoves(); // execute computer moves after player has played
                    } catch (UserMessageException e1) {
                        repaint();
                        JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getTitle(), JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (gameCtrl.hasRoundEnded()) {
                        boolean gameOngoing = gameCtrl.startNextRound();
                        if (!gameOngoing) { // game has ended because at least 1 player has at least 100 points
                            JOptionPane.showMessageDialog(null, "To start new game, select 3 cards to pass to the left.", "New hearts.model.Game", JOptionPane.INFORMATION_MESSAGE);
                            players = gameCtrl.startGame();
                        }
                    }
                    repaint();
                }
            }
        });
    }

    @Override
    public void repaint() {
        super.repaint();
        cardsPanel.removeAll();
        Player splayer = players[0];
        for (Card card : splayer.getHand().getCardList()) {
            cardsPanel.add(new CardPanel(card, true));
        }
        northCard.removeAll();
        northCard.add(new CardPanel(players[2].getPlayedCard(), false));
        northLabel.setText("<html><b>" + players[2].getName() + " (" + players[2].getHandSize() + " Cards) </b> | Round Points: " + players[2].getPointsFromCurrentRound() + " | Total Points: " + players[2].getTotalPoints() + "</html>");

        southCard.removeAll();
        southLabel.setText("<html><b>" + players[0].getName() + " (" + players[0].getHandSize() + " Cards) </b> | Round Points: " + players[0].getPointsFromCurrentRound() + " | Total Points: " + players[0].getTotalPoints() + "</html>");
        southCard.add(new CardPanel(players[0].getPlayedCard(), false));

        eastCard.removeAll();
        eastCard.add(new CardPanel(players[3].getPlayedCard(), false));
        eastLabel.setText("<html><div style='text-align: center;'><b>" + players[3].getName() + " (" + players[3].getHandSize() + " Cards) </b><br/><br/>Round Points: " + players[3].getPointsFromCurrentRound() + "<br/>Total Points: " + players[3].getTotalPoints() + "</div></html>");

        westCard.removeAll();
        westCard.add(new CardPanel(players[1].getPlayedCard(), false));
        westLabel.setText("<html><div style='text-align: center;'><b>" + players[1].getName() + " (" + players[1].getHandSize() + " Cards) </b><br/><br/>Round Points: " + players[1].getPointsFromCurrentRound() + "<br/>Total Points: " + players[1].getTotalPoints() + "</div></html>");

        validate();
    }
}
