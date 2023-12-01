//First Come First Serve CPU Scheduler Algorithm

public class FCFS extends ProcessSimulation {
	private GUI gui;

	public FCFS(GUI gui) {
		this.gui = gui;
	}

	public void addProcess(Process process) {
		// set the arrival time
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
		for (int i = 0; i < processList.size(); i++) {
			// run the process and when it is done remove from queue
			System.out.println("fcfs: " + processList.get(i) +" "+ i);
			processList.get(i).runProcess();
			processList.get(i).setCompletionTime(System.currentTimeMillis());
		}
		this.removeProcesses(); 
		
		//once execution has finished, callback to the GUI
		//get data asynchronously (since gui time and schedulers time are different)
		gui.displayAverageResults(getApplication().getName(), getScheduler(), getAverageTurnAroundTime(), getAverageWaitingTime(), getThroughput());
	}

}