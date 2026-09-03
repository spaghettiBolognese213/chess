package chess.board.pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, Point position) {
        super(isWhite, position);
        this.type = PieceType.BISHOP;
        this.typeChar = 'B';
        this.typeString = "bishop";
        moveablePoints = null;
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

    @Override
    public Piece copy() {
        Bishop newBishop = new Bishop(isWhite, new Point(this.getPosition().x, this.getPosition().y));
        newBishop.selected = selected;
        newBishop.moveable = moveable;

        if (this.moveablePoints != null)
            System.out.println("moveable is not null");

        return newBishop;
    }
}
