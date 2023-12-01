//Round Robin CPU Scheduler Algorithm

public class RoundRobin extends ProcessSimulation {
	GUI gui;

	public RoundRobin(GUI gui) {
		this.gui = gui;
	}

	public void addProcess(Process process) {
		// set arrivalTime
		process.setArrivalTime(System.currentTimeMillis());
		processList.add(process);
	}

	public void removeProcesses() {
		/* loop through process list, 
		 * remove every process that has ran
		 */
		for (int i = 0; i < processList.size(); i++) {
			processList.remove(i);
		}
	}

	// non-preemptive
	public void simulate() {
		long timeQuantum = 2;
		finishedRunProcessList.addAll(processList);
		for (int i = 0; i < processList.size(); i++) {
			System.out.println("rr: " + processList.get(i) + " " + processList.get(i).getRemainingTime());
			if (processList.get(i).getRemainingTime() > timeQuantum) {
				processList.get(i).setRemainingTime(processList.get(i).getRemainingTime() - timeQuantum);
				processList.get(i).runProcess(timeQuantum);
				//since process doesn't finish simulating, add it to end of list
				processList.add(processList.get(i));
			} 
			else if (processList.get(i).getRemainingTime() <= timeQuantum) {
				processList.get(i).runProcess();
				processList.get(i).setCompletionTime(System.currentTimeMillis());
			}
		}
		this.removeProcesses();

		//once execution has finished, callback to the GUI 
		//get data asynchronously (since gui time and schedulers time are different)
		gui.displayAverageResults(getApplication().getName(), getScheduler(), getAverageTurnAroundTime(), getAverageWaitingTime(), getThroughput());
	}
}