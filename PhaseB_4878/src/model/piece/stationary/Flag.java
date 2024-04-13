package model.piece.stationary;

import model.player.Player;

public class Flag extends StationaryPiece {

    /**
     * Creates a flag for the given player in the given board position
     *
     * @param player   Owner of the flag
     * @param position Position on the board
     * @post A Flag for player in position given is created
     */
    public Flag(Player player, int[] position) {
        super(player, position);
    }
}
