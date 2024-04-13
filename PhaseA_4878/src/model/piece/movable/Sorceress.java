package model.piece.movable;

import model.player.Player;

public class Sorceress extends MovablePiece {

    /**
     * Creates a sorceress for the given player and placed in the given position
     * @param player Player the piece belongs to
     * @param position Position of the piece on the board
     */
    public Sorceress(Player player, int[] position) {
        super(player, position, 6);
    }
}
