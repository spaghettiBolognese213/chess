package chess;

import chess.board.Board;

public class Chess {
    private static int SIZE = 8;
    Board board;

    public Chess() {
        board = new Board(SIZE);
    }
}
