package model;

public class Timer extends Thread {

    /**
     * Time representation. Index 0 for hours, 1 for mins and 2 for secs
     */
    private final int[] time; // hour, min, sec


    /**
     * Sets time
     *
     * @param time Time to be set
     * @pre hours>=0, mins[0,59], sec[0,60]
     * @post Time set to the given time
     */
    public void setTime(int[] time) {
        if (time[0] < 0 || time[1] < 0 || time[1] > 59 || time[2] < 0 || time[2] > 59)
            throw new IllegalArgumentException("Time not valid");
        else {
            this.time[0] = time[0];
            this.time[1] = time[1];
            this.time[2] = time[2];
        }
    }

    /**
     * Gets current time
     *
     * @return Array of current time
     * @post Returns the current time
     */
    public int[] getTime() {
        return this.time;
    }

    /**
     * Gets a formatted string of the current time
     *
     * @return String of the current time
     * @post Returns current time in formatted/readable string
     */
    public String toString() {
        return this.time[0] + "h " + this.time[1] + "m " + this.time[2] + "s";
    }

    /**
     * Creates a timer and resets the time
     * @post A timer with the default time is created
     */
    public Timer() {
        this.time = new int[]{0, 0, 0};
    }

    /**
     * Used by Thread to initiate clock
     */
    public void run() {
        try {
            updateTime();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates time
     *
     * @post Time=PrevTime+1sec
     */
    public void updateTime() throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            int[] currentTime = this.getTime();
            currentTime[2]++;
            currentTime = reformatTime(currentTime);
            this.setTime(currentTime);
        }
    }

    /**
     * Manages time counting units: Converts seconds to minutes and minutes to hours if needed
     *
     * @param time Time to be checked
     * @return Valid time representation
     * @post A valid representation of the given time is returned
     */
    private int[] reformatTime(int[] time) {
        boolean unitPassed = false;
        if (time[2] >= 60) {
            time[2] = 0;
            time[1]++;
            unitPassed = true;
        }
        if (unitPassed && time[1] >= 60) {
            time[1] = 0;
            time[0]++;
        }
        return time;
    }
}
