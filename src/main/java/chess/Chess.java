package chess;

import chess.board.BoardState;
import chess.display.*;
import chess.json.*;

public class Chess {
    private static int SIZE = 8;
    BoardState boardState;
    BoardExtractor boardExtractor;
    Display display;
    BoardDisplay boardDisplay;

    public Chess(BoardExtractor bExtractor) {
        boardState = new BoardState(SIZE);
        boardExtractor = bExtractor;
        boardState.getGridFromSave(bExtractor);
        display = new Display();
        display.setBoard(boardState);
        boardDisplay = new BoardDisplay(boardState);
    }

    public void play() {
        display.drawBoard(boardState.getBoardGrid());

        while (true);
    }
}
