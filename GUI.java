import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

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
    //Scanner scan = new Scanner(System.in);

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

        String[] microsoftOutlookProcesses = { "P1", "P2", "P3" };
        int[] microsoftOutlookProcessesArrivalOrder = { 1, 2, 3 };

        int[] microsoftOutlookProcessesBurstTimes = { 1, 2, 3 };
        Object[][] outlookData = {
                { microsoftOutlookProcesses[0], microsoftOutlookProcessesArrivalOrder[0],
                        microsoftOutlookProcessesBurstTimes[0] },
                { microsoftOutlookProcesses[1], microsoftOutlookProcessesArrivalOrder[1],
                        microsoftOutlookProcessesBurstTimes[1] },
                { microsoftOutlookProcesses[2], microsoftOutlookProcessesArrivalOrder[2],
                        microsoftOutlookProcessesBurstTimes[2] }
        };
        String[] columnNames = {"Process name", "Arrival order", "Burst time" };
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        String[] resultColumnNames = {"Application", "Scheduler", "Average Turnaround time", "Average Waiting time", "Throughput" };
        resultsTable = new JTable();
        model.addColumn(resultColumnNames[0]);
        model.addColumn(resultColumnNames[1]);
        model.addColumn(resultColumnNames[2]);
        model.addColumn(resultColumnNames[3]);
        model.addColumn(resultColumnNames[4]);
        resultsTable.setModel(model);

        String[] youtubeProcesses = { "P1", "P2", "P3", "P4", "P5", "P6" };
        int[] youtubeProcessesArrivalOrder = { 1, 2, 3, 4, 5, 6 };
        int[] youtubeProcessesBurstTimes = { 1, 2, 3, 4, 5, 6 };
        Object[][] YouTubeData = {
                { youtubeProcesses[0], youtubeProcessesArrivalOrder[0], youtubeProcessesBurstTimes[0] },
                { youtubeProcesses[1], youtubeProcessesArrivalOrder[1], youtubeProcessesBurstTimes[1] },
                { youtubeProcesses[2], youtubeProcessesArrivalOrder[2], youtubeProcessesBurstTimes[2] },
                { youtubeProcesses[3], youtubeProcessesArrivalOrder[3], youtubeProcessesBurstTimes[3] },
                { youtubeProcesses[4], youtubeProcessesArrivalOrder[4], youtubeProcessesBurstTimes[4] },
                { youtubeProcesses[5], youtubeProcessesArrivalOrder[5], youtubeProcessesBurstTimes[5] }
        };
        // scan.close();

        // to enable only one application chosen at a time
        ButtonGroup applicationButtonGroup = new ButtonGroup();
        applicationButtonGroup.add(microsoftOutlook);
        applicationButtonGroup.add(youtube);
        applicationButtonGroup.add(googleChrome);

        JButton simulate = new JButton("Simulate");
        JButton reset = new JButton("Reset Simulation");
        JButton overallResults = new JButton("Overall Results");
        
        panel.add(simulate);
        panel.add(overallResults);
        panel.add(reset);

        // clear user selection of application
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationButtonGroup.clearSelection();
                if (panelTwo.getComponentCount() > 0) {
                    panelTwo.removeAll();
                }
                try {
                    fcfs.join();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    sjf.join();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    ljf.join();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    rr.join();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //remove rows from default model and reset model
                model.setRowCount(0);
                resultsTable.setModel(model);
                //reset panel
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
        //fix to take average results
        simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // need to appear when simulate button is clicked
                if (microsoftOutlook.isSelected()) {
                    fcfs.setApplication("Microsoft Outlook");
                    fcfs.setScheduler("FCFS");
                    fcfs.setData(microsoftOutlookProcesses, microsoftOutlookProcessesBurstTimes);
                   // sjf = new SJF(microsoftOutlookProcesses, microsoftOutlookProcessesBurstTimes);
                    ljf = new LJF(microsoftOutlookProcesses, microsoftOutlookProcessesBurstTimes);
                    rr = new RoundRobin(microsoftOutlookProcesses, microsoftOutlookProcessesBurstTimes);
                    fcfs.start();
                    //sjf.start();
                    // try {
                    //     fcfs.join();
                    // } catch (InterruptedException e1) {
                    //     // TODO Auto-generated catch block
                    //     e1.printStackTrace();
                    // }
                    // sjf.start();
                    //System.out.println("hi!");
                    // ljf.simulate();
                    // rr.simulate();
                    // callback to when schedulers are done running needed
                    // get data asynchronously (since gui time and schedulers time are different)
                }
                if (youtube.isSelected()) {
                    fcfs.setScheduler("FCFS");
                    fcfs.setApplication("YouTube");
                    fcfs.setData(youtubeProcesses, youtubeProcessesBurstTimes);
                    //sjf = new SJF(youtubeProcesses, youtubeProcessesBurstTimes);
                    sjf.setScheduler("SJF");
                    sjf.setApplication("YouTube");
                    ljf.setScheduler("LJF");
                    ljf.setApplication("YouTube");
                    //ljf = new LJF(youtubeProcesses, youtubeProcessesBurstTimes);
                    rr.setScheduler("Round Robin");
                    rr.setApplication("YouTube");
                    //rr = new RoundRobin(youtubeProcesses, youtubeProcessesBurstTimes);
                    fcfs.start();
                    // try {
                    //     fcfs.join();
                    // } catch (InterruptedException e1) {
                    //     // TODO Auto-generated catch block
                    //     e1.printStackTrace();
                    // }
                    //sjf.start();
                    //System.out.println("hi!");
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
    //fix
    public void displayAverageResults(String application, String scheduler, double turnaround, double waiting, double throughput) {
        Object[] data = {application, scheduler, turnaround, waiting, throughput};
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        model.addRow(data);
        resultsTable.setModel(model);
        //resultsTable = new JTable(outlookResults, resultColumnNames);
        resultsTable.setGridColor(Color.gray);
        panelTwo.add(new JScrollPane(resultsTable));
        panelTwo.setVisible(true);
        frame.setVisible(true);
        panelTwo.repaint();
        //new instance of thread
        //fcfs = new FCFS(this);
        sjf = new SJF(this);
        
    }

    // public void displayOverallResults(String application, double turnaround, double waiting, double throughput) {
    //     double[] MicrosoftOutlookResults = { calculateOverallTurnAroundTime(fcfs/* , sjf /*, ljf, rr */),
    //             calculateOverallWaitingTime(fcfs/* , sjf /*, ljf, rr */),
    //             calculateOverallThroughput(fcfs/* , sjf /*, ljf, rr */) };
    //     String[] resultColumnNames = { "Turnaround time", "Waiting time", "Throughput" };
    //     Object[][] outlookResults = {
    //             { MicrosoftOutlookResults[0], MicrosoftOutlookResults[1], MicrosoftOutlookResults[2] }
    //     };
    //     resultsTable = new JTable(outlookResults, resultColumnNames);
    //     resultsTable.setGridColor(Color.gray);
    //     panelTwo.add(new JScrollPane(resultsTable));
    //     panelTwo.setVisible(true);
    //     frame.setVisible(true);
    //     //new instance of thread
    //     fcfs = new FCFS(this);
    // }
}