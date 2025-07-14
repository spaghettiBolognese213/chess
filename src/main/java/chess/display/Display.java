package chess.display;


import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    private JLayeredPane layeredPane;

    Display() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(512, 512));

        ImageIcon boardIcon = new ImageIcon(
                getClass().getClassLoader().getResource("images/chessBoard.jpg")
        );

        JLabel boardLabel = new JLabel(boardIcon);
        boardLabel.setBounds(0, 0, 512, 512);
        layeredPane.add(boardLabel, Integer.valueOf(0));

        setContentPane(layeredPane);
        pack();
        setLocationRelativeTo(null); // center on screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Display::new);
    }
}
