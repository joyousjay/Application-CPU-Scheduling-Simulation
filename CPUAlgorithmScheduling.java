import java.util.*;

public class CPUAlgorithmScheduling extends ProcessSimulation{
     
    ArrayList<Process> processList = new ArrayList<>();

    public double getThroughput(){
          return 0.0;
    }

    public double getTurnAroundTime(){
          return 0.0;
    }

    public double getWaitingTime(){
        return 0.0;
    }

    public double getAverageWaitingTime(){
          return 0.0;
    }

    public void addProcess(Process process){
      processList.add(process);
    }

    public void removeProcess(Process process){
      processList.remove(process);
    }

    public void simulateFCFS(){
        processList.sort();
    }

    public void simulateSJF(){

    }

    public void simulateLJF(){

    }

    public void simulateRoundRobin(){
      int timer = 0;
      int timeQuantum = 2;
      
      for (int i = 0; i < processList.size(); i++) {

        //processList.get(i).burstTime;
     
      }
    }



}