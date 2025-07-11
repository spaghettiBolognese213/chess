package chess.board;

public class Board {
    PieceType[][] boardGrid;
    int boardSize;

    public Board(int size) {
        boardSize = size;
        boardGrid = new PieceType[size][size];
        boardGrid = fillBoard(PieceType.EMPTY);
    }

    public PieceType[][] fillBoard(PieceType value) {
        PieceType[][] grid = new PieceType[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int collumn = 0; collumn < boardSize; collumn++) {
                grid[row][collumn] = value;
            }
        }
        return grid;
    }
}
