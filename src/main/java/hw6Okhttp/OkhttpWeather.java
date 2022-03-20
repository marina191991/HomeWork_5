package hw6Okhttp;


import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class OkhttpWeather {

static Properties prop = new Properties();
    public static void main(String[] args) throws IOException {
        loadProp();
        OkHttpClient client = new OkHttpClient();
        //https://rapidapi.com/weatherbit/api/weather/
        //url=https://weatherbit-v1-mashape.p.rapidapi.com/forecast/3hourly?lat=59.939099&lon=30.315877
                HttpUrl url=new HttpUrl.Builder()
                .scheme("https")
                .host("weatherbit-v1-mashape.p.rapidapi.com")
                .addPathSegment(prop.getProperty("FORECAST"))
                .addPathSegment(prop.getProperty("HOURLY"))
                .addEncodedQueryParameter("lat","59.939099")
                .addQueryParameter("lon","30.315877")
                .build();


        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "67a544ab7dmsh44d165b295529dcp13ef5djsn6f5d12e8d0e1")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.code());
        System.out.println(response.body().string());
        System.out.println("Content-Type : " + response.header("Content-Type"));

    }
    private static void loadProp() throws IOException {
        try
                (FileInputStream fileInputStream = new FileInputStream("src/main/java/hw6Okhttp/weather.properties")) {
            prop.load(fileInputStream);

        }
    }
}
