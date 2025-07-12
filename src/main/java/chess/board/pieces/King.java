package chess.board.pieces;

public class King extends Piece {
    public King(boolean isWhite) {
        this.type = PieceType.KING;
        this.typeChar = 'K';
        this.isWhite = isWhite;
    }
}
