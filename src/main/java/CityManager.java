import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class CityManager {
    private final Set<String> cities;
    private final Set<String> usedCities;
    private String lastCity = "";
    private int userCityCount = 0;
    private int computerCityCount = 0;

    public CityManager() {
        cities = new HashSet<>();
        usedCities = new HashSet<>();
        loadCitiesFromFile();
    }

    public boolean isUsedCity(String city) {
        return usedCities.contains(city);
    }

    public boolean isValidCity(String city) {
        if (cities.contains(city)) {
        if (usedCities.contains(city)) {
            usedCities.add(city);
        } else if (!lastCity.isEmpty()) {
            char firstLetter = Character.toLowerCase(city.charAt(0));
            char lastLetter = Character.toLowerCase(lastCity.charAt(lastCity.length() - 1));
            return firstLetter == lastLetter;
        } else return cities.contains(city);
        return true;
        } return false;
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
        return !GameManager.isPlayerTurn();
    }

    public String getLastCity() {
        return lastCity;
    }

    public void removeCity(String city) {
        usedCities.add(city);
        cities.remove(city);
        lastCity = city;
    }

    public Set<String> getCities() {
        return cities;
    }

    public void initializeCities() {
        loadCitiesFromFile();
    }

    public void incrementUserCityCount() {
        userCityCount++;
    }

    public void incrementComputerCityCount() {
        computerCityCount++;
    }

    public String getCityCountRatio() {
        return userCityCount + ":" + computerCityCount;
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