package chess.board.pieces;

public class EmptyPiece extends Piece{
    public EmptyPiece() {
        this.type = PieceType.EMPTY;
        this.typeChar = ' ';
    }
}
