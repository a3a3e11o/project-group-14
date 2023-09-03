import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame {
    private final Game game;

    public StartWindow(Game game) {
        this.game = game;

        setTitle("Міста");
        setSize(400, 120);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Вітаємо вас у грі дитинства і всіх розумників!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Старт");
        startButton.addActionListener(e -> startGame());
        buttonPanel.add(startButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void startGame() {
        game.startGame();
        dispose();
    }
}