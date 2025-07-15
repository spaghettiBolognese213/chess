package chess.board.pieces;

import java.awt.*;
import java.awt.Point;

public class Piece {
    protected PieceType type;
    protected char typeChar;
    protected boolean isWhite;
    protected String typeString = null;

    protected boolean selected = false;
    protected Point[] moveablePoints;
    protected boolean moveable = false;

    public Point[] getMoveablePoints() {
        if (moveablePoints == null) return null;
        return moveablePoints;
    }

    public void setMoveable(Boolean value) {moveable = value;}

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

    public Boolean canMoveTo() {
        return moveable;
    }

    public void setSelected(boolean value) {selected = value;}

    public boolean isSelected() {
        return selected;
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

