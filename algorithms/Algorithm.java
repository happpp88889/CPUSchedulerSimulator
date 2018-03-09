package algorithms;

import misc.Process;

import java.util.ArrayList;
import java.util.List;

public abstract class Algorithm {
    protected ArrayList<Process> data;

    public Algorithm(ArrayList<Process> data) {
        this.data = data;
    }


    abstract public void computeStep(Process p);

    /**
     * pick next process depending on the CPU scheduling algorithm currently used
     * @param buffer
     * @return
     */
    abstract public Process nextProcess(List<Process> buffer);



}
