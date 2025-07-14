package chess.board.pieces;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        this.type = PieceType.KNIGHT;
        this.typeChar = 'N';
        this.isWhite = isWhite;
        this.typeString = "knight";
    }
}
