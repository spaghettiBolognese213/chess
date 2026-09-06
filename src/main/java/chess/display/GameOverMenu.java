package chess.display;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameOverMenu extends JPanel {
    public enum GameConclusion{
        WHITE_WON,
        BLACK_WON,
        DRAW
    }

    private JPanel menuPanel;
    private GameOverMenu gameOverMenu = null;
    private GameConclusion gameConclusion;
//    private JLabel gameConclusionLabel;

    public GameOverMenu() {
        createPanel();
        // create panel
            // make layout of panel
        // add buttons
    }

    private void createPanel() {
//        this = new/ JPanel();
//        menuPanel.setPreferredSize(new Dimension(300, 500));
        setPreferredSize(new Dimension(300, 300));
        setLayout(new BorderLayout());

//        add(getGameConclusionLabel());

        setVisible(false);
    }

    private JLabel getGameConclusionLabel() {
        return switch(gameConclusion) {
            case WHITE_WON -> new JLabel("White won");
            case BLACK_WON -> new JLabel("Black won");
            case DRAW -> new JLabel("Draw");
            default -> new JLabel("Something went wrong");
        };
    }

    public void setGameConclusion(GameConclusion result) {
//        gameConclusion = result;
        JLabel label = switch(result) {
            case WHITE_WON -> new JLabel("White won");
            case BLACK_WON -> new JLabel("Black won");
            case DRAW -> new JLabel("Draw");
            default -> new JLabel("Something went wrong");
        };

        add(label);
    }
}
