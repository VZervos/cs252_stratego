package model.piece.movable;

import model.Board;
import model.piece.Piece;
import model.player.Player;

public abstract class MovablePiece extends Piece {
    /**
     * Power of the piece
     * @inv power>=0
     */
    private int power;

    /**
     * Sets the power of this piece
     * @param power Power to be set
     * @pre power>=0
     * @post power is set to the given power
     */
    public void setPower(int power) {
        if (power<0) throw new IllegalArgumentException("Power can't be negative.");
        else this.power=power;
    }

    /**
     * Returns this piece's power
     * @return This piece's power
     */
    public int getPower() {return this.power;}

    /**
     * Creates a movable piece
     * @param player The player this piece belongs to
     * @param position The position of the piece on the board
     * @param power Piece's power
     */
    protected MovablePiece (Player player, int[] position, int power) {
        super(player, position);
        this.setPower(power);
    }

    /**
     * Gets piece's available moves
     * @param pos Piece's current position on the board
     * @param board Board to be analyzed
     * @return An array of possible moves
     * @pre pos[0, 7][0, 9] && board!=Null
     * @post Array of valid moves
     */
    public int[][] getMoves(int[] pos, Board board) {
        return new int[0][];
    }

    /**
     * Moves the piece to a certain location and triggers any needed action (e.g. attack, rescue)
     * @param pos Position to be moved int
     * @pre pos[0, 7][0, 9]
     * @post Needed actions are called based on the position
     */
    public void move(int[] pos) {}

    /**
     * Attacks the given piece
     * @param opp Piece to attack
     * @return Piece that wins the fight
     * @pre opp!=null && opp.owner!=this.owner
     * @post Winning piece
     */
    public Piece attack(Piece opp) {
        return null;
    }

    /**
     * Rescues a piece
     * @param piece The piece code of the piece to be rescued
     * @return The rescued piece
     * @pre piece[0-9]
     * @post A new piece of the requested type is returned
     */
    public Piece rescue(int piece) {
        return null;
    }
}
