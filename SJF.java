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
		/* loop through process list, 
		 * remove every process that has ran and add it to new list
		 */
		for (int i = 0; i < processList.size(); i++) {
			Process process = processList.remove(i);
			finishedRunProcessList.add(process);
		}
	}

	public void simulate() {
		// sort by ascending (smallest) burst time 
		processList.sort(Comparator.comparing(Process::getBurstTime));
		for (int i = 0; i < processList.size(); i++) {
			System.out.println("sjf: " + processList.get(i) +" "+ i);
			processList.get(i).runProcess();
			processList.get(i).setCompletionTime(System.currentTimeMillis());
		}
		this.removeProcesses();

		//once execution has finished, callback to the GUI
		//get data asynchronously (since gui time and schedulers time are different)
		gui.displayAverageResults(getApplication().getName(), getScheduler(), getAverageTurnAroundTime(), getAverageWaitingTime(), getThroughput());
	}
	//set the application's process names and its burst times
	// public void setData() {
	// 	Application app = getApplication();
	// 	for (int i = 0; i < app.getProcesses().length; i++) {
	// 		addProcess(new Process(app.getProcesses()[i], app.getBurstTimes()[i]));
	// 		//System.out.println("FCFS " + processList.get(i) +" "+ i);
	// 	}	
	// }
}