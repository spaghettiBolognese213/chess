//package chess.board;
//
//import chess.board.pieces.*;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BoardTest {
//
//    @Test
//    public void fillBoardEmpty() {
//        int boardSize = 8;
//        Board board = new Board(boardSize);
//        Piece[][] actualGrid = board.fillBoard(new EmptyPiece());
//
//        assertAll(() -> {
//            for (int row = 0; row < boardSize; row++) {
//                for (int collumn = 0; collumn < boardSize; collumn++) {
//                    assertEquals(new EmptyPiece(), actualGrid[row][collumn]);
//                }
//            }
//        });
//    }
//
//    @Test
//    public void fillBoardAllKings() {
//        int boardSize = 8;
//        Board board = new Board(boardSize);
//        Piece[][] actualGrid = board.fillBoard(new King());
//
//        assertAll(() -> {
//            for (int row = 0; row < boardSize; row++) {
//                for (int collumn = 0; collumn < boardSize; collumn++) {
//                    assertEquals(new King(), actualGrid[row][collumn]);
//                }
//            }
//        });
//    }
//}