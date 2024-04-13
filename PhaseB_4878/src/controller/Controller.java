package controller;

import exception.InvalidPieceException;
import exception.InvalidPlayerException;
import model.Board;
import model.Timer;
import model.piece.Piece;
import model.piece.movable.MovablePiece;
import model.piece.movable.Scout;
import model.piece.stationary.Flag;
import model.piece.stationary.Trap;
import model.player.Army;
import model.player.Player;
import view.*;

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
     * Statistics view instance
     */
    private static Statistics statistics;

    /**
     * Gets board
     *
     * @return Game's board
     * @post Board is returned
     */
    public static Board getBoard() {
        return board;
    }

    /**
     * Gets player 1
     *
     * @return Player 1's instance
     * @post Player 1 is returned
     */
    public static Player getPlayer1() {
        return player1;
    }

    /**
     * Gets player 2
     *
     * @return Player 2's instance
     * @post Player 2 is returned
     */
    public static Player getPlayer2() {
        return player2;
    }

    /**
     * Gets player currently playing
     *
     * @return Player playing now
     * @post Player playing now is returned
     */
    public static Player getNowPlaying() {
        return nowPlaying;
    }

    /**
     * Gets round number
     *
     * @return Round number
     * @post Returns current round
     */
    public static int getRound() {
        return round;
    }

    /**
     * Gets Reduced Army setting
     *
     * @return True if enabled, false if not
     * @post Reduced Army setting is returned
     */
    public static boolean getReducedArmy() {
        return reducedArmy;
    }

    /**
     * Gets No Retreat setting
     *
     * @return True if enabled, false if not
     * @post No Retreat setting is returned
     */
    public static boolean getNoRetreat() {
        return noRetreat;
    }

    /**
     * Gets timer
     *
     * @return Timer's instance
     * @post Returns the timer of the game
     */
    public static Timer getTimer() {
        return timer;
    }

    /**
     * Starts a new game
     *
     * @param player1Name   Player 1
     * @param player2Name   Player 2
     * @param reducedArmyIn ReducedArmy setting
     * @param noRetreatIn   NoRetreat setting
     * @pre player1Name!=Null, player2Name!=Null
     * @post Starts a game with the given parameters
     */
    public void startGame(String player1Name, String player2Name, boolean reducedArmyIn, boolean noRetreatIn) {
        reducedArmy = reducedArmyIn;
        noRetreat = noRetreatIn;
        player1 = new Player(player1Name, 1);
        player2 = new Player(player2Name, 2);
        nowPlaying = player1;
        round = 1;
        timer = new Timer();
        board = new Board(player1, player2);
        gameboard = new GameBoard();
        statistics = new Statistics();
        Thread statsThread = new Thread(statistics);
        statsThread.start();
    }

    /**
     * Ends the game with the given winner
     *
     * @param winner Winner of the game
     * @post Game ended
     */
    public static void endGame(Player winner) {
        gameboard.setBounds(200, 0, 850, 700);
        gameboard.setEnabled(false);
        statistics.stop();
        GameEnd end = new GameEnd(winner);
    }

    /**
     * Checks if the game has ended
     *
     * @return 0 if not ended, 1 if player1 won, 2 if player2 won, 3 if draw
     * @post Returns if the game is lost for one of the two players
     */
    public static int checkIfEnded() {
        boolean p1Defeated = false;
        boolean p2Defeated = false;
        if (!player1.hasFlag() || !player1.getArmy().hasMovablePieces())
            p1Defeated = true;
        if (!player2.hasFlag() || !player2.getArmy().hasMovablePieces())
            p2Defeated = true;
        if (!p1Defeated && p2Defeated) // player1 won
            return 1;
        else if (p1Defeated && !p2Defeated) // player2 won
            return 2;
        else if (p1Defeated && p2Defeated) // draw
            return 3;
        else // game continues
            return 0;
    }

    /**
     * Sets the player currently playing
     *
     * @param player Player playing
     * @pre player!=Null
     * @post Player currently playing updated
     */
    public static void setPlayerPlaying(Player player) {
        nowPlaying = player;
    }

    /**
     * Moves to the next round only if the game hasn't ended, making any necessary changes
     *
     * @post Game is moved to the next round: Player playing swapped & board updated
     */
    public static void nextRound() {
        int winner = checkIfEnded();
        if (winner == 1) {
            endGame(player1);
            return;
        } else if (winner == 2) {
            endGame(player2);
            return;
        } else if (winner == 3) {
            endGame(null);
            return;
        }
        if (getNowPlaying().getId() == 1) {
            setPlayerPlaying(player2);
        } else {
            setPlayerPlaying(player1);
        }
        round++;
        gameboard.updateBoardView(board);
        statistics.updateStatistics();
    }

    /**
     * Attempts to move a piece at a certain location
     *
     * @param piece    Piece to be moved
     * @param position Location
     * @pre piece!=Null, position[0,7][0,9]
     * @post Board is updated with the result of the movement and proceeds to next round
     */
    public static void makeMove(Piece piece, int[] position) {
        int prevY = piece.getYPosition();
        int prevX = piece.getXPosition();
        int y = position[0];
        int x = position[1];
        Piece opp = Board.getPieceAt(y, x);
        Player attackerPieceOwner = piece.getOwner();
        Piece winner;
        attackerPieceOwner.setTotalMoves(attackerPieceOwner.getTotalMoves() + 1);
        if (opp != null) { // Attack
            if (opp instanceof Flag) {
                opp.getOwner().setFlag(false);
                Board.setPieceAt(y, x, piece);
                Board.setPieceAt(prevY, prevX, null);
                nextRound();
                return;
            } else {
                winner = initiateAttack(piece, opp);
                Board.setPieceAt(position[0], position[1], winner);
            }
        } else { // Simple move
            Board.setPieceAt(position[0], position[1], piece);
        }
        piece = Board.getPieceAt(y, x); // Rescue
        if (piece != null && !(piece instanceof Scout) && (
                (piece.getOwner() == player1 && y == 7
                        && (!(piece instanceof Trap) && !((MovablePiece) piece).hasRescued()))
                        || (piece.getOwner() == player2 && y == 0
                        && (!(piece instanceof Trap) && !((MovablePiece) piece).hasRescued())))) {
            Army army = attackerPieceOwner.getArmy();
            if (army.hasAvailableRescues() && attackerPieceOwner.getTotalSaves() < 2) {
                int[] rescues = army.getAvailableRescues();
                ((MovablePiece) piece).setRescued(true);
                gameboard.setEnabled(false);
                RescueSelector rs = new RescueSelector(attackerPieceOwner, rescues);
            }
        }
        Board.setPieceAt(prevY, prevX, null);
        nextRound();
    }

    /**
     * Initiates a combat between two pieces
     *
     * @param attacker Attacking piece
     * @param defender Defending piece
     * @return Winner of the fight
     * @pre attacker, defender!=null
     * @post Winner piece is returned, loser is removed and stats are updated
     */
    public static Piece initiateAttack(Piece attacker, Piece defender) {
        if (attacker == null || defender == null) throw new InvalidPieceException("Invalid piece");
        int attackerPieceCode = Army.getPieceCode(attacker);
        Player attackerPieceOwner = attacker.getOwner();
        Piece winner;
        attackerPieceOwner.setTotalMoves(attackerPieceOwner.getTotalMoves() + 1);
        int oppPieceCode;
        Player oppPieceOwner = defender.getOwner();
        winner = ((MovablePiece) attacker).attack(defender);
        if (winner != null) { // Not draw
            oppPieceCode = Army.getPieceCode(defender);
            attackerPieceOwner.setTotalAttacks(attackerPieceOwner.getTotalAttacks() + 1);
            if (winner == attacker) { // Attacker won
                if (!(defender instanceof Trap)) {
                    attackerPieceOwner.setCapturesOf(oppPieceCode, attackerPieceOwner.getCapturesOf(oppPieceCode) + 1);
                    attackerPieceOwner.setTotalCaptures(attackerPieceOwner.getTotalCaptures() + 1);
                    attackerPieceOwner.setSuccessfulAttacks(attackerPieceOwner.getSuccessfulAttacks() + 1);
                }
                oppPieceOwner.getArmy().removePiece(defender);
            } else { // Defender won
                oppPieceOwner.setCapturesOf(attackerPieceCode, oppPieceOwner.getCapturesOf(attackerPieceCode) + 1);
                oppPieceOwner.setTotalCaptures(oppPieceOwner.getTotalCaptures() + 1);
                attackerPieceOwner.getArmy().removePiece(attacker);
            }
        } else { // Draw
            oppPieceCode = Army.getPieceCode(defender);
            player1.setCapturesOf(oppPieceCode, player1.getCapturesOf(oppPieceCode) + 1);
            player1.setTotalCaptures(player1.getTotalCaptures() + 1);
            oppPieceOwner.getArmy().removePiece(attacker);
            player2.setCapturesOf(oppPieceCode, player2.getCapturesOf(oppPieceCode) + 1);
            player2.setTotalCaptures(player2.getTotalCaptures() + 1);
            oppPieceOwner.getArmy().removePiece(defender);
        }
        return winner;
    }

    /**
     * Rescues a piece for the given player
     *
     * @param player    Player the piece is going to be rescued for
     * @param pieceCode Piece code of the rescued piece
     * @pre player!=null & pieceCode[0,9]
     * @post Rescued piece is added in player's army and stats are updated
     */
    public static void rescue(Player player, int pieceCode) {
        if (player == null) throw new InvalidPlayerException("Invalid player");
        if (pieceCode < 0 || pieceCode > 9) throw new InvalidPieceException("Invalid piece code");
        Army army = player.getArmy();
        Piece piece = army.createPieceFromCode(pieceCode);
        player.getArmy().addPiece(piece);
        if (Board.setPieceOnBoardRandomly(piece)) { // Placement successful
            player.setSavesOf(pieceCode, player.getSavesOf(pieceCode) + 1);
            player.setTotalSaves(player.getTotalSaves() + 1);
        } else // Cancel rescue operation
            player.getArmy().removePiece(piece);
        gameboard.setEnabled(true);
        gameboard.updateBoardView(board);
    }

    /**
     * Initiates the game
     */
    public static void main(String[] args) {
        MainMenu game = new MainMenu();
    }
}
