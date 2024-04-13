package view;

import controller.Controller;
import exception.InvalidPlayerException;
import model.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class RescueSelector extends JFrame implements ActionListener {
    /**
     * Piece rescued selector
     */
    private JComboBox selector;
    /**
     * Available pieces' codes
     */
    private int[] rescuePieceCodes;
    /**
     * The player the selector is running for
     */
    private final Player player;

    /**
     * Creates a rescueselector for player p
     *
     * @param p       Player the selector is created for
     * @param rescues Available rescues list
     * @pre p!=null
     * @post A rescue selector window for player p is created
     */
    public RescueSelector(Player p, int[] rescues) {
        if (p == null) throw new InvalidPlayerException("Invalid player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Select piece to rescue");
        setBounds(200, 100, 400, 400);

        ImageIcon backgroundIcon;
        Image image;
        if (p.getId() == 1) {
            backgroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                    "/resources/bluePlayerBackground.png")));
        } else {
            backgroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                    "/resources/redPlayerBackground.png")));
        }
        image = backgroundIcon.getImage();
        image = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(image);
        setContentPane(new JLabel(backgroundIcon));

        setLayout(new GridLayout(3, 1));
        setPreferredSize(new Dimension(800, 600));

        JLabel title = new JLabel("Select piece to rescue", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(200, 200));
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(Color.YELLOW);
        add(title);
        player = p;

        createSelector(player, rescues);
        add(selector);

        JButton rescueButton = new JButton();
        rescueButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/resources/save.png"))));
        rescueButton.setBorder(null);
        rescueButton.setFocusPainted(false);
        rescueButton.setContentAreaFilled(false);
        rescueButton.setBorderPainted(false);
        rescueButton.setBackground(new Color(0, 0, 0, 0));
        rescueButton.setPreferredSize(new Dimension(200, 200));
        rescueButton.setFont(new Font("Serif", Font.BOLD, 30));
        rescueButton.addActionListener(this);
        rescueButton.setBorder(null);
        rescueButton.setFocusPainted(false);
        rescueButton.setContentAreaFilled(false);
        rescueButton.setBorderPainted(false);
        add(rescueButton);

        setVisible(true);
    }

    /**
     * Creates the piece selector
     *
     * @param player  The player the selector is to be created for
     * @param rescues Available rescues of the player
     * @post The selector JComboBox is created
     */
    public void createSelector(Player player, int[] rescues) {
        ArrayList<String> piecesToBeRescuedList = new ArrayList<String>();
        ArrayList<Integer> rescuePieceCodeList = new ArrayList<Integer>();
        if (rescues[0] > 0) {
            piecesToBeRescuedList.add("Slayer");
            rescuePieceCodeList.add(0);
        }
        if (rescues[1] > 0) {
            piecesToBeRescuedList.add("Scout");
            rescuePieceCodeList.add(1);
        }
        if (rescues[2] > 0) {
            piecesToBeRescuedList.add("Dwarf");
            rescuePieceCodeList.add(2);
        }
        if (rescues[3] > 0) {
            piecesToBeRescuedList.add("Elf");
            rescuePieceCodeList.add(3);
        }
        if (rescues[4] > 0) {
            if (player.getId() == 1)
                piecesToBeRescuedList.add("Yeti");
            else
                piecesToBeRescuedList.add("LavaBeast");
            rescuePieceCodeList.add(4);
        }
        if (rescues[5] > 0) {
            piecesToBeRescuedList.add("Sorceress");
            rescuePieceCodeList.add(5);
        }
        if (rescues[6] > 0) {
            piecesToBeRescuedList.add("BeastRider");
            rescuePieceCodeList.add(6);
        }
        if (rescues[7] > 0) {
            piecesToBeRescuedList.add("Knight");
            rescuePieceCodeList.add(7);
        }
        if (rescues[8] > 0) {
            piecesToBeRescuedList.add("Mage");
            rescuePieceCodeList.add(8);
        }
        if (rescues[9] > 0) {
            piecesToBeRescuedList.add("Dragon");
            rescuePieceCodeList.add(9);
        }
        int amount = piecesToBeRescuedList.size();
        String[] piecesToBeRescued = new String[amount];
        rescuePieceCodes = new int[amount];
        for (int i = 0; i < amount; i++) {
            piecesToBeRescued[i] = piecesToBeRescuedList.get(i);
            rescuePieceCodes[i] = rescuePieceCodeList.get(i);
        }
        selector = new JComboBox(piecesToBeRescued);
        selector.setBackground(new Color(0, 0, 0, 0));
        selector.setPreferredSize(new Dimension(200, 200));
        selector.setFont(new Font("Serif", Font.BOLD, 30));
    }

    /**
     * Handles the rescue button click event
     *
     * @param e Button click event
     * @post This window is closed and requested piece is sent to be rescued
     */
    public void actionPerformed(ActionEvent e) {
        int selectedPiece = selector.getSelectedIndex();
        Controller.rescue(player, rescuePieceCodes[selectedPiece]);
        dispose();
    }
}
