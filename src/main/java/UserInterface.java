import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserInterface extends JPanel {
    private final JLabel responseLabel;
    private final JLabel computerResponseLabel;
    private final JTextField inputField;
    private final JButton playButton;

    public UserInterface() {
        setLayout(new BorderLayout());

        responseLabel = new JLabel("Введіть назву міста:");
        computerResponseLabel = new JLabel("Комп'ютер: ");
        inputField = new JTextField();
        playButton = new JButton("Зробити хід");

        responseLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        computerResponseLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(responseLabel);
        mainPanel.add(inputField);
        mainPanel.add(computerResponseLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playButton.setPreferredSize(new Dimension(110, 30));
        buttonPanel.add(playButton);
        mainPanel.add(buttonPanel);
        add(mainPanel, BorderLayout.CENTER);
    }

    public String getUserInput() {
        return inputField.getText().trim();
    }

    public void setResponseLabel(String text) {
        responseLabel.setText(text);
    }

    public void setComputerResponseLabel(String text) {
        computerResponseLabel.setText(text);
    }

    public void addActionListenerToPlayButton(ActionListener listener) {
        playButton.addActionListener(listener);
    }

    public void clearInputField() {
        inputField.setText("");
    }
}
