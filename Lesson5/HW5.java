package Lesson5;


import java.io.*;
import java.util.Scanner;

public class HW5 {
    public static void main(String[] args) {

        try (OutputStream out = new FileOutputStream("data.txt")) {
            out.write("dsrthshdsf;fdzsdsdds;svdsdvasrg;ddffd".getBytes());
            out.write(10);
            out.write("12345;12345;12345;12345".getBytes());
            out.write(10);
            out.write("54321;54321;54321;54321".getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data.txt"))) {
            String tempString;

            int count = 0;
            tempString = bufferedReader.readLine();
            String[] headers = tempString.split(";");
            int[][] data = new int[countTheNumberOfRows("data.txt")][headers.length];
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

    public static int countTheNumberOfRows(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        int lines = 0;

        while (scanner.hasNextLine()) {
            lines++;

        }
        return lines;
    }
}


