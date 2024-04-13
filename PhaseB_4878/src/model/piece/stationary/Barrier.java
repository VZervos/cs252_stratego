package model.piece.stationary;

import model.piece.movable.SpecialPiece;

public class Barrier extends StationaryPiece implements SpecialPiece {

    /**
     * Creates a barrier piece in the given position
     *
     * @param position Position to be placed
     * @post A barrier in position given is created
     */
    public Barrier(int[] position) {
        super(position);
    }
}
