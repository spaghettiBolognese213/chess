package chess.json;

import java.io.File;
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

                if (!pieceId[0].equals("EMPTY")) outputArray[i][j] = stringToPiece(pieceId[0], pieceId[1]);
                else outputArray[i][j] = stringToPiece(pieceId[0], "false");
            }
        }
        return outputArray;
    }

    private Piece stringToPiece(String string, String colourId) {
        boolean isWhite = (colourId.equals("W"));

        return switch (string) {
            case "BISHOP" -> new Bishop(isWhite);
            case "EMPTY" -> new EmptyPiece(isWhite);
            case "KING" -> new King(isWhite);
            case "KNIGHT" -> new Knight(isWhite);
            case "PAWN" -> new Pawn(isWhite);
            case "QUEEN" -> new Queen(isWhite);
            case "ROOK" -> new Rook(isWhite);
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }
}
