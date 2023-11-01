public class Process {
    String processId;
    //time process is given for execution
    int burstTime;
    //time process arrives to queue
    int arrivalTime;
    //time process finishes execution
    //int completionTime;
    int timeRun;

    Process(String processId, int arrivalTime, int burstTime /*,int completionTime*/) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        //this.completionTime = completionTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public String getProcessId() {
        return processId;
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
 