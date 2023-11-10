import java.lang.Thread;

public class Process {
    String processId;
    //time process is takes to complete execution
    long burstTime;
    //time process arrives to queue
    long arrivalTime;
    //time process finishes execution
    long completionTime;
    
    Process(String processId, int burstTime) {
        this.processId = processId;
        this.burstTime = burstTime;
    }

    //setters and getters
    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public void setBurstTime(long burstTime) {
        this.burstTime = burstTime;
    }
    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getBurstTime() {
        return burstTime;
    }

    public String getProcessId() {
        return processId;
    }

    public void setCompletionTime(long completionTime){
        this.completionTime = completionTime;
    }

    public long getCompletionTime() {
        return completionTime;
    }
    //simulates a process in scheduler
    public void runProcess() {
        //System.out.println("is it failing" + burstTime);
        try {
            //System.out.println("sleep");
            Thread.sleep(burstTime * 1000);
            //System.out.println("awake");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //results of every process
    //formula for turnaround time: completion time  - arrival time
    public double getTurnAroundTime() {
        return (this.getCompletionTime() - this.getArrivalTime())/1000;
   
    }

    //results of every process
    //formula for waiting time: turnaround time - burst time
    public double getWaitingTime() {
        return this.getTurnAroundTime() - this.getBurstTime();
    }

    public String toString() { 
        return processId + " " + burstTime;
    }
}