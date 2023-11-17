//First Come First Serve CPU Scheduler Algorithm

public class FCFS extends ProcessSimulation {
	private GUI gui;

	// public FCFS(String[] appNames, int[] appBurstTimes) {
	// 	for (int i = 0; i < processList.size(); i++) {
	// 		addProcess(new Process(appNames[i], appBurstTimes[i]));
	// 	}
	// }

	public FCFS(GUI gui) {
		this.gui = gui;
	}

	public void addProcess(Process process) {
		// set the arrival time
		process.setArrivalTime(System.currentTimeMillis());
		processList.add(process);
		//System.out.println(process);
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
			//System.out.println("FCFS: " + processList.get(i) +" "+ i);
			processList.get(i).runProcess();
			// this.removeProcess(processList.get(i));
			processList.get(i).setCompletionTime(System.currentTimeMillis());
			//System.out.println("is it working?");
		}
		this.removeProcesses();
		//System.out.println("is it pausing?");
		//once execution has finished, callback to the GUI
		//get data asynchronously (since gui time and schedulers time are different)
		gui.displayAverageResults(getApplication(), getScheduler(), getAverageTurnAroundTime(), getAverageWaitingTime(), getThroughput());
		// gui.displayOverallResults(getApplication(), calculateOverallTurnAroundTime(), calculateOverallWaitingTime(), calculateOverallThroughput());
	}
	//set the application's process names and its burst times
	public void setData(String[] appProcessNames, int[] appProcessBurstTimes) {
		for (int i = 0; i < appProcessNames.length; i++) {
			addProcess(new Process(appProcessNames[i], appProcessBurstTimes[i]));
			//System.out.println(processList.get(i) +" "+ i);
		}
	}

}