package chess.board.pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(boolean isWhite, Point position) {
        super(isWhite, position);
        this.type = PieceType.ROOK;
        this.typeChar = 'R';
//        this.isWhite = isWhite;
        this.typeString = "rook";
    }

    @Override
    public Point[] getMoveablePoints(Point position, Piece[][] boardGrid) {
        List<Point> outputArray = new ArrayList<>();

        getPointsUntilLimit(outputArray, position, 1,0, boardGrid);
        getPointsUntilLimit(outputArray, position, -1,0, boardGrid);
        getPointsUntilLimit(outputArray, position, 0,1, boardGrid);
        getPointsUntilLimit(outputArray, position, 0,-1, boardGrid);

        return outputArray.toArray(new Point[0]);
    }
}
