package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    /**
     * Player 1's name input field
     */
    private JTextField player1TextField;
    /**
     * Player 2's name input field
     */
    private JTextField player2TextField;
    /**
     * Checkbox for Reduced Army setting
     */
    private JCheckBox reducedArmyCheckBox;
    /**
     * Checkbox for No Retreat setting
     */
    private JCheckBox noRetreatCheckBox;
    /**
     * PLAY button
     */
    private JButton playButton;

    public MainMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Activates "X" button
        setTitle ("Stratego: Ice vs Fire");
        setBounds(200,100,800,600); //x, y, width, height)
        setLayout(new GridLayout(8, 1)); // rows, columns
        setPreferredSize(new Dimension(800, 600));

        JLabel title = new JLabel("Stratego", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(200, 200));
        title.setFont(new Font("Serif", Font.BOLD, 40));
        add(title);

        JPanel subtitle = new JPanel();
        subtitle.setLayout(new GridLayout(1, 3));
        JLabel subtitleIce = new JLabel("Ice", SwingConstants.RIGHT);
        subtitleIce.setPreferredSize(new Dimension(200, 200));
        subtitleIce.setFont(new Font("Serif", Font.BOLD, 30));
        subtitle.add(subtitleIce);
        JLabel subtitleVS = new JLabel("vs", SwingConstants.CENTER);
        subtitleVS.setPreferredSize(new Dimension(200, 200));
        subtitleVS.setFont(new Font("Serif", Font.BOLD, 30));
        subtitle.add(subtitleVS);
        JLabel subtitleFire = new JLabel("Fire", SwingConstants.LEFT);
        subtitleFire.setPreferredSize(new Dimension(200, 200));
        subtitleFire.setFont(new Font("Serif", Font.BOLD, 30));
        subtitle.add(subtitleFire);
        add(subtitle);

        JPanel player1NameIn = new JPanel();
        player1NameIn.setLayout(new GridLayout(1, 4));
        player1NameIn.add(new JLabel());
        JLabel player1Label = new JLabel("Player 1:   ", SwingConstants.RIGHT);
        player1Label.setPreferredSize(new Dimension(150, 150));
        player1Label.setFont(new Font("Serif", Font.PLAIN, 30));
        player1NameIn.add(player1Label);
        player1TextField = new JTextField();
        player1TextField.setPreferredSize(new Dimension(150, 150));
        player1Label.setFont(new Font("Serif", Font.PLAIN, 20));
        player1NameIn.add(player1TextField);
        player1NameIn.add(new JLabel());
        add(player1NameIn);

        JPanel player2NameIn = new JPanel();
        player2NameIn.setLayout(new GridLayout(1, 4));
        player2NameIn.add(new JLabel());
        JLabel player2Label = new JLabel("Player 2:   ", SwingConstants.RIGHT);
        player2Label.setPreferredSize(new Dimension(150, 150));
        player2Label.setFont(new Font("Serif", Font.PLAIN, 30));
        player2NameIn.add(player2Label);
        player2TextField = new JTextField();
        player2TextField.setPreferredSize(new Dimension(150, 150));
        player2Label.setFont(new Font("Serif", Font.PLAIN, 20));
        player2NameIn.add(player2TextField);
        player2NameIn.add(new JLabel());
        add(player2NameIn);

        JPanel reducedArmyIn = new JPanel();
        reducedArmyIn.setLayout(new GridLayout(1, 4));
        reducedArmyIn.add(new JLabel());
        JLabel reducedArmyLabel = new JLabel("Reduced Army:   ", SwingConstants.RIGHT);
        reducedArmyLabel.setPreferredSize(new Dimension(150, 150));
        reducedArmyLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        reducedArmyIn.add(reducedArmyLabel);
        reducedArmyCheckBox = new JCheckBox();
        reducedArmyCheckBox.setPreferredSize(new Dimension(150, 150));
        reducedArmyIn.add(reducedArmyCheckBox);
        reducedArmyIn.add(new JLabel());
        add(reducedArmyIn);

        JPanel noRetreatIn = new JPanel();
        noRetreatIn.setLayout(new GridLayout(1, 4));
        noRetreatIn.add(new JLabel());
        JLabel noRetreatLabel = new JLabel("No Retreat:   ", SwingConstants.RIGHT);
        noRetreatLabel.setPreferredSize(new Dimension(150, 150));
        noRetreatLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        noRetreatIn.add(noRetreatLabel);
        noRetreatCheckBox = new JCheckBox();
        noRetreatCheckBox.setPreferredSize(new Dimension(150, 150));
        noRetreatIn.add(noRetreatCheckBox);
        noRetreatIn.add(new JLabel());
        add(noRetreatIn);

        playButton = new JButton("PLAY");
        playButton.setPreferredSize(new Dimension(200, 200));
        playButton.setFont(new Font("Serif", Font.BOLD, 30));
        playButton.addActionListener(this);
        add(playButton);

        setVisible(true);
    }

    /**
     * PLAY button handler
     * @param e Button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TO-DO: Add checks
        String player1 = this.player1TextField.getText(), player2 = this.player2TextField.getText();
        boolean reducedArmy = this.reducedArmyCheckBox.isSelected(), noRetreat = this.noRetreatCheckBox.isSelected();
        System.out.println(player1 + player2 + reducedArmy + noRetreat);
        Controller c = new Controller();
        c.startGame(player1, player2, reducedArmy, noRetreat);
        dispose();
    }
}
