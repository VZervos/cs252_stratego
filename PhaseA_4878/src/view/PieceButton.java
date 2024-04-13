package view;

import model.piece.Piece;

import javax.swing.*;

public class PieceButton extends JButton {
    private Piece piece;
    private JButton button;

    public PieceButton(Piece piece) {
        this.piece = piece;
        this.button = new JButton(new ImageIcon());
    }
}
