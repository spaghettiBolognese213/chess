package chess.board.pieces;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        this.type = PieceType.BISHOP;
        this.typeChar = 'B';
        this.isWhite = isWhite;
    }
}
