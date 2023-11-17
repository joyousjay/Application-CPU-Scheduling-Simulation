//Shortest Job First CPU Scheduler Algorithm
import java.util.*;

public class SJF extends ProcessSimulation {
	private GUI gui;

	public SJF(GUI gui) {
		this.gui = gui;
	}

	public void addProcess(Process process) {
		// set arrivalTime
		process.setArrivalTime(System.currentTimeMillis());
		processList.add(process);
	}

	public void removeProcesses() {
		for (int i = 0; i < processList.size(); i++) {
			Process process = processList.remove(i);
			finishedRunProcessList.add(process);
		}
	}

	public void simulate() {
		// sort by ascending (smallest) burst time 
		processList.sort(Comparator.comparing(Process::getBurstTime));
		for (int i = 0; i < processList.size(); i++) {
			//System.out.println(processList.get(i) +" "+ i);
			processList.get(i).runProcess();
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
		}
	}
}