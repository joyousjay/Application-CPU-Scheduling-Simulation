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
		for (int i = 0; i < processList.size(); i++) {
			Process process = processList.remove(i);
			finishedRunProcessList.add(process);
		}
	}

	public void simulate() {
		for (int i = 0; i < processList.size(); i++) {
			// run the process and when it is done remove from queue
			processList.get(i).runProcess();
			processList.get(i).setCompletionTime(System.currentTimeMillis());
			//System.out.println("is it working?");
		}
		this.removeProcesses();
		
		//once execution has finished, callback to the GUI
		//get data asynchronously (since gui time and schedulers time are different)
		gui.displayAverageResults(getApplication(), getScheduler(), getAverageTurnAroundTime(), getAverageWaitingTime(), getThroughput());
	}
	
	//set the application's process names and its burst times
	public void setData(String[] appProcessNames, int[] appProcessBurstTimes) {
		for (int i = 0; i < appProcessNames.length; i++) {
			addProcess(new Process(appProcessNames[i], appProcessBurstTimes[i]));
			//System.out.println(processList.get(i) +" "+ i);
		}
	}

}