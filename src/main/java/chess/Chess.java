package chess;

import chess.board.Board;
import chess.display.*;
import chess.json.*;

public class Chess {
    private static int SIZE = 8;
    Board board;
    BoardExtractor boardExtractor;
    Display display;
    BoardDisplay boardDisplay;

    public Chess(BoardExtractor bExtractor) {
        board = new Board(SIZE);
        boardExtractor = bExtractor;
        board.getGridFromSave(bExtractor);
        display = new Display();
        display.setBoard(board);
        boardDisplay = new BoardDisplay(board);
    }

    public void play() {
        display.drawBoard(board.getBoardGrid());

        while (true);
    }
}
