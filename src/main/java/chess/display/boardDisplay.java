package chess.display;

import javax.swing.*;
import java.awt.*;

public class boardDisplay extends JFrame {
    private static final int TILE_SIZE = 64;
    private static final int BOARD_SIZE = 8;

    private JPanel boardPanel;
    private JPanel[][] boardSquares = new JPanel[BOARD_SIZE][BOARD_SIZE];

    public boardDisplay () {
        setTitle("Chess Grid Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE);
        setLayout(new BorderLayout());

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        initialiseBoard();

        add(boardPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (row > (BOARD_SIZE / 2)) placePiece("images/pieces/black_king.png",row, col);
                else placePiece("images/pieces/white_king.png",row, col);
            }
        }

    }

    private void initialiseBoard() {
        boolean isWhite = true;

        for (int row = 0; row < BOARD_SIZE; row++) {
            isWhite = !isWhite;
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBackground(isWhite ? Color.WHITE : new Color(0, 153, 153));
                boardSquares[row][col] = square;
                boardPanel.add(square);
                isWhite = !isWhite;
            }
        }
    }

    private void placePiece(String iconName, int row, int col) {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(iconName));
        Image scaledImage = icon.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
        boardSquares[row][col].add(pieceLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(boardDisplay::new);
    }
}
