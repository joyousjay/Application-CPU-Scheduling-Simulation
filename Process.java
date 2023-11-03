import java.lang.Thread;

public class Process {
    String processId;
    //time process is takes to complete execution
    long burstTime;
    //time process arrives to queue
    long arrivalTime;
    //time process finishes execution
    long completionTime;
    //int timeRun;

    Process(String processId, int arrivalTime, int burstTime /*,int completionTime*/) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        //this.completionTime = completionTime;
    }

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

    public void runProcess() {
        try {
            Thread.sleep(burstTime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //of every process
    public double getTurnAroundTime(){
        return this.getCompletionTime() - this.getArrivalTime();
    //completion - arrival time
    }

    //of every process
    public double getWaitingTime(){
        return this.getTurnAroundTime() - this.getBurstTime();
    //turnaround time - burst time
    }
}

    // //result of every process
    // int waitingTime;
    // int turnaroundtime;
    // //results from all processes in one scheduling algorithm
    // int averageofWaitingTimes;
    // int averageofTurnaroundTimes;
    // int throughput;
    // //final results of all processes' data from every scheduling algorithm
    // int overallWaitingTime;
    // int overallTurnaroundTime;
    // int overallThroughput;
 