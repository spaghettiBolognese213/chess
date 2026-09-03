package chess.board.pieces;

import java.awt.*;

public class King extends Piece {
    public King(boolean isWhite, Point position) {
        super(isWhite, position);
        this.type = PieceType.KING;
        this.typeChar = 'K';
//        this.isWhite = isWhite;
        this.typeString = "king";
        this.moveablePoints = new Point[]{
                new Point(1,0),
                new Point(1,1),
                new Point(0,1),
                new Point(-1,1),
                new Point(-1,-0),
                new Point(-1,-1),
                new Point(0,-1),
                new Point(1,-1)
        };
    }

    public Piece copy() {
        King newPiece = new King(isWhite, new Point(this.getPosition().x, this.getPosition().y));
        newPiece.selected = selected;
        newPiece.moveable = moveable;

        return newPiece;
    }

}
