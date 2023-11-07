import java.util.*;

public class RoundRobin extends ProcessSimulation {

	ArrayList<Process> processList = new ArrayList<>();
	ArrayList<Process> finishedProcessList = new ArrayList<>();

	public RoundRobin(String[] appNames, int[] appBurstTimes) {
		for (int i = 0; i < processList.size(); i++) {
			addProcess(new Process(appNames[i], appBurstTimes[i]));
		}
	}

	public void addProcess(Process process) {
		// set arrivalTime
		process.setArrivalTime(System.currentTimeMillis());
		processList.add(process);
	}
	// public void removeProcesses() {
	// 	for (int i = 0; i < processList.size(); i++) {
	// 		Process process = processList.remove(i);
	// 		finishedRunProcessList.add(process);
	// 	}
	// }
	public void removeProcess(Process process) {
		processList.remove(process);
	}

	// non-preemptive
	public void simulate() {
		double timeQuantum = 2.0;
		// double processRemainingBurstTime = processList;

		for (int i = 0; i < processList.size(); i++) {

			if (processList.get(i).getBurstTime() > timeQuantum) {
				// processRemainingBurstTime = processList.get(i).getBurstTime();
				// processRemainingBurstTime -= timeQuantum;
			} else if (processList.get(i).getBurstTime() < timeQuantum) {

			}

		}
	}
}