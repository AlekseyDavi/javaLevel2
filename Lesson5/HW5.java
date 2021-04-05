package Lesson5;


import java.io.*;
import java.util.ArrayList;

public class HW5 {
    public static void main(String[] args) {

        try (OutputStream out = new FileOutputStream("data.txt")) {
            out.write("dsrthshdsf;fdzsdsdds;svdsdvasrg;ddffd".getBytes());
            out.write(10);
            out.write("12345;12345;12345;12345".getBytes());
            out.write(10);
            out.write("54321;54321;54321;54321".getBytes());
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data.txt"))) {
            String tempString;
            int[][] data = new int[1][2];
            int count = 0;
            tempString = bufferedReader.readLine();
            String[] headers = tempString.split(";");

            while ((tempString = bufferedReader.readLine()) != null) {
                String[] dataString = tempString.split(";");
                for (int i = 0; i < dataString.length; i++) {
                    data[count][i] = Integer.parseInt(dataString[i]);
                }
                count += 1;
            }
            AppData dataArrayList = new AppData(headers, data);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
