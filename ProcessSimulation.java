public abstract class ProcessSimulation {
  
   abstract double getThroughput();
   abstract double getTurnAroundTime();
   abstract double getWaitingTime();
   abstract public void addProcess(Process process);
   abstract public void removeProcess(Process process);
   abstract void simulateFCFS();
   abstract void simulateSJF();
   abstract void simulateLJF();
   abstract void simulateRoundRobin();
}