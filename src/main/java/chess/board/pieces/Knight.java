package chess.board.pieces;

import java.awt.Point;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        this.type = PieceType.KNIGHT;
        this.typeChar = 'N';
        this.isWhite = isWhite;
        this.typeString = "knight";
        this.moveablePoints = new Point[]{
                new Point(1, 2),
                new Point(2, 1),
                new Point(2, -1),
                new Point(1, -2),
                new Point(-1, 2),
                new Point(-2, 1),
                new Point(-2, -1),
                new Point(-1, -2)
        };
    }
}
