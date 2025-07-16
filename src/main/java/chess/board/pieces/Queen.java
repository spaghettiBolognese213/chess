package chess.board.pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(boolean isWhite, Point position) {
        super(isWhite, position);
        this.type = PieceType.QUEEN;
        this.typeChar = 'Q';
//        this.isWhite = isWhite;
        this.typeString = "queen";
    }

    @Override
    public Point[] getMoveablePoints(Point position, Piece[][] boardGrid) {
        List<Point> outputArray = new ArrayList<>();

        getPointsUntilLimit(outputArray, position, 1,0, boardGrid);
        getPointsUntilLimit(outputArray, position, -1,0, boardGrid);
        getPointsUntilLimit(outputArray, position, 0,1, boardGrid);
        getPointsUntilLimit(outputArray, position, 0,-1, boardGrid);

        getPointsUntilLimit(outputArray, position, 1,1, boardGrid);
        getPointsUntilLimit(outputArray, position, -1,1, boardGrid);
        getPointsUntilLimit(outputArray, position, 1,-1, boardGrid);
        getPointsUntilLimit(outputArray, position, -1,-1, boardGrid);

        return outputArray.toArray(new Point[0]);
    }
}
