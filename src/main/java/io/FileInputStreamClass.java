package io;




import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileInputStreamClass {
    public static void main(String[] args) {
        File filePath= new File("src/main/word.txt");
        int len= (int) filePath.length();
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream= null;
        InputStreamReader inputStreamReader=null;
        /*
        //Считываем поток в консоль без буферизации(медленный вариант)
        try {
            file = new FileInputStream(filePath);
            int count;
            while ((count = file.read()) != -1) {
                System.out.print((char) count);
            }
         } catch (IOException e) {
            e.printStackTrace();
        }*/
        //Считываем поток в массив байтовый (буферизация)

     //byte[] arrayByte= " Java ".getBytes(StandardCharsets.UTF_8);
        String str=" hello Мир";

        try {
            fileOutputStream= new FileOutputStream(filePath);
           fileOutputStream.write(str.getBytes(StandardCharsets.UTF_8));

            //fileOut.write(arrayByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader= new InputStreamReader(fileInputStream, "UTF-8");
            int count;//считываем сюда байт
            int i=0;
            int [] buff =new int[len];//массив буфер
            while ((count = inputStreamReader.read()) != -1) {
                buff[i] =count;

                    System.out.print((char) buff[i]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }






    }
}
