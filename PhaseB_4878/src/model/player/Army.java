package model.player;

import controller.Controller;
import exception.InvalidPieceException;
import model.piece.Piece;
import model.piece.movable.*;
import model.piece.stationary.Trap;

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
    private final Player owner;
    /**
     * Active pieces of each type
     *
     * @inv >=0
     */
    private final int[] activePieces;
    /**
     * An array that contains an array of each piece type containing active pieces of that type
     */
    private final ArrayList<ArrayList<Piece>> pieces;

    /**
     * Gets the owner of the army
     *
     * @return Owner of the army
     * @post Owner of the army is returned
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Gets piece code of a piece
     *
     * @param piece Piece to be analyzed
     * @return 0 slayer, 1 for scout, ..., 8 for wizard, 9 for dragon and 10 for trap
     * @pre piece!=Null
     * @post Returns int piece code of the given piece
     */
    public static int getPieceCode(Piece piece) {
        if (piece instanceof Trap)
            return 10;
        else
            return ((MovablePiece) piece).getPower() - 1;
    }

    /**
     * Removes a piece from the army
     *
     * @param piece Piece to be removed
     * @pre piece!=Null
     * @post Piece is removed from pieces and active pieces of that type is reduced by 1
     */
    public void removePiece(Piece piece) {
        if (piece == null) throw new InvalidPieceException("Invalid piece");
        int pc = getPieceCode(piece);
        this.pieces.get(pc).remove(piece);
        this.activePieces[pc]--;
    }

    /**
     * Adds a piece to the army
     *
     * @param piece Piece to be added
     * @pre piece!=Null
     * @post Piece is added to pieces and active pieces of tha type is increased by 1
     */
    public void addPiece(Piece piece) {
        int pieceCode = getPieceCode(piece);
        if (piece == null) throw new InvalidPieceException("Invalid piece");
        this.activePieces[pieceCode]++;
        this.pieces.get(pieceCode).add(piece);
    }

    /**
     * Returns active piece of the given type
     *
     * @param piece Piece code of requested type
     * @return Number of active pieces of that type
     * @pre piece[0, 10]
     * @post Returns the number of active pieces of that type
     */
    public int getNumberOfPieces(int piece) {
        return this.activePieces[piece];
    }

    /**
     * Checks if there are movable pieces left
     *
     * @return True if there are active movable pieces
     * @post Returns whether there are active movable pieces
     */
    public boolean hasMovablePieces() {
        for (int i = 0; i < 10; i++) { // Check movable pieces' slots
            int pieces = this.getNumberOfPieces(i);
            if (pieces > 0) { // There are movable pieces
                for (int j = 0; j<pieces; j++) { // For each of them
                    Piece piece = this.pieces.get(i).get(j);
                    int[][] moves = ((MovablePiece) piece).getMoves();
                    if (moves.length>0) { // Check if they are not blocked
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Creates a piece for this army's owner based on the piece code given
     *
     * @param pieceCode Piece code of the piece
     * @return Player's new piece
     * @pre pieceCode[0, 10]
     * @post Returns a piece of pieceCode for the given player
     */
    public Piece createPieceFromCode(int pieceCode) {
        int[] defaultPosition = new int[]{0, 0};
        Player owner = this.getOwner();
        if (pieceCode == 0)
            return new Slayer(owner, defaultPosition);
        else if (pieceCode == 1)
            return new Scout(owner, defaultPosition);
        else if (pieceCode == 2)
            return new Dwarf(owner, defaultPosition);
        else if (pieceCode == 3)
            return new Elf(owner, defaultPosition);
        else if (pieceCode == 4)
            if (owner.getId() == 1)
                return new Yeti(owner, defaultPosition);
            else
                return new LavaBeast(owner, defaultPosition);
        else if (pieceCode == 5)
            return new Sorceress(owner, defaultPosition);
        else if (pieceCode == 6)
            return new BeastRider(owner, defaultPosition);
        else if (pieceCode == 7)
            return new Knight(owner, defaultPosition);
        else if (pieceCode == 8)
            return new Mage(owner, defaultPosition);
        else if (pieceCode == 9)
            return new Dragon(owner, defaultPosition);
        else if (pieceCode == 10)
            return new Trap(owner, defaultPosition);
        else
            throw new InvalidPieceException("Invalid piece code");
    }

    /**
     * Creates an army based on the given mode
     *
     * @post Army is created based on gamemode
     */
    public void createArmy() {
        boolean reducedArmy = Controller.getReducedArmy();
        if (!reducedArmy) { // Classic stratego
            for (int i = 0; i < Army.PIECE_TYPES; i++) {
                int pieceCount = Army.DEFAULT_PIECES[i];
                for (int j = 0; j < pieceCount; j++) {
                    this.addPiece(createPieceFromCode(i));
                }
            }
        } else { // Reduced Army activated
            for (int i = 0; i < Army.PIECE_TYPES; i++) {
                int pieceCount = Army.DEFAULT_PIECES[i] / 2;
                if (pieceCount == 0) pieceCount++;
                for (int j = 0; j < pieceCount; j++) {
                    this.addPiece(createPieceFromCode(i));
                }
            }
        }
    }

    /**
     * Checks whether there are pieces to be rescued
     *
     * @return True if there are savable pieces
     * @post Returns whether there are pieces that can be rescued
     */
    public boolean hasAvailableRescues() {
        int[] rescues = this.getAvailableRescues();
        for (int i = 0; i < Army.PIECE_TYPES - 1; i++)
            if (rescues[i] > 0)
                return true;
        return false;
    }

    /**
     * Creates a list of pieces that can be rescued from each type
     *
     * @return Array of number of savable pieces of each type
     * @post Returns a list pieces that can be saved
     */
    public int[] getAvailableRescues() {
        boolean reducedArmy = Controller.getReducedArmy();
        int[] rescues = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!reducedArmy) { // Classic stratego
            for (int i = 0; i < Army.PIECE_TYPES - 1; i++) {
                rescues[i] = Army.DEFAULT_PIECES[i] - this.activePieces[i];
            }
        } else { // Reduced army
            for (int i = 0; i < Army.PIECE_TYPES - 1; i++) {
                int defaultPieces;
                if (Army.DEFAULT_PIECES[i] / 2 == 0)
                    defaultPieces = 1;
                else
                    defaultPieces = Army.DEFAULT_PIECES[i] / 2;
                rescues[i] = defaultPieces - this.activePieces[i];
            }
        }
        return rescues;
    }

    /**
     * Gets army's pieces list
     *
     * @return Army's pieces list
     * @post Army's pieces list is returned
     */
    public ArrayList<ArrayList<Piece>> getArmyPieces() {
        return this.pieces;
    }

    /**
     * Gets army's active pieces counter
     *
     * @return Army's active pieces counter
     * @post Army's active pieces counter is returned
     */
    public int[] getActiveArmyPieces() {
        return this.activePieces;
    }

    /**
     * Creates an army
     *
     * @post An army for the player is created
     */
    public Army(Player p) {
        this.activePieces = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.pieces = new ArrayList<>();
        for (int i = 0; i < Army.PIECE_TYPES; i++) {
            this.pieces.add(new ArrayList<Piece>());
        }
        this.owner = p;
        this.createArmy();
    }
}
