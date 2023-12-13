import java.util.ArrayList;

public abstract class ProcessSimulation extends Thread {

   abstract public void addProcess(Process process);
   abstract void simulate();

   ArrayList<Process> processList = new ArrayList<>();
   ArrayList<Process> finishedRunProcessList = new ArrayList<>();
   private Application application;
   private ProcessSet processSet;
   private String scheduler;

   // invokes simulate method in schedulers 
   public void run() {
      System.out.println("loading");
      simulate();
      System.out.println("finished");
   }

   //sets the application details for an applicarion
   // public void setApplication(Application application){
   //    this.application = application;
   // }

   //gets application
   public Application getApplication(){
      return application;
   }

   //set the process set information
   // public void setProcessSets(ProcessSet processSet){
   //    this.processSet = processSet;
   // }

   //get process set
   public ProcessSet getProcessSet(){
      return processSet;
   }

   public void setData(ProcessSet processSet, Application application){
      this.application = application;
      this.processSet = processSet;
      loadProcessDetails();
   }

   //sets the names of the cpu schedulers 
   public void setScheduler(String scheduler){
      this.scheduler = scheduler;
   }

   //get the cpu scheduler
   public String getScheduler(){
      return this.scheduler;
   }
   
   //results from all processes in one scheduling algorithm
   public double getAverageWaitingTime() {
      double sumOfWaitingTimes = 0;
      for (int i = 0; i < finishedRunProcessList.size(); i++){
         sumOfWaitingTimes += finishedRunProcessList.get(i).getWaitingTime();
      }
      System.out.print("Average waiting time: " + sumOfWaitingTimes/finishedRunProcessList.size()); 
      return (sumOfWaitingTimes/finishedRunProcessList.size()); 
   }

   //results from all processes in one scheduling algorithm
   public double getAverageTurnAroundTime() {
      double sumOfTurnAroundTimes = 0;
      for (int i = 0; i < finishedRunProcessList.size(); i++){
         sumOfTurnAroundTimes += finishedRunProcessList.get(i).getTurnAroundTime(); 
      }
      System.out.println("Average turnaround time: " + sumOfTurnAroundTimes/finishedRunProcessList.size());
      return (sumOfTurnAroundTimes/finishedRunProcessList.size());
   }

   //results from all processes in one scheduling algorithm
   public double getThroughput() {
      // number of processes divided by total turnaround time 
      double sumOfCompletionTimes = 0;
      for (int i = 0; i < finishedRunProcessList.size(); i++){
         sumOfCompletionTimes += (finishedRunProcessList.get(i).getBurstTime());
      }  
      System.out.println("Average throughput " + finishedRunProcessList.size()/sumOfCompletionTimes);
      return (finishedRunProcessList.size()/sumOfCompletionTimes);
   }

   /* final results of all processes' data from every scheduling algorithm 
    * retrieves the overall turnaround time, overall waiting time, and overall throughput
   */
   static public double calculateOverallTurnAroundTime(ProcessSimulation... processSchedulers) {
      double sumOfSchedulersTurnAroundTimes = 0.0;
      for (ProcessSimulation processScheduler : processSchedulers) {
          sumOfSchedulersTurnAroundTimes += processScheduler.getAverageTurnAroundTime();
      }
      return (sumOfSchedulersTurnAroundTimes / processSchedulers.length);
   }

   static public double calculateOverallWaitingTime(ProcessSimulation... processSchedulers) {
      double sumOfSchedulersWaitingTime = 0.0;
      for (ProcessSimulation processScheduler : processSchedulers) {
          sumOfSchedulersWaitingTime += processScheduler.getAverageWaitingTime();
      }
      return (sumOfSchedulersWaitingTime / processSchedulers.length);
   }

  static public double calculateOverallThroughput(ProcessSimulation... processSchedulers) {
      double sumOfSchedulersThroughput = 0.0;
      for (ProcessSimulation processScheduler : processSchedulers) {
          sumOfSchedulersThroughput += processScheduler.getThroughput();
      }
      return (sumOfSchedulersThroughput / processSchedulers.length);
   }
   //set the processes' names and processes' burst times for an application's process set size 
   public void loadProcessDetails() {
		for (int i = 0; i < processSet.getSize(); i++) {
			addProcess(new Process(processSet.getProcesses()[i], application.getBurstTimes()[i]));
		}	
	}
}