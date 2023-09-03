import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameManager {
    private final UserInterface ui;
    private CityManager cityManager;
    private MoveManager moveManager;

    public GameManager(UserInterface ui) {
        this.ui = ui;
        initializeCityManager();
        initializeMoveManager();
        ui.addActionListenerToPlayButton(new PlayButtonListener());

    }

    private void initializeCityManager() {
        cityManager = new CityManager();
        cityManager.initializeCities();
    }

    private void initializeMoveManager() {
        moveManager = new MoveManager(ui, cityManager);
    }

    private class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cityManager.isComputerTurn()) {
                moveManager.makeComputerMove();
            } else {
                moveManager.makeMove();
            }
        }
    }
}