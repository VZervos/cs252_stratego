package model.piece.movable;

import model.player.Player;

public class Dwarf extends MovablePiece implements SpecialPiece {

    /**
     * Creates a dwarf rider for the given player and placed in the given position
     *
     * @param player   Player the piece belongs to
     * @param position Position of the piece on the board
     * @post A Dwarf for player in position given is created
     */
    public Dwarf(Player player, int[] position) {
        super(player, position, 3);
    }
}
