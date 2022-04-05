package hw8SQLite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private String City;
    private String localDate;
    //private String weatherText;
    private Float temperature;

    public WeatherData() {

    }
    public WeatherData(String city, String localDate, Float temperature) {
        this.City = city;
        this.localDate = localDate;
       // this.weatherText = weatherText;
        this.temperature = temperature;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

   /* public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }*/

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }
}
