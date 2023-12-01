//Longest Job First CPU Scheduler Algorithm
import java.util.*;

public class LJF extends ProcessSimulation{
    GUI gui;

    public LJF(GUI gui) {
       this.gui = gui;
    }

    public void removeProcesses() {
    /* loop through process list, 
		 * remove every process that has ran and add it to new list
		 */
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

    public void simulate(){
       //reverse order of burst time (longest to shortest)
      Collections.sort(processList, Collections.reverseOrder(Comparator.comparing(Process::getBurstTime)));
      for (int i = 0; i < processList.size(); i++) {
        System.out.println("ljf " + processList.get(i) +" "+ i);
        processList.get(i).runProcess();
        processList.get(i).setCompletionTime(System.currentTimeMillis());
      }
      this.removeProcesses();
      //once execution has finished, callback to the GUI
		  //get data asynchronously (since gui time and schedulers time are different)
      gui.displayAverageResults(getApplication().getName(), getScheduler(), getAverageTurnAroundTime(), getAverageWaitingTime(), getThroughput());
        
    }
}