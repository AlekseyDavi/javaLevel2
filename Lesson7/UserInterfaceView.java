package Lesson7;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class UserInterfaceView {
    Controller controller = new Controller();
    String command = "выход";

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите название города");
            String city = scanner.nextLine();
            int chosen;
            do {
                System.out.println("Введите \"1\" для получения текущей погоды, " +
                        "введите \"2\" для получения прогноза погоды на 5 дней," +
                        " введите \"3\" для получения доступа к архиву, для выхода введите \"4\"");
                command = scanner.nextLine();
                chosen = parseInt(command);
            } while (chosen != 1 && chosen != 2 && chosen != 3 && chosen != 4);
            if (chosen == 4) {
                System.out.println("Спасибо");
                System.exit(0);
            }
            try {
                controller.getWeather(chosen, city);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
            continue;
        }
    }
}
