package chess.board.pieces;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;


public class Piece {
    protected PieceType type;
    protected char typeChar;
    protected boolean isWhite;
    protected String typeString = null;

    protected boolean selected = false;
    protected Point[] moveablePoints;
    protected boolean moveable = false;

    protected Point position;

    public Piece(boolean newIsWhite, Point newPosition) {
        isWhite = newIsWhite;
        position = newPosition;
    }

    public Piece copy() {
        Piece newPiece = new Piece(isWhite, new Point(this.getPosition().x, this.getPosition().y));
        newPiece.typeChar = typeChar;
        newPiece.typeString = typeString;
        newPiece.selected = selected;
        newPiece.moveable = moveable;

        if (this.moveablePoints != null) {
            newPiece.moveablePoints = new Point[moveablePoints.length];
            for (int i = 0; i < moveablePoints.length; i++) {
                newPiece.moveablePoints[i] = moveablePoints[i];
            }
        }

        return newPiece;
    }

    protected List<Point> getPointsUntilLimit(List<Point> list, Point position, int rowMultiplier, int colMultiplier, Piece[][] boardGrid) {
        Point tempPoint;

        for (int i = 1; i < boardGrid.length; i++) {
            tempPoint = new Point(position.x + (i * rowMultiplier),
                    position.y + (i * colMultiplier));

            if ((tempPoint.x < boardGrid.length && tempPoint.x >= 0 && // within bounds
                    tempPoint.y < boardGrid.length && tempPoint.y >= 0) &&
                    boardGrid[tempPoint.y][tempPoint.x].getType() == PieceType.EMPTY) list.add(tempPoint);

            else if ((tempPoint.x < boardGrid.length && tempPoint.x >= 0 && // within bounds
                    tempPoint.y < boardGrid.length && tempPoint.y >= 0) &&
                    boardGrid[tempPoint.y][tempPoint.x].getType() != PieceType.EMPTY) {

                if (boardGrid[tempPoint.y][tempPoint.x].isWhite != this.isWhite) {
                    list.add(tempPoint);
                }
                return list;
            }
        }
        return list;
    }

    public Point getPosition() {return position;}

    public void setPosition(Point newPosition) {position = newPosition;}

    public boolean canMove(Piece[][] boardGrid, List<Point> attackedPositions) {
        Point[] possiblePoints = this.getMoveablePoints(position, boardGrid);

        for (Point point : possiblePoints) {
            if (!attackedPositions.contains(point)) return true;
        }

        return false;
    }

    public Point[] getMoveablePoints(Point position, Piece[][] boardGrid) {
        List<Point> outputArray = new ArrayList<>();
        Point tempPoint;

        for (Point moveablePoint : moveablePoints) {
            tempPoint = new Point(moveablePoint.x + position.x,
                    moveablePoint.y + position.y);
            if (tempPoint.x < boardGrid.length && tempPoint.x >= 0 && // within bounds
                    tempPoint.y < boardGrid.length && tempPoint.y >= 0) {
                if (boardGrid[tempPoint.y][tempPoint.x].getType() == PieceType.EMPTY ||
                        boardGrid[tempPoint.y][tempPoint.x].isWhite != this.isWhite) outputArray.add(tempPoint);
            }
        }

        return outputArray.toArray(new Point[0]);
    }

    public void setMoveable(Boolean value) {moveable = value;}

    public PieceType getType() {
        return type;
    }

    public Character getCharPiece() {
        return typeChar;
    }

    public boolean isWhite() {return isWhite;}

    @Override
    public String toString() {
        return typeString;
    }

    public Boolean canMoveTo() {
        return moveable;
    }

    public void setSelected(boolean value) {selected = value;}

    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piece other = (Piece) obj;
        return this.type == other.type && this.typeChar == other.typeChar;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(type, typeChar);
    }
}

