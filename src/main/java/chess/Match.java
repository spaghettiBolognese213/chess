package chess;

import chess.board.*;
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

    public Match(Piece[][] boardGrid) {
        boardState = new BoardState(SIZE);
        boardState.setGrid(boardGrid);
        whiteTurn = true;
        stateList = new ArrayList<BoardState>();
        stateList.add(boardState);
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

    private List<Point> attackedPositions(List<Piece> allPieces) {
        List<Point> attackedPoints = new ArrayList<Point>();
        Point[] pointBuffer;

        for (Piece piece : allPieces) {
            pointBuffer = piece.getMoveablePoints(piece.getPosition(), boardState.getBoardGrid());
            attackedPoints.addAll(List.of(pointBuffer));
        }

        return attackedPoints;
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
            }
            whiteTurn = !whiteTurn;
        }
    }
}
