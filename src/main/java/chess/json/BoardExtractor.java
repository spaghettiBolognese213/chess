package chess.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import chess.board.PieceType;

public class BoardExtractor {
    private static String defaultBoardPath = "boardStates/defaultBoard.json";

    public static PieceType[][] extractDefault() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            PieceType[][] retrievedData =
                    objectMapper.readValue(new File(defaultBoardPath), PieceType[][].class);
        } catch (IOException e) {
            System.err.println("Failed to load board: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }
}
