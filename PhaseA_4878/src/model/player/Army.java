package model.player;

import model.piece.Piece;

import java.util.ArrayList;

public class Army {
    /**
     * All different piece types (excludes flag)
     */
    public static final int PIECE_TYPES = 11;
    /**
     * Default number of each piece in an army. Trap is ALL_PIECES[10]=6
     */
    public static final int[] DEFAULT_PIECES = new int[]{1, 4, 5, 2, 2, 2, 3, 2, 1, 1, 6};
    /**
     * Owner of the army
     */
    private Player owner;
    /**
     * Active pieces of each type
     * @inv >=0
     */
    private int[] activePieces = new int[11];
    /**
     * An array that contains an array of each piece type containing active pieces of that type
     */
    private ArrayList<ArrayList<Piece>> pieces;

    /**
     * Sets the owner of the army
     * @param owner Owner to be set
     * @pre owner!=Null
     * @post Army's owner set to given player
     */
    public void setOwner(Player owner) {
        if (owner==null) throw new IllegalArgumentException("No player is assigned.");
        else this.owner=owner;
    }

    /**
     * Gets the owner of the army
     * @return Owner of the army
     * @post Owner of the army is returned
     */
    public Player getOwner() {return this.owner;}
    /**
     * Gets piece code of a piece
     * @param piece Piece to be analyzed
     * @return 0 slayer, 1 for scout, ..., 8 for wizard, 9 for dragon and 10 for trap
     * @pre piece!=Null
     * @post Returns int piece code of the given piece
     */
    public int getPieceCode(Piece piece) {return 0;}

    /**
     * Removes a piece from the army
     * @param piece Piece to be removed
     * @pre piece!=Null
     * @post Piece is removed from pieces and active pieces of that type is reduced by 1
     */
    public void removePiece(Piece piece) {}

    /**
     * Adds a piece to the army
     * @param piece Piece to be added
     * @pre piece!=Null
     * @post Piece is added to pieces and active pieces of tha ttype is increased by 1
     */
    public void addPiece(Piece piece) {}

    /**
     * Returns active piece of the given type
     * @param piece Piece code of requested type
     * @return Number of active pieces of that type
     * @pre piece[0,10]
     * @post Returns the number of active pieces of that type
     */
    public int getNumberOfPieces(int piece) {return 0;}

    /**
     * Checks if there are movable pieces left
     * @return True if there are active movable pieces
     * @post Returns whether there are active movable pieces
     */
    public boolean hasMovablePieces() {return false;}

    /**
     * Creates an army based on the given mode
     * @param mode false=No Reduced Army, true=ReducedArmy
     */
    public void createArmy(boolean mode) {}

    /**
     * Resets the army
     * @post Army is empty
     */
    public void resetArmy() {}

    /**
     * Checks whether there are pieces to be rescued
     * @return True if there are savable pieces
     * @post Returns whether there are pieces that can be rescued
     */
    public boolean hasAvailableRescues() {return false;}

    /**
     * Creates a list of pieces that can be rescued from each type
     * @return Array of number of savable pieces of each type
     * @post Returns a list pieces that can be saved
     */
    public int[] getAvailableRescues() {
        return new int[0];
    }

    /**
     * Creates an army for the given mode
     * @param mode false=No Reduced Army, true=ReducedArmy
     */
    public Army(boolean mode) {
        this.createArmy(mode);
    }
}
