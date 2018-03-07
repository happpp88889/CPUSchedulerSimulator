package algorithms;

import java.util.ArrayList;

public class FIFO implements Algorithm {
    private ArrayList<Double[]> data;
    private double avarageTime;
    private boolean arrivalAtOnce = true;

    public FIFO(ArrayList<Double[]> data) {
        this.data = data;
    }

    @Override
    public void compute() {

        double serviceTime=0;
        double waitTime=0;
        for (int i=0; i<data.size(); i++) {

            Double row[] = data.get(i);
            if (!arrivalAtOnce) {
                serviceTime += waitTime - row[0];
                waitTime += row[1];
            } else {
                serviceTime += waitTime;
                waitTime += row[1];
            }

        }
        avarageTime = serviceTime/data.size();
    }

    @Override
    public double getAvarageTime() {
        return avarageTime;
    }
}
