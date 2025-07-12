package chess.board.pieces;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        this.type = PieceType.PAWN;
        this.typeChar = 'p';
        this.isWhite = isWhite;
    }
}
