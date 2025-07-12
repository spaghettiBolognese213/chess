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
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                outputArray[i][j] = stringToPiece(stringArray[i][j]);
            }
        }
        return outputArray;
    }

    private Piece stringToPiece(String string) {
        return switch (string) {
            case "BISHOP" -> new Bishop();
            case "EMPTY" -> new EmptyPiece();
            case "KING" -> new King();
            case "KNIGHT" -> new Knight();
            case "PAWN" -> new Pawn();
            case "QUEEN" -> new Queen();
            case "ROOK" -> new Rook();
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }
}
