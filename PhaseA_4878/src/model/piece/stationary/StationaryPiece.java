package model.piece.stationary;

import model.piece.Piece;
import model.player.Player;

public abstract class StationaryPiece extends Piece {

    /**
     * Creates a stationary piece for the given player in the given board position
     * @param player Owner of the piece
     * @param position Position on the board
     */
    public StationaryPiece(Player player, int[] position) { super(player, position); }
}
