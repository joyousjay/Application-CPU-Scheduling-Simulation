import java.util.*;

public class SJF extends ProcessSimulation {

	ArrayList<Process> processList = new ArrayList<>();
	ArrayList<Process> finishedProcessList = new ArrayList<>();

	public SJF(String[] appNames, int[] appBurstTimes) {
		for (int i = 0; i < processList.size(); i++) {
			addProcess(new Process(appNames[i], appBurstTimes[i]));
		}
	}

	public void addProcess(Process process) {
		// set arrivalTime
		process.setArrivalTime(System.currentTimeMillis());
		processList.add(process);
	}

	public void removeProcess(Process process) {
		process.setCompletionTime(System.currentTimeMillis());
		processList.remove(process);
		finishedRunProcessList.add(process);
	}

	public void simulate() {
		// sort by burst time (ascending or descending ?)
		processList.sort(Comparator.comparing(process -> process.burstTime));
		for (int i = 0; i < processList.size(); i++) {
			// processList.get(i).getBurstTime()
			processList.get(i).runProcess();
			processList.remove(i);
		}
	}
}