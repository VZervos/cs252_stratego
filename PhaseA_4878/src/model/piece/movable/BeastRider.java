package model.piece.movable;

import model.player.Player;

public class BeastRider extends MovablePiece {

    /**
     * Creates a beast rider for the given player and placed in the given position
     * @param player Player the piece belongs to
     * @param position Position of the piece on the board
     */
    public BeastRider(Player player, int[] position) {
        super(player, position, 7);
    }
}
