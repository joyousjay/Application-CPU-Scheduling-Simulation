import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI implements TableModelListener {
    /* global variables */
    JTable appTable = new JTable(); //table to display information about application
    JTable averageResultsTable= new JTable(); //table to display average results of every schedulder
    JTable overallResultsTable = new JTable(); //table to display overall results of an application
    JFrame frame = new JFrame();
    JFrame textFrame = new JFrame();
    JRadioButton microsoftOutlook, youtube, googleChrome;
   
    JPanel panel = new JPanel(); //holds the application, simulate, and reset buttons
    JPanel panelTwo = new JPanel(); //holds the table displayed 
    JPanel panelThree = new JPanel(); //holds the loading popup for when simulate is selected
    
    //CPU scheduler objects
    FCFS fcfs = new FCFS(this); //First Come First Serve
    SJF sjf = new SJF(this); //Shortest Job First
    LJF ljf = new LJF(this);//Longest Job First
    RoundRobin rr = new RoundRobin(this);//Round Robin

    DefaultTableModel appModel = new DefaultTableModel(); //data for the application table 
    
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
        /* buttons for the user to click on an application to simulate */
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
        panelTwo.add(new JScrollPane(appTable));

        DefaultTableModel averageModel = (DefaultTableModel) averageResultsTable.getModel();
        String[] averageResultsColumnNames = {"Application", "Scheduler", "Average Turnaround time", "Average Waiting time", "Throughput" };
        averageResultsTable = new JTable();
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
        
       
        JTextField textField = new JTextField("Loading...");
        textField.setBorder(BorderFactory.createTitledBorder("Load results"));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setSize(200,200);
       // textField.setText("Loading...");
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.BLUE);
        panelThree.add(new JScrollPane(textField), BorderLayout.CENTER);
        panelThree.setLayout(new BorderLayout());
        //textFrame.add(new JScrollPane(textField), BorderLayout.CENTER);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textFrame.add(panelThree);
        textFrame.setLocationRelativeTo(panelTwo);
        textFrame.setSize(160, 160);
        //textFrame.setVisible(true);
        //textFrame.setBackground(Color.GRAY);
        //textFrame.setVisible(false);
       // textFrame.pack();

        frame.setSize(1200, 1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
        // clears user selection of application and removes the data results associated with user interaction
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fcfs.join();
                    sjf.join();
                    ljf.join();
                    rr.join();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //clear the application radio button choice
                applicationButtonGroup.clearSelection();
                // loop through the components of panelTwo and remove every component object except the first one
                Component[] panelTwoComponents = panelTwo.getComponents();
                for (int i = 1; i < panelTwoComponents.length; i++) {
                    panelTwo.remove(panelTwoComponents[i]);
                }
                //remove rows from default model and reset model
                appModel.setRowCount(0);
                averageModel.setRowCount(0);
                overallModel.setRowCount(0);
                appTable.setModel(appModel);
                averageResultsTable.setModel(averageModel);
                overallResultsTable.setModel(overallModel);
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
                //SwingUtilities.invokeLater(()->{
                textField.setVisible(true);
                panelThree.setVisible(true);
                textFrame.setVisible(true);
                //JOptionPane.showMessageDialog(panelThree, textField, "Waiting to Execute", JOptionPane.PLAIN_MESSAGE);
                // panelThree.revalidate();
                // panelThree.repaint();
                //
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
                //causes threads to wait before ending
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
           // });
           // SwingUtilities.invokeLater(()->{
                textField.setVisible(false); 
                panelThree.setVisible(false);
                textFrame.setVisible(false);   
           // });
               
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
            //store the user input where the chnage took place into the index within the array of the appropriate process and application
            updatedAppModel = (DefaultTableModel) e.getSource();
            this.msOutlookProcessesBurstTimes[e.getFirstRow()] = Integer.parseInt((String)updatedAppModel.getValueAt(e.getFirstRow(), e.getColumn()));
            this.youtubeProcessesBurstTimes[e.getFirstRow()] = Integer.parseInt((String)updatedAppModel.getValueAt(e.getFirstRow(), e.getColumn()));
            this.chromeProcessesBurstTimes[e.getFirstRow()] = Integer.parseInt((String)updatedAppModel.getValueAt(e.getFirstRow(), e.getColumn()));
        }
    }
}