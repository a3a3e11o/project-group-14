import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CityManager {

    private final List <String> cities;
    private ArrayList <String> usedCities;
   private String lastCity = "";

   public CityManager(){
       cities = new ArrayList<>();
       usedCities = new ArrayList<>();
       loadCitiesFromFile("cities.txt");
   }

   public boolean isValidCity(String city) {
       char lastLetter = city.charAt(city.length() - 1);
       if (city.isEmpty()) {
           return false;
       } else if (lastCity.charAt(lastCity.length() - 1) != lastLetter) {
           return false;
       }
       if (cities.contains(city)) {
           usedCities.add(city);
           cities.remove(city);
           lastCity = city;
           return true;
       }

       return false;
   }
   public boolean isUsedCity(String city){
       return usedCities.contains(city);
   }
    public boolean containsCyrillicCharacters(String city) {
        for (char c : city.toCharArray()) {
            if (!Character.isLetter(c) || Character.UnicodeBlock.of(c) != Character.UnicodeBlock.CYRILLIC) {
                return false;
            }
        }
        return true;
    }
   public boolean isComputerTurn(){
       return !lastCity.isEmpty() && Character.isUpperCase(lastCity.charAt(0));
    }
    public String getLastCity(){
       return lastCity;
    }
    public void removeCity(String city){
       cities.remove(city);
       lastCity = city;
    }
    public List <String> getCities(){
       return cities;
    }

    private void loadCitiesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cities.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}