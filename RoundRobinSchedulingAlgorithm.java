/** RoundRobinSchedulingAlgorithm.java
 * 
 * A scheduling algorithm that randomly picks the next job to go.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class RoundRobinSchedulingAlgorithm extends BaseSchedulingAlgorithm {

    private Vector<Process> jobs;
    /** the time slice each process gets */
    private int quantum;
    private int index;
    int quantumCounter;


    RoundRobinSchedulingAlgorithm() {
        activeJob = null;
        quantum = 10;
        quantumCounter = 0;
        index = 0;
        jobs = new Vector<Process>();

    }

    /** Add the new job to the correct queue. */
    public void addJob(Process p) {

        jobs.add(p);

    }

    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p) {
        int processIndex = jobs.indexOf( p );
        if ( jobs.remove( p ) )
        {
            if ( index >= processIndex ) {
                index--;
                return true;
            }
        }
        return false;
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the value of quantum.
     * 
     * @return Value of quantum.
     */
    public int getQuantum() {
        return quantum;
    }

    /**
     * Set the value of quantum.
     * 
     * @param v
     *            Value to assign to quantum.
     */
    public void setQuantum(int v) {
        this.quantum = v;
    }

    /**
     * Returns the next process that should be run by the CPU, null if none
     * available.
     */
    public Process getNextJob(long currentTime)
    {

        if ( activeJob == null && jobs.size() == 0 )
            return null;
        if ( activeJob == null && jobs.size() != 0 )
        {
            activeJob = jobs.get( index );
            index++;
            quantumCounter = 0;
        }

        if ( isJobFinished() || quantum == quantumCounter )
        {
            if ( index >= jobs.size( ) )
                index = 0;
            activeJob = jobs.get( index );
            index++;
            quantumCounter = 0;
        }
        quantumCounter++;
        return activeJob;
    }




    public String getName() {
        return "Round Robin";
    }
    
}