package model;

public class Timer extends Thread {

    /**
     * Time representation. Index 0 for hours, 1 for mins and 2 for secs
     */
    private int[] time; // hour, min, sec


    /**
     * Sets time
     * @param time Time to be set
     * @pre hours>=0, mins[0,59], sec[0,60]
     * @post Time set to the given time
     */
    public void setTime(int[] time) {
        if (time[0]<0 || time[1]<0 || time[1]>59 || time[2]<0 || time[2]>59)
            throw new IllegalArgumentException("Time not valid");
        else {
            this.time[0]=time[0];
            this.time[1]=time[1];
            this.time[2]=time[2];
        }
    }

    /**
     * Gets current time
     * @return Array of current time
     * @post Returns the current time
     */
    public int[] getTime() {return this.time;}

    /**
     * Gets a formatted string of the current time
     * @return String of the current time
     * @post Returns current time in formatted/readable string
     */
    public String toString() {return null;};

    /**
     * Creates a timer and resets the time
     */
    public Timer() {
        this.time = new int[]{0, 0, 0};
    }

    /**
     * Used by Thread to initiate clock
     */
    public void run() {}

    /**
     * Updates time
     * @post Time=PrevTime+1sec
     */
    public void updateTime() {}

}
