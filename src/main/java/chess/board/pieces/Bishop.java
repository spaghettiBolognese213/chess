package chess.board.pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        this.type = PieceType.BISHOP;
        this.typeChar = 'B';
        this.isWhite = isWhite;
        this.typeString = "bishop";
    }

    @Override
    public Point[] getMoveablePoints(Point position, Piece[][] boardGrid) {
        List<Point> outputArray = new ArrayList<>();

        getPointsUntilLimit(outputArray, position, 1,1, boardGrid);
        getPointsUntilLimit(outputArray, position, -1,1, boardGrid);
        getPointsUntilLimit(outputArray, position, 1,-1, boardGrid);
        getPointsUntilLimit(outputArray, position, -1,-1, boardGrid);

        return outputArray.toArray(new Point[0]);
    }
}
