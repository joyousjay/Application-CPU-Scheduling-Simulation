public class FCFS extends ProcessSimulation {

	public FCFS(String[] appNames, int[] appBurstTimes) {
		for (int i = 0; i < processList.size(); i++) {
			addProcess(new Process(appNames[i], appBurstTimes[i]));
		}
	}

	public void addProcess(Process process) {
		// set the arrival time
		process.setArrivalTime(System.currentTimeMillis());
		processList.add(process);
	}

	public void removeProcess(Process process) {
		process.setCompletionTime(System.currentTimeMillis());
		processList.remove(process);
		finishedRunProcessList.add(process);
	}

	public void simulate() {
		for (int i = 0; i < processList.size(); i++) {
			// run the process and when it is done remove from queue
			processList.get(i).runProcess();
			processList.remove(i);
		}
	}

}