package model.piece.movable;

import model.player.Player;

public class Slayer extends MovablePiece implements SpecialPiece {

    /**
     * Creates a slayer for the given player and placed in the given position
     * @param player Player the piece belongs to
     * @param position Position of the piece on the board
     */
    public Slayer(Player player, int[] position) {
        super(player, position, 1);
    }
}