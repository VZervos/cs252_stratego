package view;

import controller.Controller;
import model.Timer;
import model.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Statistics extends JFrame implements Runnable {
    /**
     * Player currently playing
     */
    private Player playerPlaying;
    /**
     * Game's timer
     */
    private final Timer timer;
    /**
     * Timer's thread instance
     */
    private final Thread timerThread;

    /**
     * Creates the statistics window for the first time
     *
     * @post The statistics window is created
     */
    public Statistics() {
        timer = Controller.getTimer();
        timerThread = new Thread(timer);
        timerThread.start();
        updateStatistics();
        setVisible(true);
    }

    /**
     * Updates statistics using Controller
     *
     * @post Statistics are updated based on Controller's values
     */
    public void updateStatistics() {
        // Clear and refill GridLayout
        getContentPane().removeAll();
        playerPlaying = Controller.getNowPlaying();
        JLabel empty = new JLabel();
        empty.setPreferredSize(new Dimension(60, 60));

        ImageIcon backgroundIcon;
        if (playerPlaying.getId() == 1) {
            backgroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                    "/resources/bluePlayerBackground.png")));
        } else {
            backgroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                    "/resources/redPlayerBackground.png")));
        }
        Image image;
        image = backgroundIcon.getImage();
        image = image.getScaledInstance(840, 750, java.awt.Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(image);
        setContentPane(new JLabel(backgroundIcon));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Statistics");
        setBounds(900, 75, 400, 600);
        setLayout(new GridLayout(16, 1));
        setPreferredSize(new Dimension(800, 600));

        JPanel timesection = new JPanel();
        timesection.setBackground(new Color(0, 0, 0, 0));
        timesection.setLayout(new GridLayout(1, 2));
        JLabel timeTitle = new JLabel();
        timeTitle.setText("Timer: ");
        timeTitle.setForeground(Color.WHITE);
        timeTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        timeTitle.setPreferredSize(new Dimension(60, 60));
        timeTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        timesection.add(timeTitle);
        JLabel time = new JLabel();
        time.setText(timer.toString());
        time.setForeground(Color.WHITE);
        time.setHorizontalAlignment(SwingConstants.LEFT);
        time.setPreferredSize(new Dimension(60, 60));
        time.setFont(new Font("Serif", Font.PLAIN, 20));
        time.setBorder(null);
        timesection.add(time);
        timesection.setBorder(null);
        add(timesection);

        add(empty);

        JLabel activeRulesTitle = new JLabel("Active Rules", SwingConstants.CENTER);
        activeRulesTitle.setForeground(Color.WHITE);
        activeRulesTitle.setPreferredSize(new Dimension(60, 60));
        activeRulesTitle.setFont(new Font("Serif", Font.BOLD, 20));
        add(activeRulesTitle);

        JPanel reducedArmy = new JPanel();
        reducedArmy.setBackground(new Color(0, 0, 0, 0));
        reducedArmy.setLayout(new GridLayout(1, 2));
        JLabel reducedArmyText = new JLabel();
        reducedArmyText.setText("Reduced Army: ");
        reducedArmyText.setForeground(Color.WHITE);
        reducedArmyText.setHorizontalAlignment(SwingConstants.RIGHT);
        reducedArmyText.setPreferredSize(new Dimension(60, 60));
        reducedArmyText.setFont(new Font("Serif", Font.PLAIN, 20));
        reducedArmy.add(reducedArmyText);
        JLabel reducedArmySetting = new JLabel();
        reducedArmySetting.setBackground(new Color(0, 0, 0, 0));
        reducedArmySetting.setHorizontalAlignment(SwingConstants.LEFT);
        reducedArmySetting.setForeground(Color.WHITE);
        reducedArmySetting.setPreferredSize(new Dimension(60, 60));
        if (Controller.getReducedArmy()) {
            reducedArmySetting.setText("Yes");
        } else {
            reducedArmySetting.setText("No");
        }
        reducedArmySetting.setFont(new Font("Serif", Font.BOLD, 20));
        reducedArmy.add(reducedArmySetting);
        add(reducedArmy);

        JPanel noRetreat = new JPanel();
        noRetreat.setBackground(new Color(0, 0, 0, 0));
        noRetreat.setLayout(new GridLayout(1, 2));
        JLabel noRetreatText = new JLabel();
        noRetreatText.setText("No Retreat: ");
        noRetreatText.setForeground(Color.WHITE);
        noRetreatText.setHorizontalAlignment(SwingConstants.RIGHT);
        noRetreatText.setPreferredSize(new Dimension(60, 60));
        noRetreatText.setFont(new Font("Serif", Font.PLAIN, 20));
        noRetreat.add(noRetreatText);
        JLabel noRetreatSetting = new JLabel();
        noRetreatSetting.setBackground(new Color(0, 0, 0, 0));
        noRetreatSetting.setHorizontalAlignment(SwingConstants.LEFT);
        noRetreatSetting.setForeground(Color.WHITE);
        noRetreatSetting.setPreferredSize(new Dimension(60, 60));
        if (Controller.getNoRetreat()) {
            noRetreatSetting.setText("Yes");
        } else {
            noRetreatSetting.setText("No");
        }
        noRetreatSetting.setFont(new Font("Serif", Font.BOLD, 20));
        noRetreat.add(noRetreatSetting);
        add(noRetreat);

        add(empty);

        JLabel statisticsTitle = new JLabel("Statistics", SwingConstants.CENTER);
        statisticsTitle.setForeground(Color.WHITE);
        statisticsTitle.setPreferredSize(new Dimension(60, 60));
        statisticsTitle.setFont(new Font("Serif", Font.BOLD, 20));
        add(statisticsTitle);

        JLabel nowPlaying = new JLabel(
                playerPlaying.getName() + "'s turn (Player " + playerPlaying.getId() + ")", SwingConstants.CENTER);
        nowPlaying.setForeground(Color.WHITE);
        nowPlaying.setPreferredSize(new Dimension(60, 60));
        nowPlaying.setFont(new Font("Serif", Font.PLAIN, 20));
        add(nowPlaying);

        JLabel successAttack;
        if (playerPlaying.getTotalAttacks() == 0) {
            successAttack = new JLabel("Successful Attacks: 0.0%", SwingConstants.CENTER);
        } else {
            float successAttackPercentage = (float) (playerPlaying.getSuccessfulAttacks() / playerPlaying.getTotalAttacks() * 100);
            successAttack = new JLabel(
                    "Successful Attacks: " + successAttackPercentage + "%", SwingConstants.CENTER);
        }
        successAttack.setForeground(Color.WHITE);
        successAttack.setPreferredSize(new Dimension(60, 60));
        successAttack.setFont(new Font("Serif", Font.PLAIN, 20));
        add(successAttack);

        JPanel rescuesAndRound = new JPanel();
        rescuesAndRound.setBackground(new Color(0, 0, 0, 0));
        rescuesAndRound.setLayout(new GridLayout(1, 2));
        JLabel rescues = new JLabel();
        rescues.setText("Rescues: " + playerPlaying.getTotalSaves());
        rescues.setForeground(Color.WHITE);
        rescues.setHorizontalAlignment(SwingConstants.CENTER);
        rescues.setPreferredSize(new Dimension(60, 60));
        rescues.setFont(new Font("Serif", Font.PLAIN, 20));
        rescuesAndRound.add(rescues);
        JLabel round = new JLabel();
        round.setText("Round: " + Controller.getRound());
        round.setForeground(Color.WHITE);
        round.setHorizontalAlignment(SwingConstants.CENTER);
        round.setPreferredSize(new Dimension(60, 60));
        round.setFont(new Font("Serif", Font.PLAIN, 20));
        rescuesAndRound.add(round);
        add(rescuesAndRound);

        add(empty);

        JLabel capturesTitle = new JLabel("Captures", SwingConstants.CENTER);
        capturesTitle.setForeground(Color.WHITE);
        capturesTitle.setPreferredSize(new Dimension(60, 60));
        capturesTitle.setFont(new Font("Serif", Font.BOLD, 20));
        add(capturesTitle);

        for (int i = 0; i < 3; i++) {
            JPanel capturesSection = new JPanel();
            capturesSection.setBackground(new Color(0, 0, 0, 0));
            capturesSection.setLayout(new GridLayout(1, 8));
            for (int j = 0; j < 4; j++) {
                JLabel icon = new JLabel();
                setPreferredSize(new Dimension(60, 60));
                if (i < 2) {
                    icon.setIcon(getIcon(i * 4 + j));
                    capturesSection.add(icon);
                    JLabel captures = new JLabel();
                    captures.setText(" " + playerPlaying.getCapturesOf(i * 4 + j));
                    captures.setForeground(Color.WHITE);
                    captures.setHorizontalAlignment(SwingConstants.LEFT);
                    setPreferredSize(new Dimension(60, 60));
                    captures.setFont(new Font("Serif", Font.BOLD, 20));
                    capturesSection.add(captures);
                } else if (i == 2 && (j == 1 || j == 2)) {
                    icon.setIcon(getIcon(i * 4 + (j - 1)));
                    capturesSection.add(icon);
                    JLabel captures = new JLabel();
                    captures.setText(" " + playerPlaying.getCapturesOf(i * 4 + (j - 1)));
                    captures.setForeground(Color.WHITE);
                    setPreferredSize(new Dimension(60, 60));
                    captures.setFont(new Font("Serif", Font.BOLD, 20));
                    capturesSection.add(captures);
                } else {
                    icon = new JLabel();
                    capturesSection.add(icon);
                }
            }
            add(capturesSection);
        }

        JLabel totalCaptures = new JLabel(
                "Total captures: " + playerPlaying.getTotalCaptures(), SwingConstants.CENTER);
        totalCaptures.setForeground(Color.WHITE);
        totalCaptures.setPreferredSize(new Dimension(60, 60));
        totalCaptures.setFont(new Font("Serif", Font.PLAIN, 20));
        add(totalCaptures);

        revalidate();
        repaint();
    }

    /**
     * Gets the icon of the piececode using playerPlaying
     *
     * @param pieceCode Piece's piece code
     * @return pieceCode's icon is returned
     * @post An ImageIcon for the piece is returned
     */
    private ImageIcon getIcon(int pieceCode) {
        ImageIcon icon;
        if (playerPlaying.getId() == 1) {
            if (pieceCode == 0)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/slayerR.png")));
            else if (pieceCode == 1)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/scoutR.png")));
            else if (pieceCode == 2)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/dwarfR.png")));
            else if (pieceCode == 3)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/elfR.png")));
            else if (pieceCode == 4)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/lavaBeast.png")));
            else if (pieceCode == 5)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/sorceressR.png")));
            else if (pieceCode == 6)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/beastRiderR.png")));
            else if (pieceCode == 7)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/knightR.png")));
            else if (pieceCode == 8)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/mageR.png")));
            else
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/dragonR.png")));
        } else {
            if (pieceCode == 0)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/slayerB.png")));
            else if (pieceCode == 1)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/scoutB.png")));
            else if (pieceCode == 2)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/dwarfB.png")));
            else if (pieceCode == 3)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/elfB.png")));
            else if (pieceCode == 4)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/yeti.png")));
            else if (pieceCode == 5)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/sorceressB.png")));
            else if (pieceCode == 6)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/beastRiderB.png")));
            else if (pieceCode == 7)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/knightB.png")));
            else if (pieceCode == 8)
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/mageB.png")));
            else
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/dragonB.png")));
        }
        Image image;
        image = icon.getImage();
        image = image.getScaledInstance(50, 40, java.awt.Image.SCALE_SMOOTH);
        image = image.getScaledInstance(50, 40, Image.SCALE_DEFAULT);
        icon = new ImageIcon(image);
        return icon;
    }


    /**
     * Updates the timer
     *
     * @post Timer is updated
     */
    public void updateTimer() throws InterruptedException {
        while (true) {
            updateStatistics();
            Thread.sleep(1000);
        }
    }

    /**
     * Starts the timer
     *
     * @post Timer is started
     */
    @Override
    public void run() {
        updateStatistics();
        try {
            updateTimer();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Stops the timer
     */
    public void stop() {
        timerThread.stop();
        dispose();
    }
}
