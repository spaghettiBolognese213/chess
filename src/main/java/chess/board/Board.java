package chess.board;

import chess.board.pieces.*;
import chess.json.BoardExtractor;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Board {
    Piece[][] boardGrid;
    int boardSize;
    boolean hasSelected;
    Point selectedPoint;
    List<Point> moveablePoints;

    public Board(int size) {
        boardSize = size;
        boardGrid = new Piece[size][size];
        boardGrid = fillBoard(new Pawn(true));
        moveablePoints = new ArrayList<Point>();
    }

    private void movePiece(Piece movingPiece, Point oldPoint, Point newPoint) {
        clearMoveablePoints();
        boardGrid[newPoint.y][newPoint.x] = movingPiece;
        boardGrid[oldPoint.y][oldPoint.x] = new EmptyPiece(false);
    }

    private void deselectSquare() {
        clearMoveablePoints();
        boardGrid[selectedPoint.y][selectedPoint.x].setSelected(false);
        hasSelected = false;
    }

    private void selectSquare(Point newPoint) {
        selectedPoint = newPoint;
        boardGrid[selectedPoint.y][selectedPoint.x].setSelected(true);
        hasSelected = true;
        setMoveablePoints(selectedPoint, boardGrid[selectedPoint.y][selectedPoint.x]);
    }

    public void handleSelected(Point point) {
        if (hasSelected && selectedPoint.equals(point)) { // deselect
            deselectSquare();
        }
        else if (moveablePoints.contains(point) && !selectedPoint.equals(point)) {
            movePiece(boardGrid[selectedPoint.y][selectedPoint.x], selectedPoint, point);
            selectedPoint = point;
            deselectSquare();
        }
        else if (hasSelected && !selectedPoint.equals(point)) { // reselect
            deselectSquare();
            selectSquare(point);
        }
        else if (!hasSelected) {
            selectSquare(point);
        }
    }

    public void clearMoveablePoints() {
        for (Point point : moveablePoints) {
            boardGrid[point.y][point.x].setMoveable(false);
        }
        moveablePoints.clear();
    }

    public void setMoveablePoints(Point currentPoint, Piece piece) {
        Point[] tempArray = piece.getMoveablePoints(currentPoint, boardGrid);

        for (Point point : tempArray) {
            boardGrid[point.y][point.x].setMoveable(true);
            moveablePoints.add(point);
        }
    }

    public Piece[][] getBoardGrid() {return boardGrid;}

    public Piece[][] fillBoard(Piece value) {
        Piece[][] grid = new Piece[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int collumn = 0; collumn < boardSize; collumn++) {
                grid[row][collumn] = value;
            }
        }
        return grid;
    }

    public void getGridFromSave(BoardExtractor boardExtractor) {
        Piece[][] newGrid = boardExtractor.extractDefault();
        boardGrid = newGrid; // maybe has different size
    }

    public String getBoardString() {
        StringBuilder outputString = new StringBuilder();
        String lineString = "———————————————————————————————————\n";

        outputString.append("  ");
        for (int i = 1; i <= boardSize; i++) {
            outputString.append("| ").append(i).append(" ");
        }
        outputString.append(" \n");
        outputString.append(lineString);

        int baseAscii = 65;
        String pieceIcon;
        for (int i = 0; i < boardSize; i++) {
            outputString.append((char) (baseAscii+i)).append(" ");
            for (int j = 0; j < boardSize; j++) {

                pieceIcon = boardGrid[i][j].getCharPiece().toString();
                if (boardGrid[i][j].isWhite()) pieceIcon = pieceIcon.toLowerCase();

                outputString.append("| ").append(pieceIcon).append(" ");
            }
            outputString.append("|\n");
            outputString.append(lineString);
        }
        return outputString.toString();
    }
}
