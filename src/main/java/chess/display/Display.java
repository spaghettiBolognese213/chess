package chess.display;


import chess.board.pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    BoardDisplay boardDisplay;

    public Display() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        boardDisplay = new BoardDisplay();
        setContentPane(boardDisplay);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void drawBoard(Piece[][] currentGrid) {
        boardDisplay.drawPieces(currentGrid);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Display::new);
    }
}
