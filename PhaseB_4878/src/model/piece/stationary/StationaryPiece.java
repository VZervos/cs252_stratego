package model.piece.stationary;

import model.piece.Piece;
import model.player.Player;

public abstract class StationaryPiece extends Piece {

    /**
     * Creates a stationary piece for the given player in the given board position
     *
     * @param player   Owner of the piece
     * @param position Position on the board
     * @post A stationary piece for player in position is created
     */
    public StationaryPiece(Player player, int[] position) {
        super(player, position);
    }

    /**
     * Creates a stationary piece in the given board position. Only used by barrier constructor.
     *
     * @param position Position on the board
     * @post A stationary piece in position is created
     */
    public StationaryPiece(int[] position) {
        super(position);
    }
}
