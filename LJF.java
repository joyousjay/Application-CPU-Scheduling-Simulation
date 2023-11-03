import java.util.*;

public class LJF extends ProcessSimulation{
     
    ArrayList<Process> processList = new ArrayList<>();
    ArrayList<Process> finishedProcessList = new ArrayList<>();

    public void addProcess(Process process){
      //set arrivalTime 
      
      // set the arrival time idea (?)
      for (int i = 0; i < processList.size(); i++) {
        processList.get(i).setArrivalTime(i);
      }
      processList.add(process);
    }

    public void removeProcess(Process process){
      processList.remove(process);
    }

    public void simulate(){
      //Collections.sort(processList, Comparator.comparing(Process::getArrivalTime));
      int timer = 0;
       //reverse order of burst time (longest to shortest)
      Collections.reverseOrder(Comparator.comparing(Process::getBurstTime));
        
      } 

}