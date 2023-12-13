//the details of an application established here
public class Application {
    int[] appBurstTimes; //stores burst times of processes in an application
    String name;

    //class constructor
    Application(int[] appBurstTimes){
        this.appBurstTimes = appBurstTimes;
    }

    //sets the application name
    public void setName(String name) {
        this.name = name;
    }

    //retrieves the name of every application
    public String getName() {
        return name;
    }

    //retrieves the burst times for every process in an application
    public int[] getBurstTimes() {
        return appBurstTimes;
    }



}