import javax.swing.*;

public class MoveManager {
    private final UserInterface ui;
    private final CityManager cityManager;

    public MoveManager(UserInterface ui, CityManager cityManager) {
        this.ui = ui;
        this.cityManager = cityManager;
    }

    public void makeMove() {
        String userCity = ui.getUserInput();
        if (userCity.equalsIgnoreCase("Здаюсь")) {
            JOptionPane.showMessageDialog(null, "Комп'ютер переміг! Удачі наступного разу");
            System.exit(0);
        }
        if (!cityManager.containsCyrillicCharacters(userCity)) {
            JOptionPane.showMessageDialog(null, "Можна використовувати лише кирилицю");
            return;
        }
        if (!cityManager.isComputerTurn()) {
            if (!cityManager.getLastCity().isEmpty()) {
                if (Character.isLowerCase(userCity.charAt(0))) {
                    JOptionPane.showMessageDialog(null, "Місто має починатися з великої літери!");
                    return;
                }
            }
            if (!cityManager.isValidCity(userCity)) {
                JOptionPane.showMessageDialog(null, "Некоректне місто!");
                return;
            }
            if (cityManager.isUsedCity(userCity)) {
                JOptionPane.showMessageDialog(null, "Це місто вже було використане!");
                return;
            }
            cityManager.removeCity(userCity);
            ui.setResponseLabel("Місто на букву " + userCity.charAt(userCity.length() - 1) + ":");
            if (!cityManager.isGameOver()){
                makeComputerMove();
            }
        }
    }

    public void makeComputerMove() {
        String computerCity = findComputerCity();
        if (computerCity != null) {
            cityManager.removeCity(computerCity);
            ui.setResponseLabel("Місто на букву " + computerCity.charAt(computerCity.length() - 1) + ":");
        } else {
            JOptionPane.showMessageDialog(null, "Комп'ютер не може знайти місто. Гравець переміг!");
            GameManager.setIsPlayerTurn(true);
            return;
        }
        cityManager.removeCity(computerCity);
        ui.setComputerResponseLabel("Комп'ютер: " + computerCity);
        GameManager.setIsPlayerTurn(true);
    }

    private String findComputerCity() {
        char lastLetter = Character.toLowerCase(cityManager.getLastCity().charAt(cityManager.getLastCity().length() - 1));
        String computerCity = null;
        for (String city : cityManager.getCities()) {
            if (Character.toLowerCase(city.charAt(0)) == lastLetter && !cityManager.isUsedCity(city)) {
                computerCity = city;
                break;
            }
        }
        return computerCity;
    }
}