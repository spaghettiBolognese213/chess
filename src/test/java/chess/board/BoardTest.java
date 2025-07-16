package chess.board;

import chess.board.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

//    @Test
//    public void fillBoardEmpty() {
//        int boardSize = 8;
//        BoardState boardState = new BoardState(boardSize);
//        Piece[][] actualGrid = boardState.fillBoard(new EmptyPiece(false));
//
//        assertAll(() -> {
//            for (int row = 0; row < boardSize; row++) {
//                for (int collumn = 0; collumn < boardSize; collumn++) {
//                    assertEquals(new EmptyPiece(false), actualGrid[row][collumn]);
//                }
//            }
//        });
//    }

//    @Test
//    public void fillBoardAllKings() {
//        int boardSize = 8;
//        BoardState boardState = new BoardState(boardSize);
//        Piece[][] actualGrid = boardState.fillBoard(new King(true));
//
//        assertAll(() -> {
//            for (int row = 0; row < boardSize; row++) {
//                for (int collumn = 0; collumn < boardSize; collumn++) {
//                    assertEquals(new King(true), actualGrid[row][collumn]);
//                }
//            }
//        });
//    }

//    @Test
//    public void printTestAllPawns() {
//        int boardSize = 8;
//        BoardState boardState = new BoardState(boardSize);
//        boardState.fillBoard(new Pawn(true));
//
//        String expectedString =
//                "  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8  \n" +
//                "———————————————————————————————————\n" +
//                "A | p | p | p | p | p | p | p | p |\n" +
//                "———————————————————————————————————\n" +
//                "B | p | p | p | p | p | p | p | p |\n" +
//                "———————————————————————————————————\n" +
//                "C | p | p | p | p | p | p | p | p |\n" +
//                "———————————————————————————————————\n" +
//                "D | p | p | p | p | p | p | p | p |\n" +
//                "———————————————————————————————————\n" +
//                "E | p | p | p | p | p | p | p | p |\n" +
//                "———————————————————————————————————\n" +
//                "F | p | p | p | p | p | p | p | p |\n" +
//                "———————————————————————————————————\n" +
//                "G | p | p | p | p | p | p | p | p |\n" +
//                "———————————————————————————————————\n" +
//                "H | p | p | p | p | p | p | p | p |\n" +
//                "———————————————————————————————————\n";
//
//        String actualString = boardState.getBoardString();
//        assertEquals(expectedString, actualString);
//    }
}