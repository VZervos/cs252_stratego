package view;

import controller.Controller;
import model.player.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameEnd extends JFrame implements ActionListener {

    /**
     * Play again button
     */
    private final JButton replayButton;
    /**
     * Exit game button
     */
    private final JButton exitButton;

    /**
     * Creates the game end frame with player as the winner
     *
     * @param winner Winner of the game
     * @post A game end window showing the final stats based on winner is created
     */
    public GameEnd(Player winner) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Stratego: Ice vs Fire");
        setBounds(225, 0, 800, 800);
        ImageIcon backgroundIcon;
        Image image;
        if (winner != null && winner.getId() == 1) {
            backgroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                    "/resources/bluePlayerBackground.png")));
            image = backgroundIcon.getImage();
            image = image.getScaledInstance(840, 750, java.awt.Image.SCALE_SMOOTH);
            backgroundIcon = new ImageIcon(image);
            setContentPane(new JLabel(backgroundIcon));
        } else if (winner != null) {
            backgroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                    "/resources/redPlayerBackground.png")));
            image = backgroundIcon.getImage();
            image = image.getScaledInstance(840, 750, java.awt.Image.SCALE_SMOOTH);
            backgroundIcon = new ImageIcon(image);
            setContentPane(new JLabel(backgroundIcon));
        } else {
            setBackground(Color.yellow);
        }

        setLayout(new GridLayout(15, 1)); 
        setPreferredSize(new Dimension(800, 800));

        JLabel title = new JLabel("GAME OVER", SwingConstants.CENTER);
        title.setForeground(Color.YELLOW);
        title.setPreferredSize(new Dimension(200, 200));
        title.setFont(new Font("Serif", Font.BOLD, 60));
        add(title);

        JLabel empty = new JLabel();
        add(empty);

        JLabel winnerTitle = new JLabel("Winner", SwingConstants.CENTER);
        winnerTitle.setForeground(Color.YELLOW);
        winnerTitle.setPreferredSize(new Dimension(200, 200));
        winnerTitle.setFont(new Font("Serif", Font.BOLD, 50));
        add(winnerTitle);

        JLabel winnerNameTitle = new JLabel();
        if (winner != null) {
            winnerNameTitle.setText(winner.getName());
        } else {
            winnerNameTitle.setText("Draw");
        }
        winnerNameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        winnerNameTitle.setForeground(Color.YELLOW);
        winnerNameTitle.setPreferredSize(new Dimension(200, 200));
        winnerNameTitle.setFont(new Font("Serif", Font.PLAIN, 40));
        add(winnerNameTitle);

        add(empty);
        JLabel empty2 = new JLabel();
        add(empty2);

        JPanel generalStatsSection = new JPanel();
        generalStatsSection.setBackground(new Color(0, 0, 0, 0));
        generalStatsSection.setLayout(new GridLayout(1, 2));
        JLabel rounds = new JLabel("Rounds: " + Controller.getRound(), SwingConstants.CENTER);
        rounds.setForeground(Color.YELLOW);
        rounds.setPreferredSize(new Dimension(60, 60));
        rounds.setFont(new Font("Serif", Font.PLAIN, 20));
        generalStatsSection.add(rounds);
        JLabel timeTaken = new JLabel("Time Taken: " + Controller.getTimer().toString(), SwingConstants.CENTER);
        timeTaken.setPreferredSize(new Dimension(60, 60));
        timeTaken.setForeground(Color.YELLOW);
        timeTaken.setFont(new Font("Serif", Font.PLAIN, 20));
        generalStatsSection.add(timeTaken);
        add(generalStatsSection);

        JPanel player1Stats = new JPanel();
        player1Stats.setBackground(Color.YELLOW);
        player1Stats.setLayout(new GridLayout(1, 1));
        JLabel player1StatsTitle = new JLabel(Controller.getPlayer1().getName() + "'s Stats", SwingConstants.CENTER);
        player1StatsTitle.setForeground(Color.blue);
        player1StatsTitle.setBackground(Color.yellow);
        player1StatsTitle.setPreferredSize(new Dimension(200, 200));
        player1StatsTitle.setFont(new Font("Serif", Font.BOLD, 25));
        player1Stats.add(player1StatsTitle);
        add(player1Stats);

        JPanel player1StatsSection1 = new JPanel();
        player1StatsSection1.setBackground(Color.yellow);
        player1StatsSection1.setLayout(new GridLayout(1, 2));
        JLabel p1Attacks = new JLabel("Attacks: " + Controller.getPlayer1().getTotalAttacks(), SwingConstants.CENTER);
        p1Attacks.setPreferredSize(new Dimension(60, 60));
        p1Attacks.setForeground(Color.BLUE);
        p1Attacks.setFont(new Font("Serif", Font.PLAIN, 20));
        player1StatsSection1.add(p1Attacks);
        JLabel p1SuccessfulAttacks = new JLabel("Successful Attacks: " + Controller.getPlayer1().getSuccessfulAttacks(), SwingConstants.CENTER);
        p1SuccessfulAttacks.setPreferredSize(new Dimension(60, 60));
        p1SuccessfulAttacks.setForeground(Color.blue);
        p1SuccessfulAttacks.setFont(new Font("Serif", Font.PLAIN, 20));
        player1StatsSection1.add(p1SuccessfulAttacks);
        add(player1StatsSection1);
        JPanel player1StatsSection2 = new JPanel();
        player1StatsSection2.setBackground(Color.yellow);
        player1StatsSection2.setLayout(new GridLayout(1, 2));
        JLabel p1Saves = new JLabel("Saves: " + Controller.getPlayer1().getTotalSaves(), SwingConstants.CENTER);
        p1Saves.setPreferredSize(new Dimension(60, 60));
        p1Saves.setForeground(Color.blue);
        p1Saves.setFont(new Font("Serif", Font.PLAIN, 20));
        player1StatsSection2.add(p1Saves);
        JLabel p1Captures = new JLabel("Captures: " + Controller.getPlayer1().getTotalCaptures(), SwingConstants.CENTER);
        p1Captures.setPreferredSize(new Dimension(60, 60));
        p1Captures.setForeground(Color.blue);
        p1Captures.setFont(new Font("Serif", Font.PLAIN, 20));
        player1StatsSection2.add(p1Captures);
        add(player1StatsSection2);

        JPanel player2Stats = new JPanel();
        player2Stats.setBackground(Color.YELLOW);
        player2Stats.setLayout(new GridLayout(1, 1));
        JLabel player2StatsTitle = new JLabel(Controller.getPlayer2().getName() + "'s Stats", SwingConstants.CENTER);
        player2StatsTitle.setForeground(Color.red);
        player2StatsTitle.setBackground(Color.yellow);
        player2StatsTitle.setPreferredSize(new Dimension(200, 200));
        player2StatsTitle.setFont(new Font("Serif", Font.BOLD, 25));
        player2Stats.add(player2StatsTitle);
        add(player2Stats);

        JPanel player2StatsSection1 = new JPanel();
        player2StatsSection1.setBackground(Color.yellow);
        player2StatsSection1.setLayout(new GridLayout(1, 2));
        JLabel p2Attacks = new JLabel("Attacks: " + Controller.getPlayer2().getTotalAttacks(), SwingConstants.CENTER);
        p2Attacks.setPreferredSize(new Dimension(60, 60));
        p2Attacks.setForeground(Color.red);
        p2Attacks.setFont(new Font("Serif", Font.PLAIN, 20));
        player2StatsSection1.add(p2Attacks);
        JLabel p2SuccessfulAttacks = new JLabel("Successful Attacks: " + Controller.getPlayer2().getSuccessfulAttacks(), SwingConstants.CENTER);
        p2SuccessfulAttacks.setPreferredSize(new Dimension(60, 60));
        p2SuccessfulAttacks.setForeground(Color.red);
        p2SuccessfulAttacks.setFont(new Font("Serif", Font.PLAIN, 20));
        player2StatsSection1.add(p2SuccessfulAttacks);
        add(player2StatsSection1);
        JPanel player2StatsSection2 = new JPanel();
        player2StatsSection2.setBackground(Color.yellow);
        player2StatsSection2.setLayout(new GridLayout(1, 2));
        JLabel p2Saves = new JLabel("Saves: " + Controller.getPlayer2().getTotalSaves(), SwingConstants.CENTER);
        p2Saves.setPreferredSize(new Dimension(60, 60));
        p2Saves.setForeground(Color.red);
        p2Saves.setFont(new Font("Serif", Font.PLAIN, 20));
        player2StatsSection2.add(p2Saves);
        JLabel p2Captures = new JLabel("Captures: " + Controller.getPlayer2().getTotalCaptures(), SwingConstants.CENTER);
        p2Captures.setPreferredSize(new Dimension(60, 60));
        p2Captures.setForeground(Color.red);
        p2Captures.setFont(new Font("Serif", Font.PLAIN, 20));
        player2StatsSection2.add(p2Captures);
        add(player2StatsSection2);

        replayButton = new JButton();
        ImageIcon replayIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/resources/replay.png")));
        Image replayImage = replayIcon.getImage();
        replayImage = replayImage.getScaledInstance(120, 75, java.awt.Image.SCALE_SMOOTH);
        replayIcon = new ImageIcon(replayImage);
        replayButton.setName("Replay");
        replayButton.setIcon(replayIcon);
        replayButton.setBorder(null);
        replayButton.setFocusPainted(false);
        replayButton.setContentAreaFilled(false);
        replayButton.setBorderPainted(false);
        replayButton.setBackground(new Color(0, 0, 0, 0));
        replayButton.setPreferredSize(new Dimension(200, 200));
        replayButton.setFont(new Font("Serif", Font.BOLD, 30));
        replayButton.addActionListener(this);
        add(replayButton);

        exitButton = new JButton();
        ImageIcon exitIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/resources/exit.png")));
        Image exitImage = exitIcon.getImage();
        exitImage = exitImage.getScaledInstance(120, 75, java.awt.Image.SCALE_SMOOTH);
        exitIcon = new ImageIcon(exitImage);
        exitButton.setName("Exit");
        exitButton.setIcon(exitIcon);
        exitButton.setBorder(null);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setBackground(new Color(0, 0, 0, 0));
        exitButton.setPreferredSize(new Dimension(200, 200));
        exitButton.setFont(new Font("Serif", Font.BOLD, 30));
        exitButton.addActionListener(this);
        add(exitButton);

        setVisible(true);
    }

    /**
     * Handles buttons
     *
     * @param e Button click event
     * @post Restarts the game if replay is clicked, or exits the game
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(replayButton)) {
            dispose();
            Controller.main(null);
        } else {
            System.exit(0);
        }
    }
}
