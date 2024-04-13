package view;

import controller.Controller;
import model.piece.stationary.Barrier;
import model.piece.Piece;
import model.piece.movable.*;
import model.piece.stationary.Trap;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PieceButton extends JButton {
    /**
     * The piece represented
     */
    private Piece piece;
    /**
     * Button contained
     */
    private JButton button;

    /**
     * Sets piece represented to piece
     *
     * @param piece Piece to be set
     * @post piece represented is set to piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Gets the piece represented
     *
     * @return Piece represented
     * @post The piece represented is returned
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Sets the button
     *
     * @param button Button
     * @post button is set to button
     */
    public void setButton(JButton button) {
        this.button = button;
    }

    /**
     * Gets the button
     *
     * @return Piecebutton's button
     * @post Button contained is returned
     */
    public JButton getButton() {
        return this.button;
    }

    /**
     * Creates a new PieceButton of piece with tag ij
     *
     * @param piece Piece to be represented
     * @param i     Y position
     * @param j     X position
     */
    public PieceButton(Piece piece, int i, int j) {
        this.piece = piece;
        this.button = setIcon();
        this.button.setName(Integer.toString(i) + j);
        this.button.setBorder(null);
        this.button.setFocusPainted(false);
        this.button.setContentAreaFilled(false);
        this.button.setBorderPainted(false);
    }

    /**
     * Sets the icon of the button
     *
     * @return Button with icon is returned
     * @post Button's icon is updated based on its properties // Activates "X" button
     */
    private JButton setIcon() {
        ImageIcon icon = null;
        JButton button = new JButton();
        if (piece instanceof Barrier) {
            button.setBackground(Color.black);
        } else if (piece != null && piece.getOwner().getId() == 1) {
            if (Controller.getNowPlaying().getId() == 2) {
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/bluePieces/blueHidden.png")));
            } else if (piece instanceof MovablePiece) {
                int power = ((MovablePiece) piece).getPower();
                if (power == 1)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/slayerB.png")));
                else if (power == 2)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/scoutB.png")));
                else if (power == 3)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/dwarfB.png")));
                else if (power == 4)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/elfB.png")));
                else if (power == 5)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/yeti.png")));
                else if (power == 6)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/sorceressB.png")));
                else if (power == 7)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/beastRiderB.png")));
                else if (power == 8)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/knightB.png")));
                else if (power == 9)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/mageB.png")));
                else if (power == 10)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/dragonB.png")));
            } else {
                if (piece instanceof Trap)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/trapB.png")));
                else
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/bluePieces/flagB.png")));
            }
            Image image;
            assert icon != null;
            image = icon.getImage();
            image = image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            button.setIcon(icon);
        } else if (piece != null && piece.getOwner().getId() == 2) {
            if (Controller.getNowPlaying().getId() == 1) {
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                        "/resources/redPieces/redHidden.png")));
            } else if (piece instanceof MovablePiece) {
                int power = ((MovablePiece) piece).getPower();
                if (power == 1)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/slayerR.png")));
                else if (power == 2)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/scoutR.png")));
                else if (power == 3)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/dwarfR.png")));
                else if (power == 4)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/elfR.png")));
                else if (power == 5)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/lavaBeast.png")));
                else if (power == 6)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/sorceressR.png")));
                else if (power == 7)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/beastRiderR.png")));
                else if (power == 8)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/knightR.png")));
                else if (power == 9)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/mageR.png")));
                else if (power == 10)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/dragonR.png")));
            } else {
                if (piece instanceof Trap)
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/trapR.png")));
                else
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                            "/resources/redPieces/flagR.png")));
            }
            Image image;
            assert icon != null;
            image = icon.getImage();
            image = image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            button.setIcon(icon);
        }
        return button;
    }
}
