package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainMenu extends JFrame implements ActionListener {

    /**
     * Player 1's name input field
     */
    private final JTextField player1TextField;
    /**
     * Player 2's name input field
     */
    private final JTextField player2TextField;
    /**
     * Button for Reduced Army setting
     */
    private final JButton reducedArmyButton;
    /**
     * Button for No Retreat setting
     */
    private final JButton noRetreatButton;
    /**
     * PLAY button
     */
    private final JButton playButton;

    /**
     * Creates the main menu
     *
     * @post Main menu/starting window is created
     */
    public MainMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Stratego: Ice vs Fire");
        setBounds(200, 100, 800, 600);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/resources/stratego.jpg")));
        Image image;
        image = icon.getImage();
        image = image.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        setContentPane(new JLabel(icon));

        setLayout(new GridLayout(8, 1));
        setPreferredSize(new Dimension(800, 600));

        for (int i = 0; i < 2; i++) {
            JLabel empty = new JLabel();
            empty.setPreferredSize(new Dimension(150, 450));
            add(empty);
        }

        JLabel subtitle = new JLabel("<html><font color=aqua>Ice</font> <font color=yellow>VS</font> <font color=red>Fire</font></html>", SwingConstants.CENTER);
        subtitle.setPreferredSize(new Dimension(60, 60));
        subtitle.setFont(new Font("Serif", Font.BOLD, 60));
        add(subtitle);

        JPanel player1NameIn = new JPanel();
        player1NameIn.setBackground(new Color(0, 0, 0, 0));
        player1NameIn.setLayout(new GridLayout(1, 4));
        player1NameIn.add(new JLabel());
        JLabel player1Label = new JLabel("Player 1:   ", SwingConstants.RIGHT);
        player1Label.setForeground(Color.YELLOW);
        player1Label.setPreferredSize(new Dimension(150, 150));
        player1Label.setFont(new Font("Serif", Font.BOLD, 30));
        player1NameIn.add(player1Label);
        player1TextField = new JTextField();
        player1TextField.setPreferredSize(new Dimension(150, 150));
        player1Label.setFont(new Font("Serif", Font.PLAIN, 30));
        player1NameIn.add(player1TextField);
        player1NameIn.add(new JLabel());
        add(player1NameIn);

        JPanel player2NameIn = new JPanel();
        player2NameIn.setBackground(new Color(0, 0, 0, 0));
        player2NameIn.setLayout(new GridLayout(1, 4));
        player2NameIn.add(new JLabel());
        JLabel player2Label = new JLabel("Player 2:   ", SwingConstants.RIGHT);
        player2Label.setForeground(Color.YELLOW);
        player2Label.setPreferredSize(new Dimension(150, 150));
        player2Label.setFont(new Font("Serif", Font.BOLD, 30));
        player2NameIn.add(player2Label);
        player2TextField = new JTextField();
        player2TextField.setPreferredSize(new Dimension(150, 150));
        player2Label.setFont(new Font("Serif", Font.PLAIN, 30));
        player2NameIn.add(player2TextField);
        player2NameIn.add(new JLabel());
        add(player2NameIn);

        JPanel reducedArmyIn = new JPanel();
        reducedArmyIn.setBackground(new Color(0, 0, 0, 0));
        reducedArmyIn.setLayout(new GridLayout(1, 4));
        reducedArmyIn.add(new JLabel());
        JLabel reducedArmyLabel = new JLabel("Reduced Army:   ", SwingConstants.RIGHT);
        reducedArmyLabel.setForeground(Color.YELLOW);
        reducedArmyLabel.setPreferredSize(new Dimension(150, 150));
        reducedArmyLabel.setFont(new Font("Serif", Font.BOLD, 25));
        reducedArmyIn.add(reducedArmyLabel);
        reducedArmyButton = new JButton();
        reducedArmyButton.setText("No");
        reducedArmyButton.setFont(new Font("Serif", Font.BOLD, 25));
        reducedArmyButton.setForeground(Color.RED);
        reducedArmyButton.addActionListener(this);
        reducedArmyButton.setPreferredSize(new Dimension(150, 150));
        reducedArmyIn.add(reducedArmyButton);
        reducedArmyIn.add(new JLabel());
        add(reducedArmyIn);

        JPanel noRetreatIn = new JPanel();
        noRetreatIn.setBackground(new Color(0, 0, 0, 0));
        noRetreatIn.setLayout(new GridLayout(1, 4));
        noRetreatIn.add(new JLabel());
        JLabel noRetreatLabel = new JLabel("No Retreat:   ", SwingConstants.RIGHT);
        noRetreatLabel.setForeground(Color.YELLOW);
        noRetreatLabel.setPreferredSize(new Dimension(150, 150));
        noRetreatLabel.setFont(new Font("Serif", Font.BOLD, 25));
        noRetreatIn.add(noRetreatLabel);
        noRetreatButton = new JButton();
        noRetreatButton.setText("No");
        noRetreatButton.setFont(new Font("Serif", Font.BOLD, 25));
        noRetreatButton.setForeground(Color.RED);
        noRetreatButton.addActionListener(this);
        noRetreatButton.setPreferredSize(new Dimension(150, 150));
        noRetreatIn.add(noRetreatButton);
        noRetreatIn.add(new JLabel());
        add(noRetreatIn);

        playButton = new JButton();
        playButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/resources/start.png"))));
        playButton.setBorder(null);
        playButton.setFocusPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setBackground(new Color(0, 0, 0, 0));
        playButton.setPreferredSize(new Dimension(200, 200));
        playButton.setFont(new Font("Serif", Font.BOLD, 30));
        playButton.addActionListener(this);
        add(playButton);

        setVisible(true);
    }

    /**
     * Button click event handler
     *
     * @param e Button click event
     * @post If reducedArmy or noRetreat button is clicked then the setting is toggle, or play is clicked so it starts the game
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(playButton)) {
            String player1 = this.player1TextField.getText(), player2 = this.player2TextField.getText();
            boolean reducedArmy = (this.reducedArmyButton.getText().equals("Yes")),
                    noRetreat = (this.noRetreatButton.getText().equals("Yes"));
            Controller c = new Controller();
            c.startGame(player1, player2, reducedArmy, noRetreat);
            dispose();
        } else if (e.getSource().equals(this.reducedArmyButton)) {
            if (this.reducedArmyButton.getText().equals("Yes")) {
                this.reducedArmyButton.setText("No");
                this.reducedArmyButton.setForeground(Color.RED);
            } else {
                this.reducedArmyButton.setText("Yes");
                this.reducedArmyButton.setForeground(Color.GREEN);
            }
            revalidate();
            repaint();
        } else {
            if (this.noRetreatButton.getText().equals("Yes")) {
                this.noRetreatButton.setText("No");
                this.noRetreatButton.setForeground(Color.RED);
            } else {
                this.noRetreatButton.setText("Yes");
                this.noRetreatButton.setForeground(Color.GREEN);
            }
            revalidate();
            repaint();
        }
    }
}
