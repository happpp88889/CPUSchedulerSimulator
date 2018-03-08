package algorithms;

import misc.Process;

import java.util.ArrayList;
import java.util.List;

public class FIFO extends Algorithm {

    public FIFO(ArrayList<Process> data) {
        super(data);
    }

    @Override
    public void computeStep(Process p) {
        p.decrementDuration();
    }

    @Override
    public Process nextProcess(List<Process> buffer) {
        Process p = buffer.get(0);
        buffer.remove(p);
        return p;
    }
}
