package model.piece.movable;

import model.player.Player;

public class Mage extends MovablePiece {

    /**
     * Creates a mage for the given player and placed in the given position
     *
     * @param player   Player the piece belongs to
     * @param position Position of the piece on the board
     * @post A Mage for player in position given is created
     */
    public Mage(Player player, int[] position) {
        super(player, position, 9);
    }
}
