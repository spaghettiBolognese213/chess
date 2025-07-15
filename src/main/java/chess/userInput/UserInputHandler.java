package chess.userInput;

import java.awt.Point;

import chess.board.pieces.Piece;
import chess.board.Board;

public class UserInputHandler implements InputCallback {
    private Point selectedSquare = null;
    private Board board;

    @Override
    public void onSquareSelected(Point square) {
        System.out.println("point clicked at: (" + square.x + "," + square.y + ")");

        board.handleSelected(square);
    }

    public void setBoard(Board newBoard) {board = newBoard;}
}
