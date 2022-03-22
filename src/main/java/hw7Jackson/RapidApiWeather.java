package hw7Jackson;
/*  1. Реализовать корректный вывод информации о текущей погоде. Создать класс
WeatherResponse и десериализовать ответ сервера. Выводить пользователю только текстовое
описание погоды и температуру в градусах Цельсия.
    2. Реализовать корректный выход из программы
    3. Реализовать вывод погоды на следующие 5 дней в формате
В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE
где CITY, DATE, WEATHER_TEXT и TEMPERATURE - уникальные значения для каждого дня.
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;


public class RapidApiWeather  {
    static String cityVal;
    public static String getCityVal () {
        return cityVal;
    }
    public static void setCityVal (String cityVal){
        RapidApiWeather.cityVal = cityVal;
    }
    static Properties prop = new Properties();
    public void makeWeather () throws IOException {
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
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
              for (int i = 0; i < weatherResponse.getCnt(); i++) {
                System.out.println("В городе " + weatherResponse.getCity().getName() + " на дату " +simpleDateFormat.format( calendar.getTime())+" ожидается температура " + dayT.get(i) );
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            System.out.println();
        }


    private static void loadProp() throws IOException {
        try
                (FileInputStream fileInputStream = new FileInputStream("src/main/java/hw7Jackson/weather.properties")) {
            prop.load(fileInputStream);

        }
    }
}
