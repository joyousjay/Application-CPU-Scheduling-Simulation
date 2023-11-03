import java.util.*;

public class SJF extends ProcessSimulation{
     
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
       //sort by burst time
      processList.sort(Comparator.comparing(process -> process.burstTime));



    }
}