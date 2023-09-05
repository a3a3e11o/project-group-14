import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private UserInterface ui;

    public Game() {
        showStartWindow();
    }

    private void showStartWindow() {
        StartWindow startWindow = new StartWindow(this);
        startWindow.setVisible(true);
    }

    public void startGame() {
        initializeUI();
        initializeGameManager();

        setTitle("Міста");
        setSize(400, 120);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        setVisible(true);
    }

    private void initializeUI() {
        ui = new UserInterface();
        add(ui, BorderLayout.CENTER);
    }

    private void initializeGameManager() {
        new GameManager(ui);
    }
}