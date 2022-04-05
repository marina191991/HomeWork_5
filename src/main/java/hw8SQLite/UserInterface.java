package hw8SQLite;



import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {




static Integer check() {
    DataBaseRepositorySQLite dataBaseRepositorySQLite=new DataBaseRepositorySQLite();
    Scanner scanner = new Scanner(System.in);
    
    Integer n=null ;
    boolean loop;

    do {
        System.out.println("Введите дату в пределах пяти дней , включая текущую. " +
                "В формате чисел от 1 до 5, где 1-дата текущего дня, 5-дата последнего из пяти дней.");
do {

    try {
        n = scanner.nextInt();

        loop=true;
    } catch (InputMismatchException e) {
        System.out.println("Неккоректный ввод: введенное значение не является числом.Введите числовое значение");
        loop = false;
scanner.next();
           }
} while (loop==false);
          dataBaseRepositorySQLite.checkNumberDay(n);


    }

       while (n < 1 || n > 5);




    return DataBaseRepositorySQLite.setNumberDay(n) ;
}

    public void runApplication() throws IOException {
        Scanner scanner = new Scanner(System.in);
        //DataBaseRepositorySQLite dataBaseRepositorySQLite=new DataBaseRepositorySQLite();
        String k;
        //Integer n;



        while (true){
            System.out.println("Введите название города латинскими буквами.");
            k= scanner.nextLine();
            check();
             RapidApiWeather.setCityVal(k);
            RapidApiWeather rapidApiWeather=new RapidApiWeather();
            try {
                rapidApiWeather.makeWeather();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Для завершения введите 'N' ");
            String result = scanner.nextLine();
            checkIsExit(result);

        }
    }



    private void checkIsExit(String result) {
        if (result.equals("N") || result.equals("n") ) {
            System.out.println("Завершаю работу");
            System.exit(0);
        }
    }


}
