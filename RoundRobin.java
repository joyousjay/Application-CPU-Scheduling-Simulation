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
		for (int i = 0; i < processList.size(); i++) {
			processList.remove(i);
			//finishedRunProcessList.add(process);
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
		gui.displayAverageResults(getApplication(), getScheduler(), getAverageTurnAroundTime(), getAverageWaitingTime(), getThroughput());
	}

	public void setData(String[] appProcessNames, int[] appProcessBurstTimes) {
		for (int i = 0; i < appProcessNames.length; i++) {
			addProcess(new Process(appProcessNames[i], appProcessBurstTimes[i]));
			//System.out.println("rr: " + processList.get(i) +" "+ i);
		}
	}
}