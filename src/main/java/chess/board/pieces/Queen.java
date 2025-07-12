package chess.board.pieces;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        this.type = PieceType.QUEEN;
        this.typeChar = 'Q';
        this.isWhite = isWhite;
    }
}
