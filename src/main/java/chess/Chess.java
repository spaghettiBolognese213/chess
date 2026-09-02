package chess;

import chess.board.pieces.Piece;
import chess.display.*;
import chess.json.*;
import chess.Match;

public class Chess  {
    BoardExtractor boardExtractor;
    Display display;
    BoardDisplay boardDisplay;
    Match currentMatch;

    public Chess(BoardExtractor bExtractor) {
        boardExtractor = bExtractor;
        display = new Display();
    }

    public void play() {
        loadMatch();
        display.drawBoard(currentMatch.getBoardGrid());

        while (true);
    }

    public void loadMatch() {
        currentMatch = new Match(getGridFromSave(boardExtractor));
        display.setBoard(currentMatch);
        display.drawBoard(currentMatch.getBoardGrid());
        boardDisplay = new BoardDisplay(currentMatch.getBoardState());
    }

    public Piece[][] getGridFromSave(BoardExtractor boardExtractor) {
        Piece[][] result = boardExtractor.extractDefault();;

        return result;
    }
}
