import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CityManager {
    private final ArrayList <String> cities;
    private final ArrayList <String> usedCities;
    private String lastCity = "";


    public CityManager() {
        cities = new ArrayList<>();
        usedCities = new ArrayList<>();
        loadCitiesFromFile();
    }

    public boolean isUsedCity(String city) {

        return usedCities.contains(city);
    }

    public boolean isValidCity(String city) {
        if (usedCities.contains(city)) {
            return false;
        } else if (!lastCity.isEmpty()) {
            char firstLetter = Character.toLowerCase(city.charAt(0));
            char lastLetter = Character.toLowerCase(lastCity.charAt(lastCity.length() - 1));
            if (firstLetter != lastLetter) {
                return false;
            }
        } else if (!cities.contains(city)) {
            return false;
        }
        return true;
    }

    public boolean isGameOver() {
        return cities.isEmpty();
    }

    public boolean containsCyrillicCharacters(String city) {
        for (char c : city.toCharArray()) {
            if (!Character.isLetter(c) || Character.UnicodeBlock.of(c) != Character.UnicodeBlock.CYRILLIC) {
                return false;
            }
        }
        return true;
    }

    public boolean isComputerTurn() {
        if (GameManager.isPlayerTurn()) {
            return false;
        }
        return true;
    }

    public String getLastCity() {
        return lastCity;
    }

    public void removeCity(String city) {
        cities.remove(city);
        lastCity = city;
    }

    public ArrayList <String> getCities() {
        return cities;
    }

    public void initializeCities() {
        loadCitiesFromFile();
    }

    private void loadCitiesFromFile() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("cities.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cities.add(line.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}