package view;

import controller.Controller;
import model.Board;
import model.piece.stationary.Barrier;
import model.piece.Piece;
import model.piece.movable.MovablePiece;
import model.piece.stationary.Flag;
import model.piece.stationary.Trap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static utility.Utilities.isContained;

public class GameBoard extends JFrame implements ActionListener {

    /**
     * The actual clickable board
     */
    private final PieceButton[][] boardLayout = new PieceButton[Board.BOARD_HEIGHT][Board.BOARD_WIDTH];
    /**
     * Board's layout
     */
    private GridLayout layout;
    /**
     * The piece currently selected
     */
    private PieceButton pieceSelected;
    /**
     * Possible moves the pieceSelected can perform
     */
    private int[][] movesShown;

    /**
     * Creates the board for the first time
     *
     * @post A gameboard frame is created based on Controller's board
     */
    public GameBoard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Stratego: Ice vs Fire");
        setBounds(50, 0, 850, 700);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/resources/gameboardBackground.png")));
        Image image;
        image = icon.getImage();
        image = image.getScaledInstance(840, 750, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        setContentPane(new JLabel(icon));

        layout = new GridLayout(Board.BOARD_HEIGHT, Board.BOARD_WIDTH);
        setLayout(layout);
        setPreferredSize(new Dimension(800, 600));
        PieceButton[][] updatedLayout = convertBoard(Controller.getBoard());
        // Fills GridLayout
        for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Board.BOARD_WIDTH; j++) {
                updatedLayout[i][j].getButton().addActionListener(this);
                boardLayout[i][j] = updatedLayout[i][j];
                add(boardLayout[i][j].getButton());
            }
        }
        pieceSelected = null;
        setVisible(true);
    }

    /**
     * Handles all clicks on the board
     *
     * @param e Click event
     * @post The button click event is handled: piece action performed, available moves shown or pieceSelected reset
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String text = ((JButton) e.getSource()).getName();
        int y = Integer.parseInt(String.valueOf(text.charAt(0)));
        int x = Integer.parseInt(String.valueOf(text.charAt(1)));
        PieceButton pieceButton = boardLayout[y][x];
        Piece piece = pieceButton.getPiece();
        if (hasPieceSelected()) {
            if (isContained(movesShown, y, x)) // Clicked on available position
                Controller.makeMove(pieceSelected.getPiece(), new int[]{y, x});
            clearMoves();
            revalidate();
            repaint();
            pieceSelected = null;
        } else if (!hasPieceSelected() && piece != null &&
                Controller.getNowPlaying().getId() == piece.getOwner().getId()) { // Clicked on movable piece
            if (!(piece instanceof Trap || piece instanceof Flag || piece instanceof Barrier)) {
                movesShown = ((MovablePiece) piece).getMoves();
                selectPiece(pieceButton);
            }
        }
    }

    /**
     * Selects the piece and shows its available moves
     *
     * @param piece Piece to be selected
     * @post Available moves of pieceSelected are shown
     */
    private void selectPiece(PieceButton piece) {
        pieceSelected = piece;
        PieceButton[][] updatedLayout = convertBoard(Controller.getBoard());
        getContentPane().removeAll();
        layout = new GridLayout(Board.BOARD_HEIGHT, Board.BOARD_WIDTH);
        setLayout(layout);
        // Show available positions
        for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Board.BOARD_WIDTH; j++) {
                updatedLayout[i][j].getButton().addActionListener(this);
                if (isContained(movesShown, i, j)) {
                    updatedLayout[i][j].getButton().setBorder(new LineBorder(Color.GREEN));
                    updatedLayout[i][j].getButton().setBorderPainted(true);
                }
                boardLayout[i][j].setPiece(updatedLayout[i][j].getPiece());
                boardLayout[i][j].setButton(updatedLayout[i][j].getButton());
                add(boardLayout[i][j].getButton());
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Checks if a movable piece is selected
     *
     * @return True if a movable piece is selected, false if not
     * @post Whether a movable piece is selected is returned
     */
    private boolean hasPieceSelected() {
        if (pieceSelected != null) {
            Piece piece = pieceSelected.getPiece();
            return !(piece instanceof Barrier
                    || piece instanceof Trap
                    || piece instanceof Flag);
        }
        return false;
    }

    /**
     * Converts a board of Pieces to a board of PieceButtons
     *
     * @param board Board to be converted
     * @return Board of PieceButtons
     * @pre Board!=Null
     * @post Returns the same board as PieceButtons
     */
    public PieceButton[][] convertBoard(Board board) {
        PieceButton[][] pieceButtons = new PieceButton[Board.BOARD_HEIGHT][Board.BOARD_WIDTH];
        for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Board.BOARD_WIDTH; j++) {
                pieceButtons[i][j] = new PieceButton(board.getPieceAt(i, j), i, j);
            }
        }
        return pieceButtons;
    }

    /**
     * Updates the GameBoard View to the given board
     *
     * @param board Board to be updated to
     * @pre board!=Null
     * @post GameBoard View is updated based on board
     */
    public void updateBoardView(Board board) {
        PieceButton[][] updatedLayout = convertBoard(board);
        getContentPane().removeAll(); // Clear GridLayout
        layout = new GridLayout(Board.BOARD_HEIGHT, Board.BOARD_WIDTH);
        setLayout(layout);
        // Refill it
        for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Board.BOARD_WIDTH; j++) {
                updatedLayout[i][j].getButton().addActionListener(this);
                boardLayout[i][j].setPiece(updatedLayout[i][j].getPiece());
                boardLayout[i][j].setButton(updatedLayout[i][j].getButton());
                add(boardLayout[i][j].getButton());
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Clears available moves shown on the board
     *
     * @post Board has no marked squares (available moves are cleared)
     */
    protected void clearMoves() {
        PieceButton[][] updatedLayout = convertBoard(Controller.getBoard());
        // Same logic as updateBoard()
        getContentPane().removeAll();
        layout = new GridLayout(Board.BOARD_HEIGHT, Board.BOARD_WIDTH);
        setLayout(layout);
        for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Board.BOARD_WIDTH; j++) {
                updatedLayout[i][j].getButton().addActionListener(this);
                if (isContained(movesShown, i, j)) {
                    updatedLayout[i][j].getButton().setBorder(new LineBorder(Color.GRAY));
                }
                boardLayout[i][j].setPiece(updatedLayout[i][j].getPiece());
                boardLayout[i][j].setButton(updatedLayout[i][j].getButton());
                add(boardLayout[i][j].getButton());
            }
        }
    }
}
