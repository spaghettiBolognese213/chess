package chess;

import chess.board.*;
import chess.board.pieces.EmptyPiece;
import chess.board.pieces.Piece;
import chess.board.pieces.PieceType;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Match {
    private static int SIZE = 8;

    private BoardState boardState;
    private List<BoardState> stateList;

    private Boolean whiteTurn;
    private Boolean whiteInCheck;
    private Boolean blackInCheck;

    private String winner;
    // 0 no one
    // 1 white
    // 2 black

    public Match(Piece[][] boardGrid) {
        boardState = new BoardState(SIZE);
        boardState.setGrid(boardGrid);
        whiteTurn = true;
        stateList = new ArrayList<BoardState>();
        stateList.add(boardState);
        winner = "";
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public Piece[][] getBoardGrid() {
        return boardState.getBoardGrid();
    }

    private boolean isAllowedPiece(Piece selectedPiece, Piece clickedPiece, Point square) {
        if (selectedPiece == null) {
            if (clickedPiece == null || clickedPiece.isWhite() != whiteTurn) {
                return false;
            }
        }

        if (clickedPiece.getType() == PieceType.EMPTY && !boardState.getMoveablePoints().contains(square)) return false;

        if (selectedPiece != null) {
            boolean isReselectingOwnPiece = (clickedPiece != null && clickedPiece.isWhite()) == whiteTurn;
            boolean isMoveAttempt = boardState.getMoveablePoints().contains(square);

            if (!isReselectingOwnPiece && !isMoveAttempt) {
                return false;
            }
        }
        return true;
    }

    // check if king has a move that is not in this list<point>
    private List<Point> attackedPositions(List<Piece> allPieces) {
        List<Point> attackedPoints = new ArrayList<Point>();
        Point[] pointBuffer;

        for (Piece piece : allPieces) {
            pointBuffer = piece.getMoveablePoints(piece.getPosition(), boardState.getBoardGrid());
            attackedPoints.addAll(List.of(pointBuffer));
        }

        return attackedPoints;
    }

    private boolean canBeBlocked(Piece Target, List<Piece> attackers) {


        return false;
    }

    private boolean isMate(Piece king, List<Piece> attackers) {
//        List<Point> attackedPoints = attackedPositions(attackers);
//        Point[] movablePointsKing = king.getMoveablePoints(king.getPosition(),getBoardState().getBoardGrid());
        List<Piece> defenders = boardState.getWhitePieces(!whiteTurn);

        for (Piece defender : defenders) { // loops through all possible moves
            for (Point moveable : defender.getMoveablePoints(defender.getPosition(), boardState.getBoardGrid())) {

                BoardState tempState = boardState.copy();

                Piece tempDefender = tempState.getPiece(defender.getPosition());

                if (tempDefender.getType() == PieceType.EMPTY) {
                    continue;
                }
                tempState.forceMove(tempDefender, new Point(moveable.x, moveable.y));

                List<Piece> tempAttackers = tempState.getWhitePieces(whiteTurn);
                Piece tempKing = (tempDefender.getType() == PieceType.KING) ? tempDefender : tempState.getKing(!whiteTurn);

                if (!isCheck(tempKing, tempAttackers, tempState.getBoardGrid())) {
                    return false;
                }
            }
        }

        System.out.println("Check mate");
        return true;
    }

    private boolean isCheck(Piece king, List<Piece> attackingPieces, Piece[][] grid) {
        if (king == null) return false; // defensive check
        if (grid == null) grid = boardState.getBoardGrid();

        Point kingPosition = king.getPosition();

        for (Piece piece : attackingPieces) {
            Point[] moves = piece.getMoveablePoints(piece.getPosition(), grid);
            for (Point p : moves) {
//                System.out.println("points: " + p.x + "," + p.y);
                if (p.equals(kingPosition)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void handleSelectSquare(Point square) {
        Piece clickedPiece = boardState.getPiece(square);
        Piece selectedPiece = boardState.getSelectedPiece();

        if (!isAllowedPiece(selectedPiece, clickedPiece, square)) return;

        boolean didMove = boardState.handleSelected(square);
        if (didMove) {
            stateList.add(boardState); // maybe make a deep copy here

            boardState.buildPieceLists();

            List<Piece> attackingPieces;
            Piece king;

            attackingPieces = boardState.getWhitePieces(whiteTurn);
            king = boardState.getKing(!whiteTurn);

            if (isCheck(king, attackingPieces, null)) {
                if (!whiteTurn) {
                    whiteInCheck = true;
                }
                else { blackInCheck = true; }
                System.out.println("CHECK!");

                if (isMate(king, attackingPieces)) {
//                if (isMate(king, defenders, attackingPieces)) {
                    winner = whiteTurn ? "WHITE" : "BLACK";
                    System.out.println(winner + " has won!");

                }
            }
            whiteTurn = !whiteTurn;
//            boardState.printBoard();
        }
    }
}
