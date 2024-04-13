package model.piece.movable;

import controller.Controller;
import exception.InvalidPositionException;
import model.Board;
import model.piece.Piece;
import model.piece.stationary.Flag;
import model.piece.stationary.Trap;
import model.player.Player;

import java.util.ArrayList;

import exception.InvalidPieceException;

public abstract class MovablePiece extends Piece {
    /**
     * Power of the piece
     *
     * @inv power>=0
     */
    private int power;
    /**
     * Marks if the piece has rescued an other piece
     */
    private boolean hasRescued = false;

    /**
     * Sets the power of this piece
     *
     * @param power Power to be set
     * @pre power>=0
     * @post power is set to the given power
     */
    public void setPower(int power) {
        if (power < 0) throw new IllegalArgumentException("Power can't be negative.");
        else this.power = power;
    }

    /**
     * Returns this piece's power
     *
     * @return This piece's power
     */
    public int getPower() {
        return this.power;
    }

    /**
     * Returns whether this piece has rescued an other one
     *
     * @return True if it has rescued, not if not
     */
    public boolean hasRescued() {
        return this.hasRescued;
    }

    /**
     * Sets this piece's rescue status
     *
     * @param status Whether it has rescued an other piece
     */
    public void setRescued(boolean status) {
        this.hasRescued = status;
    }

    /**
     * Creates a movable piece
     *
     * @param player   The player this piece belongs to
     * @param position The position of the piece on the board
     * @param power    Piece's power
     * @post A movable piece for player in position with power is created
     */
    protected MovablePiece(Player player, int[] position, int power) {
        super(player, position);
        this.setPower(power);
    }

    /**
     * Gets piece's available moves
     *
     * @return An array of possible moves
     * @pre pos[0, 7][0, 9]
     * @post Array of valid moves
     */
    public int[][] getMoves() {
        int currentY = this.getYPosition(), currentX = this.getXPosition();
        ArrayList<Integer> ylist = new ArrayList<Integer>();
        ArrayList<Integer> xlist = new ArrayList<Integer>();
        int[][] moves;
        int power = this.getPower();

        if (!Board.isCoordValid(currentY, currentX)) throw new InvalidPositionException("Invalid position");

        // Add all possible moves in the list
        if (power >= 3 && power <= 10 || power == 1) { // Non-scout piece
            if (currentY + 1 < Board.BOARD_HEIGHT) {
                ylist.add(currentY + 1);
                xlist.add(currentX);
            }
            if (currentY - 1 >= 0) {
                ylist.add(currentY - 1);
                xlist.add(currentX);
            }
            if (currentX + 1 < Board.BOARD_WIDTH) {
                ylist.add(currentY);
                xlist.add(currentX + 1);
            }
            if (currentX - 1 >= 0) {
                ylist.add(currentY);
                xlist.add(currentX - 1);
            }
        } else if (power == 2) { // Scout
            for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
                ylist.add(i);
                xlist.add(currentX);
            }
            for (int j = 0; j < Board.BOARD_WIDTH; j++) {
                ylist.add(currentY);
                xlist.add(j);
            }
        } else {
            throw new IllegalArgumentException("Piece not valid.");
        }

        // Get max length
        int length = ylist.size();
        if (xlist.size() > ylist.size()) length = xlist.size();

        // Remove invalid possible positions
        for (int i = length - 1; i >= 0; i--) {
            int y = ylist.get(i);
            int x = xlist.get(i);
            Piece pieceAt = Board.getPieceAt(y, x);
            if (Board.isRestrictedArea(y, x) // Restricted area
                    || (pieceAt != null && pieceAt.getOwner() == this.getOwner()) // Same owner
                    || (y == currentY && x == currentX) // Same position
                    || (Controller.getNoRetreat() && (
                    (this.getOwner().getId() == 1 && y < currentY && (pieceAt == null || pieceAt.getOwner().getId() != 2))
                            || (this.getOwner().getId() == 2 && y > currentY && (pieceAt == null || pieceAt.getOwner().getId() != 1)))) // No Retreat mode
                    || (y - currentY != 0 && passesThroughRestrictedArea(y, x, 'y')) // Passes through Restricted Area for y
                    || (x - currentX != 0 && passesThroughRestrictedArea(y, x, 'x')) // Passes through Restricted Area for x
                    || (power != 2 && (y > currentY + 1 || y < currentY - 1 || x > currentX + 1 || x < currentX - 1)) // Is not a Scout and makes large steps
                    || (power == 2 && passesThroughPieces(y, x))) { // It is a scout and passes through pieces
                ylist.remove(i);
                xlist.remove(i);
            }
        }

        // Creates final available moves array
        length = ylist.size();
        moves = new int[length][2];
        for (int i = 0; i < length; i++) {
            moves[i][0] = ylist.get(i);
            moves[i][1] = xlist.get(i);
        }
        return moves;
    }

    /**
     * Attacks the given piece
     *
     * @param opp Piece to attack
     * @return Piece that wins the fight
     * @pre opp!=null && opp.owner!=this.owner
     * @post Winning piece
     */
    public Piece attack(Piece opp) {
        if (opp == null || opp.getOwner() == this.getOwner())
            throw new InvalidPieceException("Invalid opponent piece");
        // Special cases
        if (this instanceof Slayer && opp instanceof Dragon
                || this instanceof Dwarf && opp instanceof Trap
                || opp instanceof Flag) {
            return this;
            // Opp is a trap
        } else if (opp instanceof Trap) {
            return opp;
            // Power comparison case
        } else {
            MovablePiece movableOpp = (MovablePiece) opp;
            int oppPower = movableOpp.getPower();
            if (this.power > oppPower) {
                return this;
            } else if (this.power < oppPower) {
                return opp;
            } else {
                return null;
            }
        }
    }

    /**
     * Checks if, in order to go to (y,x) from its current position, the piece has to pass through restricted area
     *
     * @param y     Target y
     * @param x     Target x
     * @param coord Coordination line to check for (y or x)
     * @return True if it passes through restricted area, false if not
     * @pre coord==(y or x) && x,startingX[0,9]
     * @post Returns whether the piece passes through restricted area
     */
    private boolean passesThroughRestrictedArea(int y, int x, char coord) {
        int startingY = this.getYPosition();
        int startingX = this.getXPosition();
        if (!Board.isCoordValid(y, x)) throw new InvalidPositionException("Invalid location");
        if (coord == 'y') { // Vertical check
            if (y > startingY) {
                while (startingY < y) { // Goes downwards
                    if (Board.isRestrictedArea(startingY, startingX)) return true;
                    startingY++;
                }
            } else {
                while (startingY > y) { // Goes upwards
                    if (Board.isRestrictedArea(startingY, startingX)) return true;
                    startingY--;
                }
            }
        } else if (coord == 'x') { // Horizontal check
            if (x > startingX) {
                while (startingX < x) { // Goes right
                    if (Board.isRestrictedArea(startingY, startingX)) return true;
                    startingX++;
                }
            } else {
                while (startingX > x) { // Goes left
                    if (Board.isRestrictedArea(startingY, startingX)) return true;
                    startingX--;
                }
            }
        } else throw new InvalidPositionException("Invalid coord");
        return false;
    }

    /**
     * Checks if, in order to go to (y,x) from (startingy, startingx), the piece has to pass through other pieces. Checks all directions
     *
     * @param y Target y
     * @param x Target x
     * @return True if it passes through an allied piece or 1 enemy piece. First enemy piece is excluded. Else false.
     * @pre y, startingy[0, 7] && x,startingx[0,9]
     * @post Returns whether the move is valid based on pieces in it's way
     */
    private boolean passesThroughPieces(int y, int x) {
        int startingY = this.getYPosition();
        int startingX = this.getXPosition();
        int checkingY;
        int checkingX;
        if (!Board.isCoordValid(y, x) || !Board.isCoordValid(startingY, startingX))
            throw new InvalidPositionException("Invalid location");
        // Almost the same logic with passesThroughRestrictedArea
        if (y > startingY) {
            boolean enemyFound = false;
            checkingY = startingY + 1;
            checkingX = startingX;
            while (checkingY <= y) {
                Piece piece = Board.getPieceAt(checkingY, checkingX);
                if (piece != null && piece.getOwner().getId() != this.getOwner().getId() && !enemyFound) // First enemy found
                    enemyFound = true;
                else if (enemyFound) // Position behind enemy
                    return true;
                else if (piece != null && piece.getOwner().getId() == this.getOwner().getId())  // Allied piece found
                    return true;
                checkingY++;
            }
        } else {
            boolean enemyFound = false;
            checkingY = startingY - 1;
            checkingX = startingX;
            while (checkingY >= y) {
                Piece piece = Board.getPieceAt(checkingY, checkingX);
                if (piece != null && piece.getOwner().getId() != this.getOwner().getId() && !enemyFound) // First enemy found
                    enemyFound = true;
                else if (enemyFound) // Position behind enemy
                    return true;
                else if (piece != null && piece.getOwner().getId() == this.getOwner().getId()) // Allied piece found
                    return true;
                checkingY--;
            }
        }
        // Same for horizontal movement
        if (x > startingX) {
            boolean enemyFound = false;
            checkingY = startingY;
            checkingX = startingX + 1;
            while (checkingX <= x) {
                Piece piece = Board.getPieceAt(checkingY, checkingX);
                if (piece != null && piece.getOwner().getId() != this.getOwner().getId() && !enemyFound)
                    enemyFound = true;
                else if (enemyFound)
                    return true;
                else if (piece != null && piece.getOwner().getId() == this.getOwner().getId())
                    return true;
                checkingX++;
            }
        } else {
            boolean enemyFound = false;
            checkingY = startingY;
            checkingX = startingX - 1;
            while (checkingX >= x) {
                Piece piece = Board.getPieceAt(checkingY, checkingX);
                if (piece != null && piece.getOwner().getId() != this.getOwner().getId() && !enemyFound)
                    enemyFound = true;
                else if (enemyFound)
                    return true;
                else if (piece != null && piece.getOwner().getId() == this.getOwner().getId())
                    return true;
                checkingX--;
            }
        }
        return false;
    }
}
