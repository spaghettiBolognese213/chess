package chess.userInput;

import java.awt.Point;

import chess.board.BoardState;

public class UserInputHandler implements InputCallback {
    private Point selectedSquare = null;
    private BoardState boardState;

    @Override
    public void onSquareSelected(Point square) {
        System.out.println("point clicked at: (" + square.x + "," + square.y + ")");

        boardState.handleSelected(square);
    }

    public void setBoard(BoardState newBoardState) {
        boardState = newBoardState;}
}
