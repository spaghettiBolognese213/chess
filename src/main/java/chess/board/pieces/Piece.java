package chess.board.pieces;

//import PieceType

public class Piece {
    protected PieceType type;
    protected char typeChar;

    public PieceType getType() {
        return type;
    }

    public Character getCharPiece() {
        return typeChar;
    }
}

