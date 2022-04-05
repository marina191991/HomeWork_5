package hw8SQLite;

import java.io.IOException;
import java.sql.*;
import java.util.List;


public class DataBaseRepositorySQLite implements DataBaseRepository {
    private static Integer numberDay;

    public static Integer getNumberDay() {
        return numberDay;
    }

    public static Integer setNumberDay(Integer numberDay) {
        DataBaseRepositorySQLite.numberDay = numberDay;
        return numberDay;
    }

    //подключение к драйверу
    void connectDr() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //создание бд
    String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (\n" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "city TEXT NOT NULL,\n" +
            "localDate TEXT NOT NULL,\n" +
            "temperature REAL NOT NULL\n" +
            ");";
    //добавление в бд
    String insertWeatherQuery = "INSERT INTO weather (city, localDate, temperature) VALUES (?,?,?)";


    //подключение к нашей базу данных mydatabase.db
    private Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        return conn;
    }

    //создание таблицы
    void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(createTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //добавляем в таблицу полученные данные
    void saveWDAtaFromList() throws SQLException {
        for (WeatherData wd : RapidApiWeather.weatherDataList) {
            saveWeatherData(wd);
        }
    }

    @Override
    public boolean saveWeatherData(WeatherData weatherData) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            saveWeather.setString(1, weatherData.getCity());
            saveWeather.setString(2, weatherData.getLocalDate());
            saveWeather.setDouble(3, weatherData.getTemperature());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Failure on saving weather object");
    }

    //дропаем таблицу перед новыми записями
    void performDropTable() throws SQLException {
        try (Connection connection = getConnection()) {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS weather");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     /* public void readAllSaveWeatherData()  {
          try (Connection connection =getConnection();
          Statement statement=connection.createStatement()) {
              ResultSet rs= statement.executeQuery("SELECT * FROM weather ");
         while (rs.next()) {

             System.out.println(
                     rs.getInt(1) + " | "+
                             rs.getString(2) +"  |  "+
                             rs.getString(3) +"  |  "+
                             rs.getFloat(4)
             );
         }
          } catch (SQLException e) {
              e.printStackTrace();
          }

      }
      void read() {
        if ((DataBaseRepositorySQLite.getNumberDay()>=1) && (DataBaseRepositorySQLite.getNumberDay()<=5))
            readSaveWeatherData();
        else if (DataBaseRepositorySQLite.getNumberDay()==0) {
            readAllSaveWeatherData();
        }
      }*/
    public void readSaveWeatherData()  {

        try (Connection connection = getConnection()) {

            PreparedStatement prep = connection.prepareStatement("SELECT * FROM weather where id=?");
                        prep.setInt(1, DataBaseRepositorySQLite.getNumberDay());
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {

                System.out.println(
                        rs.getInt(1) + " | " +
                                rs.getString(2) + "  |  " +
                                rs.getString(3) + "  |  " +
                                rs.getFloat(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
        public List<WeatherResponse> getAllSavedData () throws IOException {
            throw new IOException("Not implemented exception");
        }
  void checkNumberDay(Integer numberDay)  {
     if (numberDay>5)  {
         System.out.println("Невозможно получить данные о погоде превышающие 5 дней.");
     }

      else if ((numberDay<1) && !(numberDay==0))  {
          System.out.println("Невозможно получить данные о погоде. Неккоректный ввод дня.");
      }

 }
    }
