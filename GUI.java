import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class GUI implements TableModelListener {
    JTable appTable = new JTable();
    JTable resultsTable = new JTable();
    JTable overallResultsTable = new JTable();
    JFrame frame = new JFrame();
    JRadioButton microsoftOutlook, youtube, googleChrome;
    // each row is a panel
    JPanel panel = new JPanel();
    JPanel panelTwo = new JPanel();
    FCFS fcfs = new FCFS(this);
    SJF sjf = new SJF(this);
    LJF ljf = new LJF(this);
    RoundRobin rr = new RoundRobin(this);

    DefaultTableModel appModel = new DefaultTableModel();

    String[] microsoftOutlookProcesses = { "P1", "P2", "P3" };
    int[] microsoftOutlookProcessesArrivalOrder = { 1, 2, 3 };
    int[] microsoftOutlookProcessesBurstTimes = {2, 4, 6};
    Object[][] outlookData = {
        {microsoftOutlookProcesses[0], microsoftOutlookProcessesArrivalOrder[0], microsoftOutlookProcessesBurstTimes[0] },
        {microsoftOutlookProcesses[1], microsoftOutlookProcessesArrivalOrder[1], microsoftOutlookProcessesBurstTimes[1] },
        {microsoftOutlookProcesses[2], microsoftOutlookProcessesArrivalOrder[2], microsoftOutlookProcessesBurstTimes[2] }
    };

    String[] youtubeProcesses = { "P1", "P2", "P3", "P4", "P5", "P6" };
    int[] youtubeProcessesArrivalOrder = { 1, 2, 3, 4, 5, 6 };
    int[] youtubeProcessesBurstTimes = { 11, 9, 7, 5, 3, 1 };
    Object[][] youtubeData = {
        { youtubeProcesses[1], youtubeProcessesArrivalOrder[1], youtubeProcessesBurstTimes[1] },
        { youtubeProcesses[2], youtubeProcessesArrivalOrder[2], youtubeProcessesBurstTimes[2] },
        { youtubeProcesses[3], youtubeProcessesArrivalOrder[3], youtubeProcessesBurstTimes[3] },
        { youtubeProcesses[4], youtubeProcessesArrivalOrder[4], youtubeProcessesBurstTimes[4] },
        { youtubeProcesses[5], youtubeProcessesArrivalOrder[5], youtubeProcessesBurstTimes[5] }
    };

    String[] googleChromeProcesses = { "P1", "P2", "P3", "P4", "P5", "P6" };
    int[] googleChromeProcessesArrivalOrder = { 1, 2, 3, 4, 5, 6 };
    int[] googleChromeProcessesBurstTimes = { 11, 9, 7, 5, 3, 1 };
    Object[][] chromeData = {
        { youtubeProcesses[1], youtubeProcessesArrivalOrder[1], youtubeProcessesBurstTimes[1] },
        { youtubeProcesses[2], youtubeProcessesArrivalOrder[2], youtubeProcessesBurstTimes[2] },
        { youtubeProcesses[3], youtubeProcessesArrivalOrder[3], youtubeProcessesBurstTimes[3] },
        { youtubeProcesses[4], youtubeProcessesArrivalOrder[4], youtubeProcessesBurstTimes[4] },
        { youtubeProcesses[5], youtubeProcessesArrivalOrder[5], youtubeProcessesBurstTimes[5] }
    };

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

        String[] columnNames = {"Process name", "Arrival order", "Burst time"};
        for (int i = 0; i < columnNames.length; i++){
            appModel.addColumn(columnNames[i]);
        }
        appModel.addTableModelListener(this);

        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        String[] resultColumnNames = {"Application", "Scheduler", "Average Turnaround time", "Average Waiting time", "Throughput" };
        resultsTable = new JTable();
        model.addColumn(resultColumnNames[0]);
        model.addColumn(resultColumnNames[1]);
        model.addColumn(resultColumnNames[2]);
        model.addColumn(resultColumnNames[3]);
        model.addColumn(resultColumnNames[4]);
        resultsTable.setModel(model);

    
        DefaultTableModel overallModel = (DefaultTableModel) overallResultsTable.getModel();
        String[] overallResultColumnNames = {"Application", "Overall Turnaround time", "Overall Waiting time", "Overall Throughput" };
        overallResultsTable = new JTable();
        overallModel.addColumn(overallResultColumnNames[0]);
        overallModel.addColumn(overallResultColumnNames[1]);
        overallModel.addColumn(overallResultColumnNames[2]);
        overallModel.addColumn(overallResultColumnNames[3]);
        overallResultsTable.setModel(overallModel);

        // to enable only one application chosen at a time
        ButtonGroup applicationButtonGroup = new ButtonGroup();
        applicationButtonGroup.add(microsoftOutlook);
        applicationButtonGroup.add(youtube);
        applicationButtonGroup.add(googleChrome);

        JButton simulate = new JButton("Simulate");
        JButton reset = new JButton("Reset Simulation");
        
        panel.add(simulate);
        panel.add(reset);

        panelTwo.add(new JScrollPane(appTable));

        // clear user selection of application
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationButtonGroup.clearSelection();
                for (int i = 1; i < panelTwo.getComponentCount(); i++){
                    panelTwo.remove(i);
                }
                // if (panelTwo.getComponentCount() > 1) {
                //     panelTwo.removeAll();
                // }
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
                overallModel.setRowCount(0);
                appModel.setRowCount(0);
                resultsTable.setModel(model);
                overallResultsTable.setModel(overallModel);
               // appTable.setModel(appModel);
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
                    // if (panelTwo.getComponentCount() > 0) {
                    //     panelTwo.remove(0);
                    // }
                    // DefaultTableModel appModel = new DefaultTableModel();
                    appModel.setRowCount(0);
                    for (int i = 0; i < outlookData.length; i++){
                        appModel.addRow(outlookData[i]);
                    }
                    
                    appTable.setModel(appModel);
                    appTable.setPreferredScrollableViewportSize(new Dimension(350, 150));
                    panelTwo.repaint();
                    
                    appTable.setGridColor(Color.gray);
                    panel.setVisible(true);
                    frame.setVisible(true);
                }
            }
        });

        youtube.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (youtube.isSelected()) {
                    // if (panelTwo.getComponentCount() > 0) {
                    //     panelTwo.remove(0);
                    // }
                    appModel.setRowCount(0);
                    for (int i = 0; i < youtubeData.length; i++){
                        appModel.addRow(youtubeData[i]);
                    }
                    appTable.setModel(appModel);
                    appTable.setPreferredScrollableViewportSize(new Dimension(350, 150));
                    
                    panelTwo.repaint();
                    appTable.setGridColor(Color.gray);
                    panel.setVisible(true);
                    frame.setVisible(true);
                }
            }
        });
        googleChrome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (googleChrome.isSelected()) {
                    // if (panelTwo.getComponentCount() > 0) {
                    //     panelTwo.remove(0);
                    // }
                    appModel.setRowCount(0);
                    for (int i = 0; i < chromeData.length; i++){
                        appModel.addRow(chromeData[i]);
                    }
                    appTable.setModel(appModel);
                    appTable.setPreferredScrollableViewportSize(new Dimension(350, 150));
                    
                    panelTwo.repaint();
                    appTable.setGridColor(Color.gray);
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
                    fcfs.setApplication("Microsoft Outlook");
                    fcfs.setData(microsoftOutlookProcesses, microsoftOutlookProcessesBurstTimes);
                    sjf.setApplication("Microsoft Outlook");
                    sjf.setData(microsoftOutlookProcesses, microsoftOutlookProcessesBurstTimes);
                    ljf.setApplication("Microsoft Outlook");
                    ljf.setData(microsoftOutlookProcesses, microsoftOutlookProcessesBurstTimes);
                    rr.setApplication("Microsoft Outlook");
                    rr.setData(microsoftOutlookProcesses, microsoftOutlookProcessesBurstTimes);
                }
                if (youtube.isSelected()) {
                    fcfs.setApplication("YouTube");
                    fcfs.setData(youtubeProcesses, youtubeProcessesBurstTimes);
                    sjf.setApplication("YouTube");
                    sjf.setData(youtubeProcesses, youtubeProcessesBurstTimes);
                    ljf.setApplication("YouTube");
                    ljf.setData(youtubeProcesses, youtubeProcessesBurstTimes);
                    rr.setApplication("YouTube");
                    rr.setData(youtubeProcesses, youtubeProcessesBurstTimes);
                }
                if (googleChrome.isSelected()){
                    fcfs.setApplication("Google Chrome");
                    fcfs.setData(youtubeProcesses, youtubeProcessesBurstTimes);
                    sjf.setApplication("Google Chrome");
                    sjf.setData(youtubeProcesses, youtubeProcessesBurstTimes);
                    ljf.setApplication("Google Chrome");
                    ljf.setData(youtubeProcesses, youtubeProcessesBurstTimes);
                    rr.setApplication("Google Chrome");
                    rr.setData(youtubeProcesses, youtubeProcessesBurstTimes);
                }
                fcfs.setScheduler("FCFS");
                sjf.setScheduler("SJF");
                ljf.setScheduler("LJF");
                rr.setScheduler("Round Robin");
                //run scheduler threads for simulation
                fcfs.start();
                sjf.start();
                ljf.start();
                rr.start();
                //cause threads to wait before ending
                try {
                        fcfs.join();
                        sjf.join();
                        ljf.join();
                        rr.join();
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    //System.out.println("am I reaching here " + fcfs.getApplication());
                    displayOverallResults(fcfs.getApplication(), 
                        ProcessSimulation.calculateOverallTurnAroundTime(fcfs, sjf, ljf, rr), 
                        ProcessSimulation.calculateOverallWaitingTime(fcfs, sjf, ljf, rr), 
                        ProcessSimulation.calculateOverallThroughput(fcfs, sjf, ljf, rr));
            }
        });          
        frame.setSize(1200, 1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    /* method to display average results for every scheduler associated with an application on table in gui */
    public void displayAverageResults(String application, String scheduler, double turnaround, double waiting, double throughput) {
        Object[] data = {application, scheduler, turnaround, waiting, throughput};
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        model.addRow(data);
        resultsTable.setModel(model);
        resultsTable.setGridColor(Color.gray);
        resultsTable.setPreferredScrollableViewportSize(new Dimension(650, 170));
        panelTwo.add(new JScrollPane(resultsTable));
        panelTwo.setVisible(true);
        frame.setVisible(true);
        panelTwo.repaint();
    }
    /* method to display overall results of all schedulers for one application on table in gui */
    public void displayOverallResults(String application, double turnaround, double waiting, double throughput) {
        Object[] overallData = {application, turnaround, waiting, throughput};
        DefaultTableModel overallModel = (DefaultTableModel) overallResultsTable.getModel();
        overallModel.addRow(overallData);
        overallResultsTable.setModel(overallModel);
        overallResultsTable.setGridColor(Color.gray);
        overallResultsTable.setPreferredScrollableViewportSize(new Dimension(650, 170));
        panelTwo.add(new JScrollPane(overallResultsTable));
        panelTwo.setVisible(true);
        frame.setVisible(true);
        panelTwo.repaint();
        //new instance of scheduler thread
        fcfs = new FCFS(this);
        sjf = new SJF(this);
        ljf = new LJF(this);
        rr = new RoundRobin(this);   
    }
    /* method for when user input is taken in to modify burst times for an application's process */
    @Override
    public void tableChanged(TableModelEvent e) {
        for (int i = 0; i < appModel.getRowCount(); i++){
                        
        }
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'tableChanged'");
    }
}