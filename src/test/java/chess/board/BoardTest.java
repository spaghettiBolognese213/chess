package chess.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void fillBoardEmpty() {
        int boardSize = 8;
        Board board = new Board(boardSize);
        PieceType[][] actualGrid = board.fillBoard(PieceType.EMPTY);

        assertAll(() -> {
            for (int row = 0; row < boardSize; row++) {
                for (int collumn = 0; collumn < boardSize; collumn++) {
                    assertEquals(PieceType.EMPTY, actualGrid[row][collumn]);
                }
            }
        });
    }

    @Test
    public void fillBoardAllKings() {
        int boardSize = 8;
        Board board = new Board(boardSize);
        PieceType[][] actualGrid = board.fillBoard(PieceType.KING);

        assertAll(() -> {
            for (int row = 0; row < boardSize; row++) {
                for (int collumn = 0; collumn < boardSize; collumn++) {
                    assertEquals(PieceType.KING, actualGrid[row][collumn]);
                }
            }
        });
    }
}