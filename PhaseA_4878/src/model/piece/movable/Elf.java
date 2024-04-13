package model.piece.movable;

import model.player.Player;

public class Elf extends MovablePiece {

    /**
     * Creates an elf for the given player and placed in the given position
     * @param player Player the piece belongs to
     * @param position Position of the piece on the board
     */
    public Elf(Player player, int[] position) {
        super(player, position, 4);
    }
}
