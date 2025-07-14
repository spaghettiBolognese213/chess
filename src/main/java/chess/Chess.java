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
//        display = new Display();
        boardDisplay = new BoardDisplay();
    }

    public void play() {
//        System.out.println(board.getBoardString());
        boardDisplay.drawPieces(board.getBoardGrid());

        while (true);
    }
}
