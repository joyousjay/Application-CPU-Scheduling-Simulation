import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    JTable outlookTable = new JTable();
    JTable youtubeTable = new JTable();
    JTable chromeTable = new JTable();
    JTable resultsTable = new JTable();
    JFrame frame = new JFrame();
    JRadioButton microsoftOutlook, youtube, googleChrome;
    //each row is a panel
    JPanel panel = new JPanel(); 
    JPanel panelTwo = new JPanel();
    
    public GUI() {
       
    }
    
    public void create() {
        microsoftOutlook = new JRadioButton("Microsoft Outlook");
        youtube = new JRadioButton("YouTube");
        googleChrome = new JRadioButton("Google Chrome");

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Application CPU Scheduler Simulation", TitledBorder.CENTER, TitledBorder.TOP));
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panelTwo,BorderLayout.CENTER);

        //add button to GUI screen
        panel.add(microsoftOutlook);
        panel.add(youtube);
        panel.add(googleChrome);

        String[] MicrosoftOutlookProcesses = {"P1", "P2", "P3"};
        int[] MicrosoftOutlookProcessesArrivalTimes = {1,2,3};
        int[] MicrosoftOutlookProcessesBurstTimes = {1,2,3};
        Object[][] outlookData = {
        { MicrosoftOutlookProcesses[0], MicrosoftOutlookProcessesArrivalTimes[0], MicrosoftOutlookProcessesBurstTimes[0] } ,
        { MicrosoftOutlookProcesses[1], MicrosoftOutlookProcessesArrivalTimes[1], MicrosoftOutlookProcessesBurstTimes[1] } ,
        { MicrosoftOutlookProcesses[2], MicrosoftOutlookProcessesArrivalTimes[2], MicrosoftOutlookProcessesBurstTimes[2] } 
        };
        String[] columnNames = {"Process Name", "Arrival order", "Burst time"};

        String[] YouTubeProcesses = {"P1", "P2", "P3", "P4", "P5", "P6"};
        int[] YouTubeProcessesArrivalTimes = {1,2,3,4,5,6};
        int[] YouTubeProcessesBurstTimes = {1,2,3,4,5,6};
        Object[][] YouTubeData = {
        { YouTubeProcesses[0], YouTubeProcessesArrivalTimes[0], YouTubeProcessesBurstTimes[0] } ,
        { YouTubeProcesses[1], YouTubeProcessesArrivalTimes[1], YouTubeProcessesBurstTimes[1] } ,
        { YouTubeProcesses[2], YouTubeProcessesArrivalTimes[2], YouTubeProcessesBurstTimes[2] } ,
        { YouTubeProcesses[3], YouTubeProcessesArrivalTimes[3], YouTubeProcessesBurstTimes[3] } ,
        { YouTubeProcesses[4], YouTubeProcessesArrivalTimes[4], YouTubeProcessesBurstTimes[4] } ,
        { YouTubeProcesses[5], YouTubeProcessesArrivalTimes[5], YouTubeProcessesBurstTimes[5] } 
        };

        //double[] MicrosoftOutlookResults = {calculateOverallThroughput(), calculateOverallTurnAroundTime(), calculateOverallWaitingTime()};
        // Object[] outlookResults = {
        //     (MicrosoftOutlookResults[0]),
        //     (MicrosoftOutlookResults[1]),
        //     (MicrosoftOutlookResults[2])
        // };
        // String[] resultColumnNames = {"Throughput", "Turnaround time", "Waiting time"};

        //to enable only one application chosen at a time
        ButtonGroup applicationButtonGroup = new ButtonGroup();
        applicationButtonGroup.add(microsoftOutlook);
        applicationButtonGroup.add(youtube);
        applicationButtonGroup.add(googleChrome);

        JButton simulate = new JButton("Simulate");
        JButton reset = new JButton("Reset Simulation");
        panel.add(simulate);
        panel.add(reset);
        
        // clear user selection of application
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationButtonGroup.clearSelection();
                if (panelTwo.getComponentCount() > 0){
                    panelTwo.removeAll(); 
                }
                panelTwo.revalidate();
                panelTwo.repaint();
            }     
        });
        //display table and results of CPU Scheduling algorithm
        //FIX: take in user changing the burst time values
        microsoftOutlook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // table.setRowCount(0);
                if (microsoftOutlook.isSelected()) {
                    if (panelTwo.getComponentCount() > 0){
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
                    if (panelTwo.getComponentCount() > 0){
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
                FCFS fcfs;
                SJF sjf;
                LJF ljf;
                RoundRobin rr;
                //need to appear when simulate button is clicked
                String[] resultColumnNames = {"Turnaround time", "Waiting time", "Throughput"};
                if (microsoftOutlook.isSelected()) {
                    fcfs = new FCFS(MicrosoftOutlookProcesses, MicrosoftOutlookProcessesBurstTimes);
                    sjf = new SJF(MicrosoftOutlookProcesses, MicrosoftOutlookProcessesBurstTimes);
                    ljf = new LJF(MicrosoftOutlookProcesses, MicrosoftOutlookProcessesBurstTimes);
                    rr = new RoundRobin(MicrosoftOutlookProcesses, MicrosoftOutlookProcessesBurstTimes);
                    fcfs.simulate();
                    sjf.simulate();
					System.out.println("hi!");
                    //ljf.simulate();
                    //rr.simulate();
                    //callback to when schedulers are done running needed
					//get data asynchronously (since gui time and scjedulers time are different)
                    double[] MicrosoftOutlookResults = {calculateOverallTurnAroundTime(fcfs, sjf/*, ljf, rr */), calculateOverallWaitingTime(fcfs, sjf /*, ljf, rr */), calculateOverallThroughput(fcfs, sjf /*, ljf, rr */)};
                    Object[][] outlookResults = {
                        {MicrosoftOutlookResults[0], MicrosoftOutlookResults[1], MicrosoftOutlookResults[2]}
                    };
                    resultsTable = new JTable(outlookResults, resultColumnNames);
                    resultsTable.setGridColor(Color.gray);
                    panelTwo.add(new JScrollPane(resultsTable)); 
                    panelTwo.setVisible(true);
                    frame.setVisible(true);
                }   
            }
        });
        frame.setSize(1200,1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//panelTwo.add(System.out.println("hi"));

    }
    /* final results of all processes' data from every scheduling algorithm */
    public double calculateOverallTurnAroundTime(ProcessSimulation... processSchedulers) {
        double sumOfSchedulersTurnAroundTimes = 0.0;
        for (ProcessSimulation processScheduler : processSchedulers) {
            sumOfSchedulersTurnAroundTimes += processScheduler.getAverageTurnAroundTime();
        }
        return sumOfSchedulersTurnAroundTimes/processSchedulers.length;
    }

    public double calculateOverallThroughput(ProcessSimulation... processSchedulers){
        double sumOfSchedulersThroughput = 0.0;
        for (ProcessSimulation processScheduler : processSchedulers) {
            sumOfSchedulersThroughput += processScheduler.getThroughput();
        }
        return sumOfSchedulersThroughput/processSchedulers.length;
    }

    public double calculateOverallWaitingTime(ProcessSimulation... processSchedulers){
        double sumOfSchedulersWaitingTime = 0.0;
        for (ProcessSimulation processScheduler : processSchedulers) {
            sumOfSchedulersWaitingTime += processScheduler.getAverageWaitingTime();
        }
        return sumOfSchedulersWaitingTime/processSchedulers.length;
    }
}