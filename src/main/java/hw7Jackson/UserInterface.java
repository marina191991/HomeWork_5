package hw7Jackson;



import java.io.IOException;
import java.util.Scanner;

public class UserInterface {



    //private final Controller controller = new Controller();


    public void runApplication() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String k;

        while (true){
            System.out.println("Введите название города латинскими буквами.");
            k= scanner.nextLine();
            RapidApiWeather.setCityVal(k);
            RapidApiWeather rapidApiWeather=new RapidApiWeather();
            rapidApiWeather.makeWeather();
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
