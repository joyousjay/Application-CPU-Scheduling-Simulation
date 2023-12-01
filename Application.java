public class Application {
    String[] appProcess;
    int[] appArrivalOrder;
    int[] appBurstTimes;
    String name;

    //class constructor
    Application(String[] appProcess, int[] appArrivalOrder, int[] appBurstTimes){
        this.appProcess = appProcess;
        this.appArrivalOrder = appArrivalOrder;
        this.appBurstTimes = appBurstTimes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String[] getProcesses() {
        return appProcess;
    }

    public int[] getArrivalOrders() {
        return appArrivalOrder;
    }

    public int[] getBurstTimes() {
        return appBurstTimes;
    }



}