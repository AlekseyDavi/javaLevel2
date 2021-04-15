package Lesson7;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OutputResult {
    public static void outputResult(String[] minimum, String[] maximum, int numberDay, String town) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        double minimumCelsius;
        double maximumCelsius;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:geekbrains.db");
            statement = connection.createStatement();
            statement.executeQuery("create table if not exists temperatureArchive (id integer primary key autoincrement,town text,data text,temperatureMin text,temperatureMax text);");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println(town);
        System.out.println();
        for (int i = 0; i < numberDay; i++) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Calendar data = Calendar.getInstance();
            data.add(Calendar.DATE, i);
            System.out.println(dateFormat.format(data.getTime()));
            minimumCelsius = (Double.parseDouble(minimum[i]) - 32) / 1.8;
            String minimumResult = String.format("%.1f", minimumCelsius);
            maximumCelsius = (Double.parseDouble(maximum[i]) - 32) / 1.8;
            String maximumResult = String.format("%.1f", maximumCelsius);
            System.out.println("Temperature: " + minimumResult + "\u2103 - " + maximumResult + "\u2103");
            System.out.println();
            try {
                String requestData = "INSERT INTO temperatureArchive ( data,temperatureMin,temperatureMax,town) values ('" + dateFormat.format(data.getTime())
                        + "', '" + minimumCelsius
                        + "', '" + maximumCelsius
                        + "', '" + town + "');";

                statement.executeUpdate(requestData);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
