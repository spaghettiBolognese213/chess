package chess.board.pieces;

import java.awt.*;

public class EmptyPiece extends Piece{
    public EmptyPiece(boolean isWhite, Point position) {
        super(isWhite, position);
        this.type = PieceType.EMPTY;
        this.typeChar = ' ';
        this.typeString = "empty";
    }

    @Override
    public Piece copy() {
        EmptyPiece newPiece = new EmptyPiece(false, this.position);
        return newPiece;
    }
}
