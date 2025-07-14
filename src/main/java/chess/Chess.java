package chess;

import chess.board.Board;
import chess.display.Display;
import chess.json.*;

public class Chess {
    private static int SIZE = 8;
    Board board;
    BoardExtractor boardExtractor;
    Display display;

    public Chess(BoardExtractor bExtractor) {
        board = new Board(SIZE);
        boardExtractor = bExtractor;
        board.getGridFromSave(bExtractor);
//        display = new Display();
    }

    public void play() {
        System.out.println(board.getBoardString());
        while (true);
    }
}
