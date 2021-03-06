/** FCFSSchedulingAlgorithm.java
 * 
 * A first-come first-served scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class FCFSSchedulingAlgorithm extends BaseSchedulingAlgorithm
{
    private Vector<Process> jobs;

    FCFSSchedulingAlgorithm()
    {
        activeJob = null;
        jobs = new Vector<Process>();
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p)
    {
            jobs.add(p);
    }

    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p)
    {

        if ( jobs.contains(p) )
        {
            jobs.remove(p);
            return true;
        }

        return false;
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg)
    {
        throw new UnsupportedOperationException();
    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime)
    {

        if ( activeJob == null )
        {
            activeJob = jobs.remove(0);
        }

        if ( activeJob.isFinished() )
        {
            activeJob = jobs.remove(0);
        }

        return activeJob;

    }


    public String getName(){
        return "First-Come First-Served";
    }
    
}