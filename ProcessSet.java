// establishes the number of processes executed by a CPU scheduler within an application
public class ProcessSet {
    int size; //stores different process set sizes
    String[] processIds; //stores names of process ids
    int[] processArrivalOrders; //stores arrival order of processes
   
    ProcessSet(int size, String[] processIds, int[] processArrivalOrders) {
        this.size = size;
        this.processIds = processIds;
        this.processArrivalOrders = processArrivalOrders;
    }
    
    //retrieves the number of processes in a process set
    public int getSize() {
        return size;
    }
    
    //retrieves the process id names for every process in a process set
    public String[] getProcesses() {
        return processIds;
    }

    //retrieves the arrival order for every process in a process set
    public int[] getArrivalOrders() {
        return processArrivalOrders;
    }
    

    
}
