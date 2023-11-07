import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GUI {
    JTable outlookTable = new JTable();
    JTable youtubeTable = new JTable();
    JTable chromeTable = new JTable();
    JTable resultsTable = new JTable();
    JFrame frame = new JFrame();
    JRadioButton microsoftOutlook, youtube, googleChrome;
    // each row is a panel
    JPanel panel = new JPanel();
    JPanel panelTwo = new JPanel();
    FCFS fcfs = new FCFS(this);
    SJF sjf;
    LJF ljf;
    RoundRobin rr;
    Scanner scan = new Scanner(System.in);

    public GUI() {

    }

    public void create() {
        microsoftOutlook = new JRadioButton("Microsoft Outlook");
        youtube = new JRadioButton("YouTube");
        googleChrome = new JRadioButton("Google Chrome");

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Application CPU Scheduler Simulation", TitledBorder.CENTER, TitledBorder.TOP));
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panelTwo, BorderLayout.CENTER);

        // add button to GUI screen
        panel.add(microsoftOutlook);
        panel.add(youtube);
        panel.add(googleChrome);

        String[] MicrosoftOutlookProcesses = { "P1", "P2", "P3" };
        int[] MicrosoftOutlookProcessesArrivalOrder = { 1, 2, 3 };

        int[] MicrosoftOutlookProcessesBurstTimes = { 1, 2, 3};
        Object[][] outlookData = {
                { MicrosoftOutlookProcesses[0], MicrosoftOutlookProcessesArrivalOrder[0],
                        MicrosoftOutlookProcessesBurstTimes[0] },
                { MicrosoftOutlookProcesses[1], MicrosoftOutlookProcessesArrivalOrder[1],
                        MicrosoftOutlookProcessesBurstTimes[1] },
                { MicrosoftOutlookProcesses[2], MicrosoftOutlookProcessesArrivalOrder[2],
                        MicrosoftOutlookProcessesBurstTimes[2] }
        };
        String[] columnNames = { "Process Name", "Arrival order", "Burst time" };

        String[] YouTubeProcesses = { "P1", "P2", "P3", "P4", "P5", "P6" };
        int[] YouTubeProcessesArrivalOrder = { 1, 2, 3, 4, 5, 6 };
        int[] YouTubeProcessesBurstTimes = { 1, 2, 3, 4, 5, 6 };
        Object[][] YouTubeData = {
                { YouTubeProcesses[0], YouTubeProcessesArrivalOrder[0], YouTubeProcessesBurstTimes[0] },
                { YouTubeProcesses[1], YouTubeProcessesArrivalOrder[1], YouTubeProcessesBurstTimes[1] },
                { YouTubeProcesses[2], YouTubeProcessesArrivalOrder[2], YouTubeProcessesBurstTimes[2] },
                { YouTubeProcesses[3], YouTubeProcessesArrivalOrder[3], YouTubeProcessesBurstTimes[3] },
                { YouTubeProcesses[4], YouTubeProcessesArrivalOrder[4], YouTubeProcessesBurstTimes[4] },
                { YouTubeProcesses[5], YouTubeProcessesArrivalOrder[5], YouTubeProcessesBurstTimes[5] }
        };
        //scan.close();
    
        // to enable only one application chosen at a time
        ButtonGroup applicationButtonGroup = new ButtonGroup();
        applicationButtonGroup.add(microsoftOutlook);
        applicationButtonGroup.add(youtube);
        applicationButtonGroup.add(googleChrome);

        JButton simulate = new JButton("Simulate");
        JButton reset = new JButton("Reset Simulation");
        panel.add(simulate);
        panel.add(reset);

        // clear user selection of application
        //FIX: how to get schedulers to stop running to avoid crash
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationButtonGroup.clearSelection();
                
                if (panelTwo.getComponentCount() > 0) {
                    panelTwo.removeAll();
                }
                panelTwo.revalidate();
                panelTwo.repaint();
            }
        });
        // display table and results of CPU Scheduling algorithm
        // FIX: take in user changing the burst time values
        microsoftOutlook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // table.setRowCount(0);
                if (microsoftOutlook.isSelected()) {
                    if (panelTwo.getComponentCount() > 0) {
                        panelTwo.remove(0);
                    }
                    outlookTable = new JTable(outlookData, columnNames);
                    panelTwo.add(new JScrollPane(outlookTable));
                    outlookTable.setGridColor(Color.gray);
                    panel.setVisible(true);
                    frame.setVisible(true);
                }
            }
        });

        youtube.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (youtube.isSelected()) {
                    if (panelTwo.getComponentCount() > 0) {
                        panelTwo.remove(0);
                    }
                    youtubeTable = new JTable(YouTubeData, columnNames);
                    panelTwo.add(new JScrollPane(youtubeTable));
                    youtubeTable.setGridColor(Color.gray);
                    panel.setVisible(true);
                    frame.setVisible(true);
                }
            }
        });

        simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // need to appear when simulate button is clicked
                if (microsoftOutlook.isSelected()) {
                    fcfs.setData(MicrosoftOutlookProcesses, MicrosoftOutlookProcessesBurstTimes);
                    sjf = new SJF(MicrosoftOutlookProcesses, MicrosoftOutlookProcessesBurstTimes);
                    ljf = new LJF(MicrosoftOutlookProcesses, MicrosoftOutlookProcessesBurstTimes);
                    rr = new RoundRobin(MicrosoftOutlookProcesses, MicrosoftOutlookProcessesBurstTimes);
                    fcfs.start();
                    //sjf.start();
                    System.out.println("hi!");
                    // ljf.simulate();
                    // rr.simulate();
                    // callback to when schedulers are done running needed
                    // get data asynchronously (since gui time and schedulers time are different)
                }
            }
        });
        frame.setSize(1200, 1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /* final results of all processes' data from every scheduling algorithm */
    public double calculateOverallTurnAroundTime(ProcessSimulation... processSchedulers) {
        double sumOfSchedulersTurnAroundTimes = 0.0;
        for (ProcessSimulation processScheduler : processSchedulers) {
            sumOfSchedulersTurnAroundTimes += processScheduler.getAverageTurnAroundTime();
        }
        return (sumOfSchedulersTurnAroundTimes / processSchedulers.length);
    }

    public double calculateOverallThroughput(ProcessSimulation... processSchedulers) {
        double sumOfSchedulersThroughput = 0.0;
        for (ProcessSimulation processScheduler : processSchedulers) {
            sumOfSchedulersThroughput += processScheduler.getThroughput();
        }
        return (sumOfSchedulersThroughput / processSchedulers.length);
    }

    public double calculateOverallWaitingTime(ProcessSimulation... processSchedulers) {
        double sumOfSchedulersWaitingTime = 0.0;
        for (ProcessSimulation processScheduler : processSchedulers) {
            sumOfSchedulersWaitingTime += processScheduler.getAverageWaitingTime();
        }
        return (sumOfSchedulersWaitingTime / processSchedulers.length);
    }

    public void displayResults() {
        double[] MicrosoftOutlookResults = { calculateOverallTurnAroundTime(fcfs/*, sjf , ljf, rr */),
                calculateOverallWaitingTime(fcfs/*, sjf , ljf, rr */),
                calculateOverallThroughput(fcfs/*, sjf , ljf, rr */) };
        String[] resultColumnNames = { "Turnaround time", "Waiting time", "Throughput" };
        Object[][] outlookResults = {
                { MicrosoftOutlookResults[0], MicrosoftOutlookResults[1], MicrosoftOutlookResults[2] }
        };
        resultsTable = new JTable(outlookResults, resultColumnNames);
        resultsTable.setGridColor(Color.gray);
        panelTwo.add(new JScrollPane(resultsTable));
        panelTwo.setVisible(true);
        frame.setVisible(true);
    }
}