import javax.swing.*;
public class MoveManager {
    private UserInterface ui;
    private CityManager cityManager;
    public MoveManager(UserInterface ui, CityManager cityManager) {
        this.ui = ui;
        this.cityManager = cityManager;
    }
    public void makeMove() {
        String userCity = ui.getUserInput();
        if (userCity.equalsIgnoreCase("Здаюсь")){
            JOptionPane.showMessageDialog(null, "Комп'ютер переміг! Удачі наступого разу");
            System.exit(0);
        }

        if (!cityManager.isValidCity(userCity)) {
            JOptionPane.showMessageDialog(null, "Некоректне місто!");
            return;
        }
        if (cityManager.isUsedCity(userCity)) {
            JOptionPane.showMessageDialog(null, "Це місто вже було використане!");
            return;
        }
        if (!cityManager.containsCyrillicCharacters(userCity)){
            JOptionPane.showMessageDialog(null, "Можна використовувати лише кирилицю");
            return;
        }

        if (!cityManager.isComputerTurn()) {
            if (!cityManager.getLastCity().isEmpty()) {
                char firstLetter = userCity.charAt(0);
                if (Character.isLowerCase(firstLetter)) {
                    JOptionPane.showMessageDialog(null, "Місто має починатися з великої літери!");
                    return;
                }
            }
            cityManager.removeCity(userCity);
            ui.setResponseLabel("Місто на букву " + userCity.charAt(userCity.length() - 1) + ":");
        } else {
            computerMove();
        }

    }
    private void computerMove() {
        char lastLetter = cityManager.getLastCity().charAt(cityManager.getLastCity().length() - 1);
        for (String city : cityManager.getCities()) {
            if (city.charAt(0) == lastLetter) {
                cityManager.removeCity(city);
                ui.setResponseLabel("Відповідь комп'ютера: " + city);
                break;
            }
            else if (cityManager.getCities().isEmpty()){
                JOptionPane.showMessageDialog(null, "Ви перемогли! Вітаємо!");
                System.exit(0);
            }
        }
    }
}