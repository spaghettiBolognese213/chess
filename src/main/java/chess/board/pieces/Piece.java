package chess.board.pieces;

//import PieceType

public class Piece {
    protected PieceType type;
    protected char typeChar;
    protected boolean isWhite;
    protected String typeString;

    public PieceType getType() {
        return type;
    }

    public Character getCharPiece() {
        return typeChar;
    }

    public boolean isWhite() {return isWhite;}

    @Override
    public String toString() {
        return typeString;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piece other = (Piece) obj;
        return this.type == other.type && this.typeChar == other.typeChar;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(type, typeChar);
    }
}

