package model.player;

import exception.InvalidPieceException;

public class Player {
    /**
     * Name of the player
     */
    private String name;
    /**
     * Id of the player
     *
     * @inv 1 or 2
     */
    private int id;
    /**
     * Player's army
     */
    private final Army army;
    /**
     * Player's flag status
     */
    private boolean flag;
    /**
     * Saves made by each piece type
     *
     * @inv >=0
     */
    private final int[] saves;
    /**
     * Total saves made by all piece's
     *
     * @inv >=0
     */
    private int totalSaves;
    /**
     * Captured enemy piece's of each type
     *
     * @inv >=0
     */
    private final int[] captures;
    /**
     * Total captured enemy pieces
     *
     * @inv >=0
     */
    private int totalCaptures;
    /**
     * Total attacks performed
     *
     * @inv >=0
     */
    private int totalAttacks;
    /**
     * Successful attacks performed (fights won)
     *
     * @inv >=0
     */
    private int successfulAttacks;
    /**
     * Total moves performed
     *
     * @inv >=0
     */
    private int totalMoves;

    /**
     * Gets player's army
     *
     * @return Player's army
     * @post Player's army is returned
     */
    public Army getArmy() {
        return this.army;
    }

    /**
     * Sets player's name
     *
     * @param name Player name
     * @post Name set to the given name or to the default if it is empty
     */
    public void setName(String name) {
        if (name.isEmpty()) {
            this.name = "player" + this.id;
        } else {
            this.name = name;
        }
    }

    /**
     * Gets player's name
     *
     * @return Player's name
     * @post Returns the player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets player's id
     *
     * @param id Player's id
     * @pre id=1 or 2
     * @post Id set to the given value
     */
    public void setId(int id) {
        if (id != 1 && id != 2) throw new IllegalArgumentException("Id must be 1 or 2.");
        else this.id = id;
    }

    /**
     * Gets player's id
     *
     * @return Player's id
     * @post Returns the player's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets flag status
     *
     * @param status Flag status. True for placed/alive, false for taken/dead
     * @post Flag is set to the given status.
     */
    public void setFlag(boolean status) {
        this.flag = status;
    }

    /**
     * Checks whether the flag is still placed/alive
     *
     * @return True if it is placed/alive
     * @post Returns true if the flag is placed/alive, otherwise false
     */

    public boolean hasFlag() {
        return this.flag;
    }

    /**
     * Sets the number of saves performed by a piece type
     *
     * @param piece Piece code
     * @param saves Saves to be set
     * @pre piece[0, 9] && saves>=0
     * @post Saves of piece type set to new value
     */
    public void setSavesOf(int piece, int saves) {
        if (piece < 0 || piece > 9) throw new InvalidPieceException("Invalid piece code");
        else if (saves < 0) throw new IllegalArgumentException("Saves can't be negative");
        else this.saves[piece] = saves;
    }

    /**
     * Gets the number of saves performed by a piece type
     *
     * @param piece Piece code of piece to be checked
     * @return Number of saves performed by that piece type
     * @pre piece[0, 9]
     * @post Returns the number of saves performed by that piece type
     */
    public int getSavesOf(int piece) {
        return this.saves[piece];
    }

    /**
     * Sets total saves
     *
     * @param saves Saves to be set
     * @pre saves>=0
     * @post Saves set to the given value
     */
    public void setTotalSaves(int saves) {
        if (saves < 0) throw new IllegalArgumentException("Saves can't be negative");
        else this.totalSaves = saves;
    }

    /**
     * Gets total saves performed
     *
     * @return Total saves
     * @post Gets total saves
     */
    public int getTotalSaves() {
        return this.totalSaves;
    }

    /**
     * Sets total captures of piece type
     *
     * @param piece    Piece code
     * @param captures Captures to be set
     * @pre piece[0, 9] && captures>=0
     * @post Captures of given piece type set to the given value
     */
    public void setCapturesOf(int piece, int captures) {
        if (piece < 0 || piece > 9) throw new InvalidPieceException("Invalid piece code");
        else if (captures < 0) throw new IllegalArgumentException("Captures can't be negative");
        else this.captures[piece] = captures;
    }

    /**
     * Returns the captures of a piece type
     *
     * @param piece Piece code
     * @return Number of captures performed by that piece type
     * @post Returns the number of captures performed by that piece type
     */
    public int getCapturesOf(int piece) {
        return this.captures[piece];
    }

    /**
     * Sets total captures
     *
     * @param captures Total captures to be set
     * @pre captures>=0
     * @post Captures set to the given value
     */
    public void setTotalCaptures(int captures) {
        if (captures < 0) throw new IllegalArgumentException("Captures can't be negative");
        else this.totalCaptures = captures;
    }

    /**
     * Gets total captures performed
     *
     * @return Number of total captures
     * @post Returns total captures performed
     */
    public int getTotalCaptures() {
        return this.totalCaptures;
    }

    /**
     * Sets total attacks
     *
     * @param attacks Total attacks value
     * @pre attacks>=0
     * @post Total attacks set to attacks
     */
    public void setTotalAttacks(int attacks) {
        if (attacks < 0) throw new IllegalArgumentException("Attacks can't be negative");
        else this.totalAttacks = attacks;
    }

    /**
     * Gets total attacks performed
     *
     * @return Number of total attacks
     * @post Returns total attacks
     */
    public int getTotalAttacks() {
        return this.totalAttacks;
    }

    /**
     * Sets succesful attacks
     *
     * @param successfulAttacks Successful attacks value
     * @pre successfulAttacks>=0
     * @post Successful attacks set to successfulAttacks
     */
    public void setSuccessfulAttacks(int successfulAttacks) {
        if (successfulAttacks < 0) throw new IllegalArgumentException("Successful attacks can't be negative");
        else this.successfulAttacks = successfulAttacks;
    }

    /**
     * Gets successful attacks performed
     *
     * @return Number of successful attacks
     * @post Returns successful attacks
     */
    public int getSuccessfulAttacks() {
        return this.successfulAttacks;
    }

    /**
     * Sets total moves
     *
     * @param moves Moves to be set
     * @pre moves>=0
     * @post Total moves set to the given value
     */
    public void setTotalMoves(int moves) {
        if (moves < 0) throw new IllegalArgumentException("Moves can't be negative");
        else this.totalMoves = moves;
    }

    /**
     * Gets total moves
     *
     * @return Number of total moves
     * @post Returns number of total moves
     */
    public int getTotalMoves() {
        return this.totalMoves;
    }

    /**
     * Creates a player with the given name
     *
     * @param name Name of the player
     * @param id   Id of the player
     * @post A player with name and id is created
     */
    public Player(String name, int id) {
        this.setId(id);
        this.setName(name);
        this.army = new Army(this);
        this.flag = true;
        this.saves = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.captures = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.setTotalSaves(0);
        this.setTotalCaptures(0);
        this.setTotalAttacks(0);
        this.setSuccessfulAttacks(0);
        this.setTotalMoves(0);
    }
}
