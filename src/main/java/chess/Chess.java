package chess;

import chess.board.Board;
import chess.json.*;

public class Chess {
    private static int SIZE = 8;
    Board board;
    BoardExtractor boardExtractor;

    public Chess(BoardExtractor bExtractor) {
        board = new Board(SIZE);
        boardExtractor = bExtractor;
    }

    public void play() {
        board.printBoard();
    }
}
