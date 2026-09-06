package chess.display;


import chess.Match;
import chess.board.BoardState;
import chess.board.pieces.Piece;
import chess.userInput.UserInputHandler;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    private BoardDisplay boardDisplay;
    private JPanel timerPanel;
    private JPanel historyPanel;
    private UserInputHandler userInputHandler;
    private GameOverMenu gameOverMenu;
    private static Display display = null;

    public static Display getInstance() {
        if (display == null) {
            display = new Display();
        }
        return display;
    }

    public Display() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // creating all panels
        timerPanel = new JPanel();
        timerPanel.setPreferredSize(new Dimension(800, 50));
        timerPanel.setBackground(Color.LIGHT_GRAY);
        timerPanel.add(new JLabel("Timer: 10:00"));

        historyPanel = new JPanel();
        historyPanel.setPreferredSize(new Dimension(200, 512));
        historyPanel.setBackground(Color.WHITE);
        historyPanel.add(new JLabel("Move History"));

        // creating inputHandler
        userInputHandler = new UserInputHandler();

        boardDisplay = new BoardDisplay(null);
        boardDisplay.setInputCallback(userInputHandler);

        // structuring the panels
        JPanel westContainer = new JPanel();
        westContainer.setLayout(new BorderLayout());
        westContainer.add(boardDisplay, BorderLayout.CENTER);
        westContainer.add(timerPanel, BorderLayout.NORTH);

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BorderLayout());
        centerContainer.add(westContainer, BorderLayout.CENTER);
        centerContainer.add(historyPanel, BorderLayout.EAST);

//        add(centerContainer, BorderLayout.CENTER); // Add combined container to frame

        JPanel overlayContainer = new JPanel();
        overlayContainer.setLayout(new OverlayLayout(overlayContainer));

        gameOverMenu = new GameOverMenu();
        overlayContainer.add(gameOverMenu);
        overlayContainer.add(centerContainer);

        add(overlayContainer, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setBoard(Match match) {
        boardDisplay.setBoard(match.getBoardState());
        userInputHandler.setMatch(match);
    }

    public void drawBoard(Piece[][] currentGrid) {
        boardDisplay.drawPieces(currentGrid);
    }

    public void showEndGamePanel(Boolean isVisible, GameOverMenu.GameConclusion result) {
        gameOverMenu.setGameConclusion(result);
        gameOverMenu.setVisible(isVisible);
        gameOverMenu.revalidate();
        gameOverMenu.repaint();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Display::new);
    }
}
