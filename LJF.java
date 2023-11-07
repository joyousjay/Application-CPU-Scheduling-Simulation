import java.util.*;

public class LJF extends ProcessSimulation{
     
    ArrayList<Process> processList = new ArrayList<>();
    ArrayList<Process> finishedProcessList = new ArrayList<>();

    public LJF(String[] appNames, int[] appBurstTimes) {
        for (int i = 0; i < processList.size(); i++){
            addProcess(new Process(appNames[i], appBurstTimes[i]));
        }
    }
    // public void removeProcesses() {
    //   for (int i = 0; i < processList.size(); i++) {
    //     Process process = processList.remove(i);
    //     finishedRunProcessList.add(process);
    //   }
    // }
    public void addProcess(Process process){
      //set arrivalTime 
      process.setArrivalTime(System.currentTimeMillis());
      processList.add(process);
    }

    public void removeProcesses(Process process){
      processList.remove(process);
    }

    public void simulate(){
      //Collections.sort(processList, Comparator.comparing(Process::getArrivalTime));
       //reverse order of burst time (longest to shortest)
      Collections.reverseOrder(Comparator.comparing(Process::getBurstTime));

      for (int i = 0; i < processList.size(); i++) {

      }
        
      } 

}