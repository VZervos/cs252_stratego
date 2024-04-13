package model.piece.movable;

import model.player.Player;

public class Knight extends MovablePiece {

    /**
     * Creates a knight for the given player and placed in the given position
     *
     * @param player   Player the piece belongs to
     * @param position Position of the piece on the board
     * @post A Knight for player in position given is created
     */
    public Knight(Player player, int[] position) {
        super(player, position, 8);
    }
}
