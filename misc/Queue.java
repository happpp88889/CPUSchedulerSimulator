package misc;

import algorithms.Algorithm;
import algorithms.FCFS;
import algorithms.SJF;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Queue {
    private ArrayList<Process> data;
    private List<Process>endedProcessess;
    private int clock = 0;
    private int size;

    public Queue(ArrayList<Process> data) {
        this.data = data;
        size = data.size();
        endedProcessess = new LinkedList<>();
    }

    private Algorithm chooseAlgo(String algName, ArrayList<Process> _data) {
        Algorithm algo = null;
        if (algName.equals("FCFS")) {
            algo = new FCFS(_data);
        } else if (algName.equals("SJF")) {
            algo = new SJF(_data);
        }
        if (algo == null) {
            System.err.println("Niepoprawna nazwa algorytmu");
        }
        return algo;
    }
    public void run(String algName, boolean preemtive) {

        ArrayList<Process> _data = makeDeepCopy(data);
        Algorithm algo = chooseAlgo(algName, _data);

        Process nextProcess = null;
        Process process = null;
        List<Process> buffer = new LinkedList<>();
        boolean fetchNewProcess = true;
        int i = 0;
        while (clock < 32) {

            if (fetchNewProcess) {
//                nextProcess = (data.size() != 0) ? data.get(0) : null;
                nextProcess = (i < _data.size()) ? _data.get(i++) : null;
                fetchNewProcess = false;
            }

            // if time equals process's arrival time add it to buffer
            if (nextProcess != null && clock == nextProcess.getArrivalTime()) {
                buffer.add(nextProcess);
                nextProcess = null;
                fetchNewProcess = true;
            }


            // chosing process from buffer depends on the algorithm
            if (process == null && buffer.size() != 0) {
                process = algo.nextProcess(buffer);
            }

            if (process != null) {
                // if algorithm is preemptive search if shorter process in buffer
                if (preemtive) {
                    buffer.add(0, process);
                    process = algo.nextProcess(buffer);
                }
                algo.computeStep(process);
                if (process.getDuration() == 0) {
                    endedProcessess.add(0, process);
                    process = null;
                }
            }

            for (Process p : buffer) {
                p.incrementWaitingTime();
            }
            clock++;
        }
    }

    public double getResult() {
        int sum = 0;
        for (Process p: endedProcessess) {
            sum += p.getWaitingTime();
        }
        return (double) sum / size;
    }

    private ArrayList<Process> makeDeepCopy(ArrayList<Process> data) {
        ArrayList<Process> dataCopy = new ArrayList<>();

        for (Process p: data) {
            dataCopy.add(new Process(p.getArrivalTime(), p.getDuration()));
        }

        return dataCopy;
    }
}
