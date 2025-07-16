package chess.board;

import chess.board.pieces.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BoardState {
    private Piece[][] boardGrid;
    private int boardSize;

    private boolean hasSelected;
    private Point selectedPoint;
    private List<Point> moveablePoints;

    private List<Piece> allWhitePieces;
    private List<Piece> allBlackPieces;
    private int whiteKingIndex;
    private int blackKingIndex;

    public BoardState(int size) {
        boardSize = size;
        boardGrid = new Piece[size][size];
        moveablePoints = new ArrayList<Point>();

        allWhitePieces = new ArrayList<Piece>();
        allBlackPieces = new ArrayList<Piece>();
    }

    // interaction
    private void movePiece(Piece movingPiece, Point oldPoint, Point newPoint) {
        clearMoveablePoints();
        boardGrid[newPoint.y][newPoint.x] = movingPiece;
        boardGrid[oldPoint.y][oldPoint.x] = new EmptyPiece(false, new Point(oldPoint.y, oldPoint.x));
    }

    private void deselectSquare() {
        clearMoveablePoints();
        boardGrid[selectedPoint.y][selectedPoint.x].setSelected(false);
        hasSelected = false;
        selectedPoint = null;
    }

    private void selectSquare(Point newPoint) {
        selectedPoint = newPoint;
        boardGrid[selectedPoint.y][selectedPoint.x].setSelected(true);
        hasSelected = true;
        setMoveablePoints(selectedPoint, boardGrid[selectedPoint.y][selectedPoint.x]);
    }

    public boolean handleSelected(Point point) {
        if (hasSelected && selectedPoint.equals(point)) { // deselect
            deselectSquare();
        }
        else if (moveablePoints.contains(point) && !selectedPoint.equals(point)) {
            movePiece(boardGrid[selectedPoint.y][selectedPoint.x], selectedPoint, point);
            selectedPoint = point;
            deselectSquare();
            return true;
        }
        else if (hasSelected && !selectedPoint.equals(point)) { // reselect
            deselectSquare();
            selectSquare(point);
        }
        else if (!hasSelected) {
            selectSquare(point);
        }
        return false;
    }

    public void clearMoveablePoints() {
        for (Point point : moveablePoints) {
            boardGrid[point.y][point.x].setMoveable(false);
        }
        moveablePoints.clear();
    }

    // getters
    public Piece[][] getBoardGrid() {return boardGrid;}

    public Piece getSelectedPiece() {
        if (selectedPoint != null) return getPiece(selectedPoint);
        else return null;
    }

    public Piece getPiece(Point location) {
        return boardGrid[location.y][location.x];
    }

    public List<Piece> getWhitePieces() {return allWhitePieces;}

    public List<Piece> getBlackPieces() {return allBlackPieces;}

    // board manipulation
    public void setMoveablePoints(Point currentPoint, Piece piece) {
        Point[] tempArray = piece.getMoveablePoints(currentPoint, boardGrid);

        for (Point point : tempArray) {
            boardGrid[point.y][point.x].setMoveable(true);
            moveablePoints.add(point);
        }
    }

    public void setGrid(Piece[][] newGrid) {
        allWhitePieces.clear();
        allBlackPieces.clear();

        boardGrid = newGrid;
        boardSize = boardGrid.length;
        Piece tempPiece;

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                tempPiece = boardGrid[row][col];

                if (tempPiece.getType() != PieceType.EMPTY){
                    if (tempPiece.isWhite()) {
                        allWhitePieces.add(tempPiece);
                        if (tempPiece.getType() == PieceType.KING) whiteKingIndex = allWhitePieces.size() - 1;
                    }
                    else if (!tempPiece.isWhite()) {
                        allBlackPieces.add(tempPiece);
                        if (tempPiece.getType() == PieceType.KING) blackKingIndex = allBlackPieces.size() - 1;
                    }
                }
            }
        }
    }

    public List<Point> getMoveablePoints() {
        return moveablePoints;
    }
}
