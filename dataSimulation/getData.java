package dataSimulation;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class getData {

    private ArrayList<Double[]> data;
    private double MAX_EXECUTE_TIME=10;
    private double MAX_TIME = 1;

    public getData() {
        data = new ArrayList<>();
    }

    /** Generates random data for CPU scheduling task
     * INPUT: number of processes
     */
    public void createData(int n) {
        Random generator = new Random();

        double executeTime, arrivalTime;
        Double row[];
        double delta = MAX_TIME/(double)n;
        for (int i=0; i<n; i++) {

            arrivalTime = i*delta + generator.nextDouble() * delta;
            executeTime = generator.nextDouble()*10;

            row = new Double[2];
            row[0] = arrivalTime;
            row[1] = executeTime;

            data.add(row);
        }
    }

    /** Save data to file
     * INPUT: filename
     * @param name
     */
    public void saveData(String name) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(name))) {
            String input;
            for (Double[] i: data) {
                input = i[0] + " " + i[1] + "\n";
                bw.write(input);
            }

        } catch (IOException ex) {
            System.err.println("Nie udało się zapisać");
        }
    }

    public void loadData(String name) {
        try (BufferedReader br = new BufferedReader(new FileReader(name))) {

            String input;
            String cols[];
            Double row[];
            while ((input = br.readLine()) != null) {
                cols = input.split(" ");
                row = new Double[2];
                row[0] = Double.parseDouble(cols[0]);
                row[1] = Double.parseDouble(cols[1]);

                data.add(row);
            }

        } catch (IOException ex) {
            System.err.println("Nie udało się zapisać");
        }
    }

    public ArrayList<Double[]> getData() {
        return data;
    }
}
