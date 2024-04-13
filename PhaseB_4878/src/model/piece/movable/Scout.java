package model.piece.movable;

import model.player.Player;

public class Scout extends MovablePiece implements SpecialPiece {

    /**
     * Creates a scout for the given player and placed in the given position
     *
     * @param player   Player the piece belongs to
     * @param position Position of the piece on the board
     * @post A Scout for player in position given is created
     */
    public Scout(Player player, int[] position) {
        super(player, position, 2);
    }
}
