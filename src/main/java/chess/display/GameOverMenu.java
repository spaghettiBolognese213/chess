package chess.display;

import chess.Chess;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverMenu extends JPanel {
    public enum GameConclusion{
        WHITE_WON,
        BLACK_WON,
        DRAW
    }

    private JPanel menuPanel;
    private GameConclusion gameConclusion;
    private JPanel labelPanel;
    private JLabel gameConclusionLabel = new JLabel("placeholder", SwingConstants.CENTER);

    public GameOverMenu() {
        setPreferredSize(new Dimension(200, 300));
        setMaximumSize(new Dimension(200,300));
        setLayout(new BorderLayout());

        createPanel();

        add(menuPanel, BorderLayout.CENTER);
        setVisible(false);
    }

    private void createPanel() {
        labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());
        labelPanel.setPreferredSize(new Dimension(200, 50));
        labelPanel.setMaximumSize(new Dimension(200, 50));
        labelPanel.setBorder(new EmptyBorder(10,10,30,10));
        labelPanel.add(gameConclusionLabel, BorderLayout.CENTER);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBorder(new EmptyBorder(10,10,10,10));
        menuPanel.add(gameConclusionLabel, BorderLayout.NORTH);

        addButtons();
    }

    private void addButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton resetGameButton = new JButton("Start new game");
        resetGameButton.setPreferredSize(new Dimension(125, 20));
        resetGameButton.setMaximumSize(new Dimension(125, 20));
        resetGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Chess.getInstance().play();
                Display.getInstance().showEndGamePanel(false, null);
            }
        });

        buttonPanel.add(resetGameButton);

        menuPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    public void setGameConclusion(GameConclusion result) {
        String text = switch(result) {
            case WHITE_WON -> "White won";
            case BLACK_WON -> "Black won";
            case DRAW -> "Draw";
            default -> "Something went wrong";
        };

        gameConclusionLabel.setText(text);
    }
}
