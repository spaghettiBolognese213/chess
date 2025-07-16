package chess.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import chess.board.BoardState;
import chess.board.pieces.*;
import chess.userInput.InputCallback;

public class BoardDisplay extends JPanel {
    private static final int TILE_SIZE = 64;
    private static final int BOARD_SIZE = 8;

    private InputCallback callback;

    private JPanel boardPanel;
    private JPanel[][] boardSquares = new JPanel[BOARD_SIZE][BOARD_SIZE];

    private BoardState boardState;

    public BoardDisplay(BoardState newBoardState) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE));

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        initialiseBoard();

        add(boardPanel, BorderLayout.CENTER);

        boardState = newBoardState;
    }

    // input
    public void setInputCallback(InputCallback callback) {
        this.callback = callback;
    }

    private void handleClick(int row, int col) {
        if (callback != null) {
            callback.onSquareSelected(new Point(col, row));
        }
    }

    public void setBoard(BoardState newBoardState) {
        boardState = newBoardState;
    }

    public void clearBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boardSquares[row][col].removeAll(); // removes previous piece icons
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


                final int r = row;
                final int c = col;
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleClick(r,c);
                        System.out.println("boardState is " + (boardState == null ? "null" : "not null"));
                        if (boardState != null) drawPieces(boardState.getBoardGrid());
                    }
                });


                boardSquares[row][col] = square;
                boardPanel.add(square);
                isWhite = !isWhite;
            }
        }
    }

    public void drawPieces(Piece[][] boardGrid) {
//        System.out.println("entered drawPieces");
        this.clearBoard();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (boardGrid[row][col] != null && boardGrid[row][col].getType() != PieceType.EMPTY)
                    placePiece(buildStringPiece(boardGrid[row][col]), row, col);
                if (boardGrid[row][col].isSelected()) {
//                    System.out.println("highlight piece!");
                    boardSquares[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                }
                else if (boardGrid[row][col].canMoveTo()) {
                    boardSquares[row][col].setBorder(BorderFactory.createLineBorder(Color.red, 3));
                }
                else {boardSquares[row][col].setBorder(null);}
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private String buildStringPiece(Piece piece) {
        StringBuilder output = new StringBuilder("images/pieces/");

        output.append(
                (piece.isWhite() ? "white" : "black")
        ).append("_");

        output.append(piece);
        output.append(".png");

        return output.toString();
    }

    private void placePiece(String iconName, int row, int col) {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(iconName));
        Image scaledImage = icon.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
        boardSquares[row][col].add(pieceLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BoardDisplay(null));
    }
}
