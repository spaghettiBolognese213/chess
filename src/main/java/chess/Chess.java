package chess;

import chess.board.pieces.Piece;
import chess.display.*;
import chess.json.*;
import chess.Match;

public class Chess  {
    BoardExtractor boardExtractor;
    BoardDisplay boardDisplay;
    Match currentMatch;

    public Chess(BoardExtractor bExtractor) {
        boardExtractor = bExtractor;
    }

    public void play() {
        loadMatch();
        Display.getInstance().drawBoard(currentMatch.getBoardGrid());

        while (true);
    }

    public void loadMatch() {
        currentMatch = new Match(getGridFromSave(boardExtractor));
        Display.getInstance().setBoard(currentMatch);
        Display.getInstance().drawBoard(currentMatch.getBoardGrid());
        boardDisplay = new BoardDisplay(currentMatch.getBoardState());
    }

    public Piece[][] getGridFromSave(BoardExtractor boardExtractor) {
        Piece[][] result = boardExtractor.extractDefault();

        return result;
    }
}
