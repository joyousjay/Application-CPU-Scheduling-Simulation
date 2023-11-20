import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class GUI implements TableModelListener {
    /* global variables */
    JTable appTable = new JTable(); //table to display information about application
    JTable averageResultsTable= new JTable(); //table to display average results of every schedulder
    JTable overallResultsTable = new JTable(); //table to display overall results of an application
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
    
    /* application information */
    String[] msOutlookProcesses = { "P1", "P2", "P3" };
    int[] msOutlookProcessesArrivalOrder = { 1, 2, 3 };
    int[] msOutlookProcessesBurstTimes = {2, 4, 6 };

    String[] youtubeProcesses = { "P1", "P2", "P3", "P4", "P5", "P6" };
    int[] youtubeProcessesArrivalOrder = { 1, 2, 3, 4, 5, 6 };
    int[] youtubeProcessesBurstTimes = { 11, 9, 7, 5, 3, 1 };

    String[] chromeProcesses = { "P1", "P2", "P3", "P4", "P5", "P6", "P7" , "P8" , "P9" , "P10" };
    int[] chromeProcessesArrivalOrder = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] chromeProcessesBurstTimes = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30 };

    public GUI() {

    }

    public void create() {
        JTextField textField = new JTextField("Loading...");
        textField.setVisible(false);
        microsoftOutlook = new JRadioButton("Microsoft Outlook");
        youtube = new JRadioButton("YouTube");
        googleChrome = new JRadioButton("Google Chrome");

        // to enable only one application chosen at a time
        ButtonGroup applicationButtonGroup = new ButtonGroup();
        applicationButtonGroup.add(microsoftOutlook);
        applicationButtonGroup.add(youtube);
        applicationButtonGroup.add(googleChrome);

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

        DefaultTableModel averageModel = (DefaultTableModel) averageResultsTable.getModel();
        String[] averageResultsColumnNames = {"Application", "Scheduler", "Average Turnaround time", "Average Waiting time", "Throughput" };
        averageResultsTable= new JTable();
        for (int i = 0; i < averageResultsColumnNames.length; i++){
            averageModel.addColumn(averageResultsColumnNames[i]);
        }
        averageResultsTable.setModel(averageModel);

    
        DefaultTableModel overallModel = (DefaultTableModel) overallResultsTable.getModel();
        String[] overallResultColumnNames = {"Application", "Overall Turnaround time", "Overall Waiting time", "Overall Throughput" };
        overallResultsTable = new JTable();
        for (int i = 0; i < overallResultColumnNames.length; i++){
            overallModel.addColumn(overallResultColumnNames[i]);
        }
        overallResultsTable.setModel(overallModel);

        JButton simulate = new JButton("Simulate");
        JButton reset = new JButton("Reset Simulation");
        
        panel.add(simulate);
        panel.add(reset);
        
        panelTwo.add(textField);

        panelTwo.add(new JScrollPane(appTable));
      
        frame.setSize(1200, 1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
        // clear user selection of application
        //FIX!! NOT WORKING PROPERLY (not removing correct tables)
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationButtonGroup.clearSelection();
                for (int i = 1; i < panelTwo.getComponentCount(); i++){
                    panelTwo.remove(i);
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
                averageModel.setRowCount(0);
                overallModel.setRowCount(0);
                appModel.setRowCount(0);
                averageResultsTable.setModel(averageModel);
                overallResultsTable.setModel(overallModel);
                //appTable.setModel(appModel);
                //reset panel
                panelTwo.revalidate();
                panelTwo.repaint();
            }
        });
        // display table and results of CPU Scheduling algorithm
        microsoftOutlook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (microsoftOutlook.isSelected()) {
                    appModel.setRowCount(0);
                    for (int i = 0; i < msOutlookProcesses.length; i++){
                        Object[] outlookData = {msOutlookProcesses[i], msOutlookProcessesArrivalOrder[i], msOutlookProcessesBurstTimes[i]};
                        appModel.addRow(outlookData);
                    }
        
                    appTable.setModel(appModel);
                    appTable.setPreferredScrollableViewportSize(new Dimension(350, 350));
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
                    appModel.setRowCount(0);
                    for (int i = 0; i < youtubeProcesses.length; i++){
                        Object[] youtubeData = {youtubeProcesses[i], youtubeProcessesArrivalOrder[i], youtubeProcessesBurstTimes[i]};
                        appModel.addRow(youtubeData);
                    }
                    appTable.setModel(appModel);
                    appTable.setPreferredScrollableViewportSize(new Dimension(350, 350));
                    
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
                    appModel.setRowCount(0);
                    for (int i = 0; i < chromeProcesses.length; i++){
                        Object[] chromeData = {chromeProcesses[i], chromeProcessesArrivalOrder[i], chromeProcessesBurstTimes[i]};
                        appModel.addRow(chromeData);
                    }
                    appTable.setModel(appModel);
                    appTable.setPreferredScrollableViewportSize(new Dimension(350, 350));
                    
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
                textField.setVisible(true);
                JOptionPane.showMessageDialog(panelTwo, textField, "Waiting to Execute", JOptionPane.PLAIN_MESSAGE);
                panelTwo.revalidate();
                panelTwo.repaint();
                // need to appear when simulate button is clicked
                if (microsoftOutlook.isSelected()) {
                    fcfs.setApplication("Microsoft Outlook");
                    fcfs.setData(msOutlookProcesses, msOutlookProcessesBurstTimes);
                    sjf.setApplication("Microsoft Outlook");
                    sjf.setData(msOutlookProcesses, msOutlookProcessesBurstTimes);
                    ljf.setApplication("Microsoft Outlook");
                    ljf.setData(msOutlookProcesses, msOutlookProcessesBurstTimes);
                    rr.setApplication("Microsoft Outlook");
                    rr.setData(msOutlookProcesses, msOutlookProcessesBurstTimes);
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
                    fcfs.setData(chromeProcesses, chromeProcessesBurstTimes);
                    sjf.setApplication("Google Chrome");
                    sjf.setData(chromeProcesses, chromeProcessesBurstTimes);
                    ljf.setApplication("Google Chrome");
                    ljf.setData(chromeProcesses, chromeProcessesBurstTimes);
                    rr.setApplication("Google Chrome");
                    rr.setData(chromeProcesses, chromeProcessesBurstTimes);
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
                } 
                catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }
                displayOverallResults(fcfs.getApplication(), 
                    ProcessSimulation.calculateOverallTurnAroundTime(fcfs, sjf, ljf, rr), 
                    ProcessSimulation.calculateOverallWaitingTime(fcfs, sjf, ljf, rr), 
                    ProcessSimulation.calculateOverallThroughput(fcfs, sjf, ljf, rr));
                // textField.setVisible(false);
                
                
            } 
        });
    } // end of create method

    /* method to display average results for every scheduler associated with an application on table in gui */
    public void displayAverageResults(String application, String scheduler, double turnaround, double waiting, double throughput) {
        Object[] data = {application, scheduler, turnaround, waiting, throughput};
        DefaultTableModel model = (DefaultTableModel) averageResultsTable.getModel();
        model.addRow(data);
        averageResultsTable.setModel(model);
        averageResultsTable.setGridColor(Color.gray);
        averageResultsTable.setPreferredScrollableViewportSize(new Dimension(650, 350));
        panelTwo.add(new JScrollPane(averageResultsTable));
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
        overallResultsTable.setPreferredScrollableViewportSize(new Dimension(650, 350));
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
    /* method for when user input is taken in to modify burst times for an application's process
     * called only when the burst times is modified by user input 
    */
    @Override
    public void tableChanged(TableModelEvent e) {
        DefaultTableModel updatedAppModel; 
        //check if column index corresponds to burst times column
        if (e.getColumn() == 2) {
            updatedAppModel = (DefaultTableModel) e.getSource();
            this.msOutlookProcessesBurstTimes[e.getFirstRow()] = Integer.parseInt((String)updatedAppModel.getValueAt(e.getFirstRow(), e.getColumn()));
            this.youtubeProcessesBurstTimes[e.getFirstRow()] = Integer.parseInt((String)updatedAppModel.getValueAt(e.getFirstRow(), e.getColumn()));
            this.chromeProcessesBurstTimes[e.getFirstRow()] = Integer.parseInt((String)updatedAppModel.getValueAt(e.getFirstRow(), e.getColumn()));
        }
    }
}