package model.piece;

import exception.InvalidPositionException;
import model.player.Player;

public abstract class Piece {

    /**
     * Owner of the piece
     */
    private Player owner;
    /**
     * Position on the board. index 0 for x and 1 for y
     *
     * @inv position[0, 7][0, 9]
     */
    private int[] position = new int[2];

    /**
     * Sets the owner of the piece
     *
     * @param player Owner of the piece
     * @pre player!=Null
     * @post Owner set to player
     */
    public void setOwner(Player player) {
        if (player == null) throw new NullPointerException("No player is assigned.");
        else this.owner = player;
    }

    /**
     * Gets the owner of the piece
     *
     * @return Owner of this piece
     * @post Returns the owner of this piece
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Sets the position of the piece
     *
     * @param position Position to be set
     * @pre position[0, 7][0, 9]
     * @post Piece is placed in the given position
     */
    public void setPosition(int[] position) {
        if (position[0] < 0 || position[0] > 7 || position[1] < 0 || position[1] > 9)
            throw new InvalidPositionException("Invalid position.");
        else this.position = position;
    }

    /**
     * Gets the position of the piece
     *
     * @return Position of this piece
     * @post Returns this piece's position
     */
    public int[] getPosition() {
        return this.position;
    }

    /**
     * Gets the Y position
     *
     * @return Y position
     * @post Returns Y position
     */
    public int getYPosition() {
        return this.position[0];
    }

    /**
     * Gets the X position
     *
     * @return X position
     * @post Returns X position
     */
    public int getXPosition() {
        return this.position[1];
    }

    /**
     * Creates a piece for the given player in the given board position
     *
     * @param owner    Owner of the piece
     * @param position Position on the board
     * @post A piece for player in position is created
     */
    protected Piece(Player owner, int[] position) {
        this.setOwner(owner);
        this.setPosition(position);
    }

    /**
     * Creates a piece in the given board position. Only used by barrier constructor.
     * @param position Position on the board
     * @post A piece in position is created
     */
    protected Piece(int[] position) {
        this.owner = null;
        this.setPosition(position);
    }
}
