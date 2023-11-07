import java.util.ArrayList;

public abstract class ProcessSimulation extends Thread {
  
   abstract public void addProcess(Process process);
   //abstract public void removeProcesses();
   abstract void simulate();

   ArrayList<Process> processList = new ArrayList<>();
   ArrayList<Process> finishedRunProcessList = new ArrayList<>();

   public void run() {
      simulate();
   }

   //results from all processes in one scheduling algorithm
   public double getAverageWaitingTime() {
      double sumOfWaitingTimes = 0;
      for (int i = 0; i < finishedRunProcessList.size(); i++){
         sumOfWaitingTimes += finishedRunProcessList.get(i).getWaitingTime();
      }
      System.out.print("Average waiting time: " + sumOfWaitingTimes/finishedRunProcessList.size()); 
      // System.out.println(sumOfWaitingTimes/finishedRunProcessList.size());
      return (sumOfWaitingTimes/finishedRunProcessList.size()); 
   }

   //results from all processes in one scheduling algorithm
   public double getAverageTurnAroundTime() {
      double sumOfTurnAroundTimes = 0;
      for (int i = 0; i < finishedRunProcessList.size(); i++){
         sumOfTurnAroundTimes += finishedRunProcessList.get(i).getTurnAroundTime(); 
      }
      System.out.println("Average turnaround time: " + sumOfTurnAroundTimes/finishedRunProcessList.size());
      return (sumOfTurnAroundTimes/finishedRunProcessList.size());
   }

   //results from all processes in one scheduling algorithm
   public double getThroughput() {
      // number of processes divided by total turnaround time 
      double sumOfCompletionTimes = 0;
      for (int i = 0; i < finishedRunProcessList.size(); i++){
         sumOfCompletionTimes += (finishedRunProcessList.get(i).getBurstTime());
        // sumOfCompletionTimes += finishedRunProcessList.get(i).getTurnAroundTime(); 
      }  
      System.out.println("Average throughput " + finishedRunProcessList.size()/sumOfCompletionTimes);
      return (finishedRunProcessList.size()/sumOfCompletionTimes);
   }
}