package model.piece.stationary;

import model.player.Player;

public class Trap extends StationaryPiece{

    /**
     * Creates a trap for the given player on the given board position
     * @param player Owner of the trap
     * @param position Position on the board
     */
    public Trap(Player player, int[] position) { super(player, position); }
}
