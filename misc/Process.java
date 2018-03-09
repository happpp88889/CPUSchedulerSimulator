package misc;

import java.lang.invoke.WrongMethodTypeException;

public class Process implements Comparable {
    private int arrivalTime;
    private int duration;
    private int waitingTime;

    public Process(int arrivalTime, int duration) {
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        waitingTime = 0;
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != this.getClass()) {
            throw new WrongMethodTypeException();
        }

        Process p = (Process) o;
        if (duration == p.getDuration()) return 0;
        else if (duration < p.getDuration()) return -1;
        else return 1;

    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public String toString() {
        return arrivalTime + " " + duration;
    }

    public void decrementDuration() {
        duration--;
    }

    public void incrementWaitingTime() {
        waitingTime++;
    }
}
