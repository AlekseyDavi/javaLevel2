package Lesson7;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    AccuWeatherModel weatherModel = new AccuWeatherModel();
    Archive archive=new Archive();


    public void getWeather(int command, String selectedCity) throws IOException, SQLException {
        switch (command) {
            case (1):
                weatherModel.getWeather(selectedCity, 1);
                break;
            case (2):
                weatherModel.getWeather(selectedCity, 5);
                break;
            case (3):
                archive.outputResultArchive(selectedCity);
                break;
            default:
                System.out.println("Неверное значение даты");
                System.exit(1);
        }

    }
}

