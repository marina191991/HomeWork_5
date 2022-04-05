package hw8SQLite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DataBaseRepository {
        //добавляем в таблицу полученные данные
        boolean saveWeatherData(WeatherData weatherData) throws SQLException;

        List<WeatherResponse> getAllSavedData() throws IOException;

}

