package hw8SQLite;


import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class RapidApiWeather {

    private static String cityVal;

    public static String getCityVal() {
        return cityVal;
    }

    public static void setCityVal(String cityVal) {
        RapidApiWeather.cityVal = cityVal;
    }

    static Properties prop = new Properties();
    static List<WeatherData> weatherDataList = new ArrayList<>();
    DataBaseRepositorySQLite dataBaseRepositorySQLite = new DataBaseRepositorySQLite();

    public void makeWeather() throws IOException, SQLException {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        loadProp();
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("community-open-weather-map.p.rapidapi.com")
                .addPathSegment(prop.getProperty("FORECAST"))
                .addPathSegment(prop.getProperty("DAILY"))
                .addEncodedQueryParameter("q", RapidApiWeather.getCityVal() + "," + "ru")
                .addQueryParameter("cnt", String.valueOf(5))
                .addQueryParameter("units", "metric")
                .build();


        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "67a544ab7dmsh44d165b295529dcp13ef5djsn6f5d12e8d0e1")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Невозможно прочесть информацию о городе. " +
                "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        String jsonResponse = response.body().string();
        StringReader reader = new StringReader(jsonResponse);

        WeatherResponse weatherResponse = objectMapper.readValue(reader, WeatherResponse.class);

        List<Float> dayT = new ArrayList<>();
        weatherResponse.getList().forEach(listTemp -> {
            float max = listTemp.getTemp().getDay();
            dayT.add(max);
        });
       /* for (int i = 0; i < weatherResponse.getCnt(); i++) {
            System.out.println("В городе " + weatherResponse.getCity().getName() + " на дату " + simpleDateFormat.format(calendar.getTime()) + " ожидается температура " + dayT.get(i));
           //добавляем в лист объекты типа WeatherData
            weatherDataList.add(new WeatherData(weatherResponse.getCity().getName(), simpleDateFormat.format(calendar.getTime()), dayT.get(i)));
            //след. день
            calendar.add(Calendar.DAY_OF_YEAR, 1);*/
        for (int i = 0; i < weatherResponse.getCnt(); i++) {
            //System.out.println("В городе " + weatherResponse.getCity().getName() + " на дату " + simpleDateFormat.format(calendar.getTime()) + " ожидается температура " + dayT.get(i));
            //добавляем в лист объекты типа WeatherData
            weatherDataList.add(new WeatherData(weatherResponse.getCity().getName(), simpleDateFormat.format(calendar.getTime()), dayT.get(i)));
            //след. день
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        //insertWeatherData();
        dataBaseRepositorySQLite.connectDr();
        dataBaseRepositorySQLite.performDropTable();
        dataBaseRepositorySQLite.createTableIfNotExists();
        dataBaseRepositorySQLite.saveWDAtaFromList();

        dataBaseRepositorySQLite.readSaveWeatherData();

        System.out.println();
    }

    //прочитать объекты, которые сохранили в лист
    static void insertWeatherData() {
        for (WeatherData weather : weatherDataList) {
            System.out.println(weather.getCity() + " " + weather.getLocalDate() + " " + weather.getTemperature());
        }

    }

    private static void loadProp() throws IOException {
        try
                (FileInputStream fileInputStream = new FileInputStream("src/main/java/hw7Jackson/weather.properties")) {
            prop.load(fileInputStream);

        }
    }
}
