package hw5IOStream;
/*1. Реализовать сохранение данных в csv файл;
2. Реализовать загрузку данных из csv файла. Файл читается целиком.
Структура csv файла:
Строка заголовок с набором столбцов
Набор строк с целочисленными значениями
* Разделитель между столбцами - символ точка с запятой (;)
Пример:
Value 1;Value 2;Value 3
100;200;123
300,400,500
Для хранения данных использовать класс вида:
public class AppData {
private String[] header;
private int[][] data;
// ...
}
Если выполняется save(AppData data), то старые данные в файле полностью перезаписываются.*/
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IOStreamClass {
static File file= new File("src/main/java/hw5IOStream/resource/Книга1.csv");
    public static String header = "value1" +";"+ "value2"+";"+"Value3"+ System.getProperty("line.separator");
public static List<FileObject> arrayList= new ArrayList<>();
    public static void main(String[] args) throws IOException {

       createDataArrayListInFileObject();
        writerData();
        readData();
    }
    //Заполнение массива числовыми данными
static void createDataArrayListInFileObject() {
        for (int i=100;i<=110;i++) {
            arrayList.add (new FileObject(i,i+100,i+200));

        }


}
//метод записывает данные в файл
    static void writerData() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);) {
            fileOutputStream.write(header.getBytes(StandardCharsets.UTF_8));
            for (FileObject arr: arrayList) {
                String string= arr.getVal1()+";"+arr.getVal2()+";"+arr.getVal3()+System.getProperty("line.separator");
                fileOutputStream.write(string.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
    static void readData() throws IOException {
        AppData appData = new AppData();
        List<String> list1 = new ArrayList();

        try (BufferedReader fileInputStream = new BufferedReader(new FileReader(file))) {
            String count;
            count = fileInputStream.readLine();
            appData.setHeader(count.split(";"));
                      while ((count = fileInputStream.readLine()) != null) {
                           list1.add(count);
            }
              int columns = list1.get(0).split(";").length;
            int[][] doubleArray = new int[arrayList.size()][columns];

            for (int i = 0; i < list1.size(); i++) {
                String[] ar = list1.get(i).split(";");
                    for (int j = 0; j < columns; j++) {
                        doubleArray[i][j] = Integer.parseInt(ar[j]);
                           }
                            }appData.setData(doubleArray);
            System.out.println("appData.getHeader()=" +appData.getHeader());
            System.out.println("appData.getData()="+appData.getData());

        }
                }
}




