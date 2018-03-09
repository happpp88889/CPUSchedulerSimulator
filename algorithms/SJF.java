package algorithms;

import misc.Process;

import java.util.ArrayList;
import java.util.List;

public class SJF extends Algorithm {

    /**
     * This is a shortest-job-first algorithm
     * Associate with each process the length of its next CPU burst.
     * Use these lengths to schedule the process with the shortest time.
     *
     * @param data
     */
    public SJF(ArrayList<Process> data) {
        super(data);
    }


    @Override
    public void computeStep(Process p) {
        p.decrementDuration();
    }

    public Process nextProcess(List<Process> buffer) {
        Process minDuration = buffer.get(0);
        for (Process p: buffer) {
            if (p.getDuration() < minDuration.getDuration()) {
                minDuration = p;
            }
        }
        buffer.remove(minDuration);
        return minDuration;
    }
}
