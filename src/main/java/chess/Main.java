package chess;

import chess.Chess;
import chess.json.BoardExtractor;

public class Main {
    public static void main(String[] args) {
        BoardExtractor boardExtractor = new BoardExtractor();
        Chess game = new Chess(boardExtractor);

        game.play();
    }
}
