package model;

import model.piece.Piece;
import model.player.Player;

public class Board {
    /**
     * Board width (x)
     */
    public static final int BOARD_WIDTH = 10;
    /**
     * Board height (y)
     */
    public static final int BOARD_HEIGHT = 8;
    /**
     * Representation of the board with its pieces
     */
    private Piece[][] board;

    /**
     * Creates an empty board
     */
    public Board(Player player1, Player player2) {
        this.board = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
        this.setArmyOnBoard(player1);
        this.setArmyOnBoard(player2);
    }

    /**
     * Checks whether the position is restricted
     * @param y Y
     * @param x X
     * @return True if it restricted, false if not
     * @pre x[0,9] && y[0,7]
     * @post Returns whether the position is restricted
     */
    public boolean isRestrictedArea(int y, int x) {return false;}

    /**
     * Returns the piece located at the given position
     * @param y Y
     * @param x X
     * @return The piece located at that position
     * @pre x[0,9] && y[0,7]
     * @post Returns the piece located there
     */
    public Piece getPieceAt(int y, int x) {return this.board[y][x];}

    /**
     * Places a piece at the given position
     * @param y Y
     * @param x X
     * @param piece Piece to be placed
     * @pre x[0,9] && y[0,7] && !isRestricted(y, x)
     * @post The given position contains the given piece
     */
    public void setPieceAt(int y, int x, Piece piece) {}

    /**
     * Sets the army of a player randomly on the board
     * @param player Player whose army is to be set
     * @pre player!=Null
     * @post Player's army placed randomly on the board based on their id
     */
    public void setArmyOnBoard(Player player) {}
}
