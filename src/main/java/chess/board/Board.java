package chess.board;

import chess.board.pieces.*;
import chess.json.BoardExtractor;

public class Board {
    Piece[][] boardGrid;
    int boardSize;

    public Board(int size) {
        boardSize = size;
        boardGrid = new Piece[size][size];
        boardGrid = fillBoard(new Pawn(true));
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
