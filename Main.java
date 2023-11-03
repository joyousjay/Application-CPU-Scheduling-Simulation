public class Main {
    public static void main(String args[]) {
        GUI gui = new GUI();
        gui.create();

        FCFS fcfs = new FCFS();
        SJF sjf = new SJF();
        LJF ljf = new LJF();
        RoundRobin roundRobin = new RoundRobin();

        gui.calculateOverallTurnAroundTime(fcfs, sjf, ljf, roundRobin);
    }
}