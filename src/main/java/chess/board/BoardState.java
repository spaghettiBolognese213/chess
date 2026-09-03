package chess.board;

import chess.board.pieces.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class BoardState {
    private Piece[][] boardGrid;
    private int boardSize;

    private boolean hasSelected;
    private Point selectedPoint;
    private List<Point> moveablePoints;

    private List<Piece> allWhitePieces;
    private List<Piece> allBlackPieces;
    private Piece whiteKing;
    private Piece blackKing;

    public BoardState(int size) {
        boardSize = size;
        boardGrid = new Piece[size][size];
        moveablePoints = new ArrayList<Point>();

        allWhitePieces = new ArrayList<Piece>();
        allBlackPieces = new ArrayList<Piece>();
    }

    private void setMembers( boolean hasSelected_,
                             Point selectedPoint_,
                             List<Point> moveablePoints_,
                             List<Piece> allWhitePieces_,
                             List<Piece> allBlackPieces_,
                             Piece whiteKing_,
                             Piece blackKing_) {
        hasSelected = hasSelected_;
        if (selectedPoint_ != null) selectedPoint = new Point(selectedPoint_.x, selectedPoint_.y);
        else selectedPoint = null;
        moveablePoints = copyList(moveablePoints_);
        allWhitePieces = copyList(allWhitePieces_);
        allBlackPieces = copyList(allBlackPieces_);
        whiteKing = whiteKing_.copy();
        blackKing = blackKing_.copy();
    }

    public BoardState copy() {
        BoardState copied = new BoardState(boardSize);
        copied.setGrid(this.boardGrid);

        copied.setMembers(hasSelected, selectedPoint, moveablePoints, allWhitePieces, allBlackPieces, whiteKing, blackKing);
//        System.out.println("original board");
//        this.printBoard();
//        System.out.println("copied board");
//        copied.printBoard();
//        System.out.println();
        return copied;
    }

    private <T> List<T> copyList(List<T> list) {
//        int size = Math.max(list1.length, list2.length);

//        System.out.println("compare list");
        List<T> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

    private <T> void printList(List<T> list) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            System.out.println(i + ": " + list.get(i));
        }
        System.out.println();
    }

    // interaction
    private void movePiece(Piece movingPiece, Point oldPoint, Point newPoint) {
        clearMoveablePoints();
        movingPiece.setPosition(newPoint);
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

        boardGrid[selectedPoint.y][selectedPoint.x].getMoveablePoints(selectedPoint, boardGrid);
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

    public List<Piece> getWhitePieces(boolean white) {
        return (white ? allWhitePieces : allBlackPieces);
    }

    public Piece getKing(boolean white) {
        return (white ? whiteKing : blackKing);
    }

    // board manipulation
    public void forceMove(Piece movingPiece, Point newPoint) {
        movePiece(movingPiece, movingPiece.getPosition(), newPoint);
    }

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

//        boardGrid = newGrid;
        for (int col = 0; col < boardSize; col++) {
            for (int row = 0; row < boardSize; row++) {
                boardGrid[col][row] = newGrid[col][row].copy();
            }
        }

        boardSize = boardGrid.length;
        Piece tempPiece;

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                tempPiece = boardGrid[row][col];
            }
        }

        buildPieceLists();
    }

    public void buildPieceLists() {
        allBlackPieces.clear();
        allWhitePieces.clear();

        boardSize = boardGrid.length;
        Piece tempPiece;

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                tempPiece = boardGrid[row][col];

                if (tempPiece.getType() != PieceType.EMPTY){
                    if (tempPiece.isWhite()) {
                        allWhitePieces.add(tempPiece);
                        if (tempPiece.getType() == PieceType.KING) whiteKing = tempPiece;
                    }
                    else if (!tempPiece.isWhite()) {
                        allBlackPieces.add(tempPiece);
                        if (tempPiece.getType() == PieceType.KING) blackKing = tempPiece;
                    }
                }
            }
        }
    }

    public List<Point> getMoveablePoints() {
        return moveablePoints;
    }

    // debugging
    public void printBoard() {
        final int longestSize = 9;
        int stringSize = (1 + 2 + longestSize) * 8;
        int preSpace = 0;
        int postSpace = 0;
        for (int row = 0; row < boardSize; row++) {
            System.out.printf("|");
            for (int col = 0; col < boardSize; col++) {
                String pieceStr = boardGrid[row][col].getType() == PieceType.EMPTY ? "none" : boardGrid[row][col].toString();
                int strlen = pieceStr == null ? 4 : pieceStr.length();

                int difference = longestSize - strlen;
                preSpace += difference / 2;
                if (difference % 2 == 0 ) postSpace += preSpace;
                else {
                    postSpace = 2 + preSpace;
                }
//                System.out.printf("|");
                for (int i = 0; i < preSpace; i++) {
                    System.out.printf(" ");
                }

                System.out.printf("%s", pieceStr);

                for (int i = 0; i < postSpace; i++) {
                    System.out.printf(" ");
                }
                System.out.printf("|");
//                stringSize += 3;
//                if (pieceStr == null) {
//                    stringSize += 4;
//                }
//                else {
//                    stringSize += boardGrid[row][col].toString().length();
//                }
                preSpace = 0;
                postSpace = 0;
            }

            System.out.printf("\n");

            for (int i = 0; i < stringSize; i++) {
                System.out.printf("-");
            }

            System.out.printf("\n");

//            stringSize = 1;
        }
    }
}
