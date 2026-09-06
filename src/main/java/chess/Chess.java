package chess;

import chess.board.pieces.Piece;
import chess.display.*;
import chess.json.*;
import chess.Match;

public class Chess  {
    private static Chess instance = null;
    BoardExtractor boardExtractor;
    BoardDisplay boardDisplay;
    Match currentMatch;
    boolean hasOngoingMatch = true;

    public static Chess getInstance(BoardExtractor bExtractor) {
        if (instance == null) {
            instance = new Chess(bExtractor);
        }
        return instance;
    }

    public static Chess getInstance() {
        if (instance == null) {
            throw new NullPointerException("chess game instance is null");
        }
        return instance;
    }

    private Chess(BoardExtractor bExtractor) {
        boardExtractor = bExtractor;
    }

    public void runGame() {
        play();
    }

    public void play() {
        loadMatch();
        Display.getInstance().drawBoard(currentMatch.getBoardGrid());
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
