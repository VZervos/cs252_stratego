package model.piece.movable;

import model.player.Player;

public class Dragon extends MovablePiece {

    /**
     * Creates a dragon for the given player and placed in the given position
     * @param player Player the piece belongs to
     * @param position Position of the piece on the board
     */
    public Dragon(Player player, int[] position) {
        super(player, position, 10);
    }
}
