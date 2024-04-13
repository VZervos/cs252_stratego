package model.piece.movable;

import model.player.Player;

public class LavaBeast extends MovablePiece {

    /**
     * Creates a lava beast for the given player and placed in the given position
     *
     * @param player   Player the piece belongs to
     * @param position Position of the piece on the board
     * @post A LavaBeast for player in position given is created
     */
    public LavaBeast(Player player, int[] position) {
        super(player, position, 5);
    }
}
