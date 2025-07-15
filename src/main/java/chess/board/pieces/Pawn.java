package chess.board.pieces;

import java.awt.*;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        this.type = PieceType.PAWN;
        this.typeChar = 'P';
        this.isWhite = isWhite;
        this.typeString = "pawn";

        this.moveablePoints = new Point[]{new Point(0, 1), new Point(0, 2)};
    }
}
