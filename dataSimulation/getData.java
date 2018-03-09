package dataSimulation;


import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import misc.Process;

public class getData {

    private ArrayList<Process> data;
    private double MAX_EXECUTE_TIME=10;
    private double MAX_TIME = 1;

    public getData() {
        data = new ArrayList<Process>();
    }

    /** Generates random data for CPU scheduling task
     * INPUT: number of processes
     */
    public void createData(int n) {

        Random generator = new Random();
        int executeTime;
        int arrivalTime;
        Process proces;
        double delta = MAX_TIME/(double)n;

        for (int i=0; i<n; i++) {

            arrivalTime = (int)(i*delta + generator.nextDouble() * delta);
            executeTime = (int)generator.nextDouble()*10;

            proces = new Process(arrivalTime, executeTime);

            data.add(proces);
        }
    }

    /** Save data to file
     * INPUT: filename
     * @param name
     */
    public void saveData(String name) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(name))) {

            String input;

            for (Process i: data) {
                input = i.getArrivalTime() + " " + i.getDuration() + "\n";
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
            Process proces;
            int arrivalTime;
            int duration;

            while ((input = br.readLine()) != null) {
                cols = input.split(" ");
                arrivalTime = Integer.parseInt(cols[0]);
                duration = Integer.parseInt(cols[1]);
                proces = new Process(arrivalTime, duration);
                data.add(proces);
            }

        } catch (IOException ex) {
            System.err.println("Nie udało się zapisać");
        }
    }

    public ArrayList<Process> getData() {
        return data;
    }
}
