package controller;

import model.Board;
import model.Timer;
import model.piece.Piece;
import model.player.Player;
import view.GameBoard;
import view.MainMenu;

public class Controller {
    /**
     * Game's board
     */
    private static Board board;
    /**
     * Player 1 (blue)
     */
    private static Player player1;
    /**
     * Player 2 (red)
     */
    private static Player player2;
    /**
     * The player currently playing
     */
    private static Player nowPlaying;
    /**
     * Current round
     */
    private static int round;
    /**
     * Reduced Army setting
     */
    private static boolean reducedArmy;
    /**
     * No Retreat setting
     */
    private static boolean noRetreat;
    /**
     * Timer (clock) of the game
     */
    private static Timer timer;
    /**
     * Gameboard view instance
     */
    private static GameBoard gameboard;

    /**
     * Gets board
     * @return Game's board
     * @post Board is returned
     */
    public static Board getBoard() {return board;}

    /**
     * Gets player 1
     * @return Player 1's instance
     * @post Player 1 is returned
     */
    public static Player getPlayer1() {return player1;}

    /**
     * Gets player 2
     * @return Player 2's instance
     * @post Player 2 is returned
     */
    public static Player getPlayer2() {return player2;}

    /**
     * Gets player currently playing
     * @return Player playing now
     * @post Player playing now is returned
     */
    public static Player getNowPlaying() {return nowPlaying;}

    /**
     * Gets round number
     * @return Round number
     * @post Returns current round
     */
    public static int getRound() {return round;}

    /**
     * Gets Reduced Army setting
     * @return True if enabled, false if not
     * @post Reduced Army setting is returned
     */
    public static boolean getReducedArmy() {return reducedArmy;}

    /**
     * Gets No Retreat setting
     * @return True if enabled, false if not
     * @post No Retreat setting is returned
     */
    public static boolean getNoRetreat() {return noRetreat;}

    /**
     * Gets timer
     * @return Timer's instance
     * @post Returns the timer of the game
     */
    public static Timer getTimer() {return timer;}

    /**
     * Starts a new game
     * @param player1Name Player 1
     * @param player2Name Player 2
     * @param reducedArmyIn ReducedArmy setting
     * @param noRetreatIn NoRetreat setting
     * @pre player1Name!=Null, player2Name!=Null
     * @post Starts a game with the given parameters
     */
    public void startGame(String player1Name, String player2Name, boolean reducedArmyIn, boolean noRetreatIn) {
        player1 = new Player(player1Name, 1);
        player2 = new Player(player2Name, 2);
        nowPlaying = player1;
        round = 1;
        reducedArmy = reducedArmyIn;
        noRetreat = noRetreatIn;
        player1.getArmy().createArmy(reducedArmy);
        player2.getArmy().createArmy(reducedArmy);
        timer = new Timer();
        board = new Board(player1, player2);
        gameboard = new GameBoard();
    }

    /**
     * Ends the game with the given winner
     * @param winner Winner of the game
     * @post Game ended
     */
    public static void endGame(Player winner) {}

    /**
     * Checks if the game has ended
     * @return True if it can be ended, false if not
     * @post Returns if the game is lost for one of the two players
     */
    public static boolean checkIfEnded() {return true;}

    /**
     * Sets the player currently playing
     * @param player Player playing
     * @pre player!=Null
     * @post Player currently playing updated
     */
    public static void setPlayerPlaying(Player player) {}

    /**
     * Moves to the next round making any necessary changes
     * @post Game is moved to the next round
     */
    public static void nextRound() {}

    /**
     * Attempts to move a piece at a certain location
     * @param piece Piece to be moved
     * @param position Location
     * @pre piece!=Null, position[0,7][0,9]
     */
    public static void makeMove(Piece piece, int[] position) {}

    /**
     * Initiates the game
     */
    public static void main(String[] args) {
        MainMenu game = new MainMenu();
    }
}
