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

    public void printBoard() {
        // outer pieces

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.printf("| %c", boardGrid[i][j].getCharPiece());
            }
            System.out.println("|\n");
        }
    }
}
