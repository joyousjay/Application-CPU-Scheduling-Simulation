//Longest Job First CPU Scheduler Algorithm
import java.util.*;

public class LJF extends ProcessSimulation{
    GUI gui;
    // ArrayList<Process> processList = new ArrayList<>();
    // ArrayList<Process> finishedProcessList = new ArrayList<>();

    public LJF(GUI gui) {
       this.gui = gui;
    }

    public void removeProcesses() {
      for (int i = 0; i < processList.size(); i++) {
        Process process = processList.remove(i);
        finishedRunProcessList.add(process);
      }
    }
    public void addProcess(Process process){
      //set arrivalTime 
      process.setArrivalTime(System.currentTimeMillis());
      processList.add(process);
    }

    // public void removeProcesses(Process process){
    //   processList.remove(process);
    // }

    public void simulate(){
       //reverse order of burst time (longest to shortest)
      Collections.sort(processList, Collections.reverseOrder(Comparator.comparing(Process::getBurstTime)));
      for (int i = 0; i < processList.size(); i++) {
        // processList.get(i).getBurstTime()
       // System.out.println("LJF " + processList.get(i) +" "+ i);
        processList.get(i).runProcess();
        // this.removeProcess(processList.get(i));
        processList.get(i).setCompletionTime(System.currentTimeMillis());
      }
      this.removeProcesses();
      //once execution has finished, callback to the GUI
		  //get data asynchronously (since gui time and schedulers time are different)
      gui.displayAverageResults(getApplication(), getScheduler(), getAverageTurnAroundTime(), getAverageWaitingTime(), getThroughput());
        
    }
    
    public void setData(String[] appProcessNames, int[] appProcessBurstTimes) {
      for (int i = 0; i < appProcessNames.length; i++) {
        addProcess(new Process(appProcessNames[i], appProcessBurstTimes[i]));
        //System.out.println("LJF" + processList.get(i) +" "+ i);
      }
    }
}