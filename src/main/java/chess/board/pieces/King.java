package chess.board.pieces;

import java.awt.*;

public class King extends Piece {
    public King(boolean isWhite) {
        this.type = PieceType.KING;
        this.typeChar = 'K';
        this.isWhite = isWhite;
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
}
