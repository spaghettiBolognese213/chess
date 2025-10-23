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

    private boolean isMate(Piece king, List<Piece> attackers, List<Piece> defenders) {
        List<Point> attackedPoints = attackedPositions(attackers);
        Point[] movablePointsKing = king.getMoveablePoints(king.getPosition(),getBoardState().getBoardGrid());
        System.out.println("inside isMate");
        int counter = 0;

        for (Piece defender : defenders) { // loops through all possible moves
            for (Point moveable : defender.getMoveablePoints(defender.getPosition(), boardState.getBoardGrid())) {
                counter++;
                System.out.println(counter);
                BoardState tempState = boardState.copy(); // copies boardstate

                Piece tempDefender = tempState.getPiece(defender.getPosition());
//                if (tempDefender.getType() == PieceType.EMPTY) continue;
                tempState.forceMove(tempDefender, moveable);

                Piece tempKing = tempState.getKing(!whiteTurn);
                List<Piece> tempAttackers = tempState.getWhitePieces(whiteTurn);

                System.out.println("changed with move:");
                tempState.printBoard();
                if (!isCheck(tempKing, tempAttackers)) {
//                    tempState.printBoard();
                    System.out.println("not checkmate");
                    return false;
                }
            }
        }

        // check attacked squares
//        king.canMove(boardState.getBoardGrid(), attackedPoints);

        // get all pieces that attack King
        // get all pieces that can block attacker
        // check if blocked also is still check
        System.out.println("Check mate");
        return true;
    }

    private boolean isCheck(Piece king, List<Piece> attackingPieces) {
        if (king == null) return false; // defensive check

        Point kingPosition = king.getPosition();

//        System.out.println("king: " + kingPosition.x + "," + kingPosition.y + "\n");

        for (Piece piece : attackingPieces) {
            Point[] moves = piece.getMoveablePoints(piece.getPosition(), boardState.getBoardGrid());
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

            if (isCheck(king, attackingPieces)) {
                if (!whiteTurn) {
                    whiteInCheck = true;
                }
                else { blackInCheck = true; }
                System.out.println("CHECK!");

                List<Piece> defenders = boardState.getWhitePieces(!whiteTurn);

                if (isMate(king, attackingPieces, defenders)) {
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
