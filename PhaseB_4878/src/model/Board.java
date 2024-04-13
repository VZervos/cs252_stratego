package model;

import controller.Controller;
import exception.InvalidPieceException;
import exception.InvalidPlayerException;
import exception.InvalidPositionException;
import model.piece.stationary.Barrier;
import model.piece.Piece;
import model.piece.stationary.Flag;
import model.player.Army;
import model.player.Player;
import utility.Utilities;

import java.util.ArrayList;

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
    private static Piece[][] board;

    /**
     * Creates an empty board
     *
     * @post An empty board is created
     */
    public Board(Player player1, Player player2) {
        board = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
        Board.setArmyOnBoard(player1);
        Board.setArmyOnBoard(player2);
        board[3][2] = new Barrier(new int[]{3, 2});
        board[3][3] = new Barrier(new int[]{3, 3});
        board[4][2] = new Barrier(new int[]{4, 2});
        board[4][3] = new Barrier(new int[]{4, 3});
        board[3][6] = new Barrier(new int[]{3, 6});
        board[3][7] = new Barrier(new int[]{3, 7});
        board[4][6] = new Barrier(new int[]{4, 6});
        board[4][7] = new Barrier(new int[]{4, 7});
    }

    /**
     * Checks whether the position is restricted
     *
     * @param y Y
     * @param x X
     * @return True if it restricted, false if not
     * @post Returns whether the position is restricted
     */
    public static boolean isRestrictedArea(int y, int x) {
        return (!isCoordValid(y, x) || (y == 3 && x == 2) || (y == 3 && x == 3) || (y == 4 && x == 2) || (y == 4 && x == 3)
                || (y == 3 && x == 6) || (y == 3 && x == 7) || (y == 4 && x == 6) || (y == 4 && x == 7));
    }

    /**
     * Returns the piece located at the given position
     *
     * @param y Y
     * @param x X
     * @return The piece located at that position
     * @pre x[0, 9] && y[0,7]
     * @post Returns the piece located there
     */
    public static Piece getPieceAt(int y, int x) {
        if (!isCoordValid(y, x)) throw new InvalidPositionException("Invalid position");
        return board[y][x];
    }

    /**
     * Places a piece at the given position
     *
     * @param y     Y
     * @param x     X
     * @param piece Piece to be placed
     * @pre x[0, 9] && y[0,7] && !isRestricted(y, x)
     * @post The given position contains the given piece
     */
    public static void setPieceAt(int y, int x, Piece piece) {
        if (!isCoordValid(y, x) || isRestrictedArea(y, x))
            throw new InvalidPositionException("Invalid coords");
        else {
            board[y][x] = piece;
            if (piece != null) piece.setPosition(new int[]{y, x});
        }
    }

    /**
     * Sets the army of a player randomly on the board
     *
     * @param player Player whose army is to be set
     * @pre player!=Null
     * @post Player's army placed randomly on the board based on their id
     */
    public static void setArmyOnBoard(Player player) {
        if (player == null) throw new InvalidPlayerException("Invalid player");
        int id = player.getId();
        Army army = player.getArmy();
        int[] activeArmyPieces = army.getActiveArmyPieces();
        ArrayList<ArrayList<Piece>> armyPieces = army.getArmyPieces();

        if (!Controller.getReducedArmy()) {
            if (id == 1) {
                setPieceAt(0, 5, new Flag(player, new int[]{0, 5}));
                // For each piece in the army list, it places it randomly in player's area
                for (int i = 0; i < Army.PIECE_TYPES; i++) {
                    for (int j = 0; j < activeArmyPieces[i]; j++) {
                        Piece piece = armyPieces.get(i).get(j);
                        boolean done = false;
                        do {
                            int y = Utilities.getRandom(0, 3);
                            int x = Utilities.getRandom(0, 10);
                            if (getPieceAt(y, x) == null) {
                                setPieceAt(y, x, piece);
                                done = true;
                            }
                        } while (!done);
                    }
                }
            } else if (id == 2) { // Other player
                setPieceAt(7, 5, new Flag(player, new int[]{7, 5}));
                for (int i = 0; i < Army.PIECE_TYPES; i++) {
                    for (int j = 0; j < activeArmyPieces[i]; j++) {
                        Piece piece = armyPieces.get(i).get(j);
                        boolean done = false;
                        do {
                            int y = Utilities.getRandom(5, 8);
                            int x = Utilities.getRandom(0, 10);
                            if (getPieceAt(y, x) == null) {
                                setPieceAt(y, x, piece);
                                done = true;
                            }
                        } while (!done);
                    }
                }
            } else
                throw new IllegalArgumentException("Invalid id");
        } else {
            if (id == 1) {
                setPieceAt(0, 5, new Flag(player, new int[]{0, 5}));
                // Same but with 1 row less for Reduced Army
                for (int i = 0; i < Army.PIECE_TYPES; i++) {
                    for (int j = 0; j < activeArmyPieces[i]; j++) {
                        Piece piece = armyPieces.get(i).get(j);
                        boolean done = false;
                        do {
                            int y = Utilities.getRandom(0, 2);
                            int x = Utilities.getRandom(0, 10);
                            if (getPieceAt(y, x) == null) {
                                setPieceAt(y, x, piece);
                                done = true;
                            }
                        } while (!done);
                    }
                }
            } else if (id == 2) { // Other player
                setPieceAt(7, 5, new Flag(player, new int[]{7, 5}));
                for (int i = 0; i < Army.PIECE_TYPES; i++) {
                    for (int j = 0; j < activeArmyPieces[i]; j++) {
                        Piece piece = armyPieces.get(i).get(j);
                        boolean done = false;
                        do {
                            int y = Utilities.getRandom(6, 8);
                            int x = Utilities.getRandom(0, 10);
                            if (getPieceAt(y, x) == null) {
                                setPieceAt(y, x, piece);
                                done = true;
                            }
                        } while (!done);
                    }
                }
            } else
                throw new IllegalArgumentException("Invalid id");
        }
    }

    /**
     * Places a single piece randomly on board based on the player it belongs to
     *
     * @param piece Piece to be placed
     * @return True on success, false if failed
     * @pre piece!=null
     * @post Piece is placed randomly in player's army area
     */
    public static boolean setPieceOnBoardRandomly(Piece piece) {
        boolean done = false;
        int tries = 0;
        if (piece == null) throw new InvalidPieceException("Invalid piece");
        if (piece.getOwner().getId() == 1) { // player1
            do {
                tries++;
                int y = Utilities.getRandom(0, 3);
                int x = Utilities.getRandom(0, 10);
                if (getPieceAt(y, x) == null) {
                    setPieceAt(y, x, piece);
                    done = true;
                } else if (tries == 1000)
                    return false;
            } while (!done);
        } else { // player2
            do {
                tries++;
                int y = Utilities.getRandom(5, 8);
                int x = Utilities.getRandom(0, 10);
                if (getPieceAt(y, x) == null) {
                    setPieceAt(y, x, piece);
                    done = true;
                } else if (tries == 1000)
                    return false;
            } while (!done);
        }
        return true;
    }

    /**
     * Checks if the given coords are valid
     *
     * @param y Y
     * @param x X
     * @return True if valid, false if not
     * @post Returns if the given coords are valid
     */
    public static boolean isCoordValid(int y, int x) {
        return !(y < 0 || y >= BOARD_HEIGHT || x < 0 || x >= BOARD_WIDTH);
    }
}
