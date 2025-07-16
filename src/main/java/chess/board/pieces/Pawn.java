package chess.board.pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, Point position) {
        super(isWhite, position);
        this.type = PieceType.PAWN;
        this.typeChar = 'P';
//        this.isWhite = isWhite;
        this.typeString = "pawn";

        this.moveablePoints = new Point[]{new Point(0, 1), new Point(0, 2)};
    }

    private boolean isOnStartingPosition(Point position) {
        return (isWhite() && position.y == 1) ||
                (!isWhite() && position.y == 6);
    }

//    private void addDiagonalMoves(List<Point> list, Point position, Piece[][] boardGrid) {
//        int multiplier = (isWhite) ? 1 : -1;
//        Point tempPoint = new Point(position.x + 1, position.y + multiplier);
//        if (boardGrid[tempPoint.y][tempPoint.x].isWhite != this.isWhite) list.add(tempPoint);
//        tempPoint = new Point(position.x - 1, position.y + multiplier);
//        if (boardGrid[tempPoint.y][tempPoint.x].isWhite != this.isWhite) list.add(tempPoint);
//    }

    @Override
    public Point[] getMoveablePoints(Point position, Piece[][] boardGrid) {
        List<Point> outputArray = new ArrayList<Point>();
        int multiplier = (isWhite) ? 1 : -1;
        int boardSize = boardGrid.length;


        Point tempPoint = new Point(position.x + 1, position.y + multiplier);
        if (tempPoint.x >= 0 && tempPoint.x < boardSize
                && tempPoint.y >= 0 && tempPoint.y < boardSize) {

            Piece targetPiece = boardGrid[tempPoint.y][tempPoint.x];
            if (targetPiece.getType() != PieceType.EMPTY &&
                    targetPiece.isWhite != this.isWhite) outputArray.add(tempPoint);
        }

        tempPoint = new Point(position.x - 1, position.y + multiplier);
        if (tempPoint.x >= 0 && tempPoint.x < boardSize
                && tempPoint.y >= 0 && tempPoint.y < boardSize) {

            Piece targetPiece = boardGrid[tempPoint.y][tempPoint.x];
            if (targetPiece.getType() != PieceType.EMPTY &&
                    targetPiece.isWhite != this.isWhite) outputArray.add(tempPoint);
        }

         tempPoint = new Point(position.x,
                position.y + multiplier);

        if (tempPoint.y < 0 || tempPoint.y >= boardGrid.length ||
                boardGrid[tempPoint.y][tempPoint.x].getType() != PieceType.EMPTY)
            return outputArray.toArray(new Point[0]);

        outputArray.add(tempPoint);

        if (!isOnStartingPosition(position)) return outputArray.toArray(new Point[0]);
        tempPoint = new Point(position.x, position.y + (2 * multiplier));
        outputArray.add(tempPoint);

        return outputArray.toArray(new Point[0]);
    }
}
