package chess.json;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import chess.board.pieces.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BoardExtractor {
    public Piece[][] extractDefault() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            InputStream inputStream = BoardExtractor.class.getClassLoader()
                    .getResourceAsStream("boardStates/defaultBoard.json");


            if (inputStream == null) {
                throw new FileNotFoundException("defaultBoard.json not found in resources.");
            }

            BoardJsonWrapper wrapper = objectMapper.readValue(inputStream, BoardJsonWrapper.class);
            String[][] retrievedData = wrapper.boardState;

            Piece[][] pieceGrid = stringGridToPieceGrid(retrievedData, retrievedData.length);
            return pieceGrid;

        } catch (IOException e) {
            System.err.println("Failed to load board: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Piece[][] stringGridToPieceGrid(String[][] stringArray, int gridSize) {
        Piece[][] outputArray = new Piece[gridSize][gridSize];

        String[] pieceId;
        boolean isWhite;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                pieceId = stringArray[i][j].split(" ");

                if (!pieceId[0].equals("EMPTY")) outputArray[i][j] = stringToPiece(pieceId[0], pieceId[1], new Point(j, i));
                else outputArray[i][j] = stringToPiece(pieceId[0], "W", new Point(j, i));
            }
        }
        return outputArray;
    }

    private Piece stringToPiece(String string, String colourId, Point position) {
        boolean isWhite = (colourId.equals("W"));
        return switch (string) {
            case "BISHOP" -> new Bishop(isWhite, position);
            case "EMPTY" -> new EmptyPiece(isWhite, position);
            case "KING" -> new King(isWhite, position);
            case "KNIGHT" -> new Knight(isWhite, position);
            case "PAWN" -> new Pawn(isWhite, position);
            case "QUEEN" -> new Queen(isWhite, position);
            case "ROOK" -> new Rook(isWhite, position);
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }
}
