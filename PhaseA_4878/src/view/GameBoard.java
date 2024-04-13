package view;

import controller.Controller;
import model.Board;
import model.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame implements ActionListener {

    /**
     * The actual clickable board
     */
    private PieceButton[][] boardLayout;
    public GameBoard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Activates "X" button
        setTitle ("Stratego: Ice vs Fire");
        setBounds(200,100,800,600); //x, y, width, height)
        setLayout(new GridLayout(Board.BOARD_WIDTH, Board.BOARD_HEIGHT)); // rows, columns
        setPreferredSize(new Dimension(800, 600));
        updateBoardView(Controller.getBoard());

        setVisible(true);
    }

    /**
     * Handles all clicks on the board
     * @param e Click event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TO-DO: Add checks
        /*String player1 = this.player1TextField.getText(), player2 = this.player2TextField.getText();
        boolean reducedArmy = this.reducedArmyCheckBox.isSelected(), noRetreat = this.noRetreatCheckBox.isSelected();
        System.out.println(player1 + player2 + reducedArmy + noRetreat);
        Controller c = new Controller();
        c.startGame(player1, player2, reducedArmy, noRetreat);
        dispose();*/
    }

    /**
     * Converts a board of Pieces to a board of PieceButtons
     * @param board Board to be converted
     * @return Board of PieceButtons
     * @pre Board!=Null
     * @post Returns the same board as PieceButtons
     */
    public PieceButton[][] convertBoard(Board board) {
        PieceButton[][] pieceButtons = new PieceButton[Board.BOARD_HEIGHT][Board.BOARD_WIDTH];
        for (int i=0; i<Board.BOARD_HEIGHT; i++) {
            for (int j=0; j<Board.BOARD_WIDTH; j++) {
                pieceButtons[i][j] = new PieceButton(board.getPieceAt(i, j));
            }
        }
        return pieceButtons;
    }

    /**
     * Updates the GameBoard View to the given board
     * @param board Board to be updated to
     * @pre board!=Null
     * @post GameBoard View is updated based on board
     */
    public void updateBoardView(Board board) {
        PieceButton[][] updatedLayout = convertBoard(board);
        setLayout(new GridLayout(Board.BOARD_WIDTH, Board.BOARD_HEIGHT));
        for (int i=0; i<Board.BOARD_HEIGHT; i++) {
            for (int j=0; j<Board.BOARD_WIDTH; j++) {
                add(updatedLayout[i][j]);
            }
        }
        dispose();
        setVisible(true);
    }
}
