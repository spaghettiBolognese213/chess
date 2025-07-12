package chess.board.pieces;

public class EmptyPiece extends Piece{
    public EmptyPiece(boolean isWhite) {
        this.type = PieceType.EMPTY;
        this.typeChar = ' ';
    }
}
