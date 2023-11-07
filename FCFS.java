import java.util.ArrayList;

public class FCFS extends ProcessSimulation {
	private GUI gui;

	public FCFS(String[] appNames, int[] appBurstTimes) {
		for (int i = 0; i < processList.size(); i++) {
			addProcess(new Process(appNames[i], appBurstTimes[i]));
		}
	}

	public FCFS(GUI gui) {
		this.gui = gui;
	}

	public void addProcess(Process process) {
		// set the arrival time
		process.setArrivalTime(System.currentTimeMillis());
		processList.add(process);
		System.out.println(process);
	}

	public void removeProcesses() {
		for (int i = 0; i < processList.size(); i++) {
			Process process = processList.remove(i);
			finishedRunProcessList.add(process);
		}
	}

	public void simulate() {
		for (int i = 0; i < processList.size(); i++) {
			// run the process and when it is done remove from queue
			System.out.println(processList.get(i) +" "+ i);
			processList.get(i).runProcess();
			// this.removeProcess(processList.get(i));
			processList.get(i).setCompletionTime(System.currentTimeMillis());
			System.out.println("is it working?");
		}
		this.removeProcesses();
		System.out.println("is it pausing?");
		//once execution has finished, callback to the GUI
		gui.displayResults();
		// getAverageWaitingTime();
		// getAverageTurnAroundTime();
		// getThroughput();
	}

	public void setData(String[] appProcessNames, int[] appProcessBurstTimes) {
		for (int i = 0; i < appProcessNames.length; i++) {
			addProcess(new Process(appProcessNames[i], appProcessBurstTimes[i]));
			System.out.println(processList.get(i) +" "+ i);
		}
	}

}