package hw7Jackson;

import hw8SQLite.DataBaseRepository;

import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {

        UserInterface userInterface = new UserInterface();
        userInterface.runApplication();
    }
}
