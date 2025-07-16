package chess;

import chess.board.*;
import chess.board.pieces.Piece;

public class Match {
    private static int SIZE = 8;

    private BoardState boardState;
    private BoardState[] stateList;

    public Match(Piece[][] boardGrid) {
        boardState = new BoardState(SIZE);
        boardState.setGrid(boardGrid);
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public Piece[][] getBoardGrid() {
        return boardState.getBoardGrid();
    }
}
