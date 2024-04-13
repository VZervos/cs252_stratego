package junit;

import controller.Controller;
import model.Board;
import model.piece.Piece;
import model.piece.movable.Dragon;
import model.piece.movable.MovablePiece;
import model.piece.movable.Slayer;
import model.player.Player;
import org.junit.Assert;
import org.junit.Test;

public class JUnitTests {

    @Test
    public void Tests() {
        Player p1 = new Player("p1", 1);
        Player p2 = new Player("p2", 2);

        // Attack test
        Piece piece1 = new Dragon(p1, new int[]{0, 0});
        MovablePiece piece2 = new Slayer(p2, new int[]{0, 0});
        Assert.assertEquals("Slayer should beat the dragon since he attacks first",
                piece2, piece2.attack(piece1));

        // Position checking test
        Assert.assertTrue("Position (3, 2) should be restricted",
                Board.isRestrictedArea(3, 2));

        // Turn management test
        Controller.setPlayerPlaying(p1);
        Assert.assertEquals("p1 is set to play now. It should be their turn",
                p1, Controller.getNowPlaying());

        // Rescues test
        int[] rescues = p2.getArmy().getAvailableRescues();
        int availableRescues = 0;
        for (int rescue : rescues) availableRescues += rescue;
        Assert.assertEquals("No captures have been made yet by p1. p2 should have no rescues available",
                availableRescues, 0);

        // Movement test
        Board b = new Board(p1, p2);
        Board.setPieceAt(0, 1, piece2);
        int position = piece2.getXPosition() + piece2.getYPosition();
        Assert.assertEquals("Piece was moved to (0, 0). It should be there",
                1, position);
    }
}
