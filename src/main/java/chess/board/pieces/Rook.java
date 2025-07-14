package chess.board.pieces;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        this.type = PieceType.ROOK;
        this.typeChar = 'R';
        this.isWhite = isWhite;
        this.typeString = "rook";
    }
}
