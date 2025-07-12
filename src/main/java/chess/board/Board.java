package chess.board;

import chess.board.pieces.*;
import chess.json.BoardExtractor;

public class Board {
    Piece[][] boardGrid;
    int boardSize;

    public Board(int size) {
        boardSize = size;
        boardGrid = new Piece[size][size];
        boardGrid = fillBoard(new Pawn());
    }

    public Piece[][] fillBoard(Piece value) {
        Piece[][] grid = new Piece[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int collumn = 0; collumn < boardSize; collumn++) {
                grid[row][collumn] = value;
            }
        }
        return grid;
    }

    public PieceType[][] getGridFromSave(BoardExtractor boardExtractor) {
//        PieceType[][] newGrid = new BoardExtractor();
        PieceType[][] newGrid = boardExtractor.extractDefault();

        return newGrid;
    }

    public String getBoardString() {
        StringBuilder outputString = new StringBuilder();
        String lineString = "———————————————————————————————————\n";

        // outer pieces
        outputString.append("  ");
        for (int i = 1; i <= boardSize; i++) {
            outputString.append("| ").append(i).append(" ");
        }
        outputString.append(" \n");
        outputString.append(lineString);

        int baseAscii = 65;
        for (int i = 0; i < boardSize; i++) {
            outputString.append((char) (baseAscii+i)).append(" ");
            for (int j = 0; j < boardSize; j++) {
                outputString.append("| ").append(boardGrid[i][j].getCharPiece()).append(" ");
            }
            outputString.append("|\n");
            outputString.append(lineString);
        }
        return outputString.toString();
    }
}
