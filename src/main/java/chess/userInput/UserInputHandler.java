package chess.userInput;

import java.awt.Point;

import chess.Match;
import chess.board.BoardState;

public class UserInputHandler implements InputCallback {
    private Point selectedSquare = null;
//    private BoardState boardState;
    private Match match;

    @Override
    public void onSquareSelected(Point square) {
        match.handleSelectSquare(square);
    }

    public void setMatch(Match newMatch) {
        match = newMatch;}
}
