package chess.board;

import chess.board.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void fillBoardEmpty() {
        int boardSize = 8;
        Board board = new Board(boardSize);
        Piece[][] actualGrid = board.fillBoard(new EmptyPiece());

        assertAll(() -> {
            for (int row = 0; row < boardSize; row++) {
                for (int collumn = 0; collumn < boardSize; collumn++) {
                    assertEquals(new EmptyPiece(), actualGrid[row][collumn]);
                }
            }
        });
    }

    @Test
    public void fillBoardAllKings() {
        int boardSize = 8;
        Board board = new Board(boardSize);
        Piece[][] actualGrid = board.fillBoard(new King());

        assertAll(() -> {
            for (int row = 0; row < boardSize; row++) {
                for (int collumn = 0; collumn < boardSize; collumn++) {
                    assertEquals(new King(), actualGrid[row][collumn]);
                }
            }
        });
    }

    @Test
    public void printTestAllPawns() {
        int boardSize = 8;
        Board board = new Board(boardSize);
        board.fillBoard(new King());

        String expectedString =
                "  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8  \n" +
                "———————————————————————————————————\n" +
                "A | p | p | p | p | p | p | p | p |\n" +
                "———————————————————————————————————\n" +
                "B | p | p | p | p | p | p | p | p |\n" +
                "———————————————————————————————————\n" +
                "C | p | p | p | p | p | p | p | p |\n" +
                "———————————————————————————————————\n" +
                "D | p | p | p | p | p | p | p | p |\n" +
                "———————————————————————————————————\n" +
                "E | p | p | p | p | p | p | p | p |\n" +
                "———————————————————————————————————\n" +
                "F | p | p | p | p | p | p | p | p |\n" +
                "———————————————————————————————————\n" +
                "G | p | p | p | p | p | p | p | p |\n" +
                "———————————————————————————————————\n" +
                "H | p | p | p | p | p | p | p | p |\n" +
                "———————————————————————————————————\n";

        String actualString = board.getBoardString();
        assertEquals(expectedString, actualString);
    }
}