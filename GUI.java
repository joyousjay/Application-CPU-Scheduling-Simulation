import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


public class GUI {
    /* global variables */
    JTable appTable = new JTable(); //table to display information about application
    JTable averageResultsTable= new JTable(); //table to display average results of every schedulder
    JTable overallResultsTable = new JTable(); //table to display overall results of an application
    JFrame frame = new JFrame();
    JFrame textFrame = new JFrame();
    JRadioButton[] appButtons; //array of application buttons
    JComboBox<Integer> processSetBox; //list to store the process set sizes
    JPanel panel = new JPanel(); //holds the applications, simulate, and reset buttons
    JPanel panelTwo = new JPanel(new FlowLayout()); //holds the table displayed 
    JDialog popup = new JDialog(frame, "Simulate Executing", true); //holds the loading popup for when simulate is selected
    
    //CPU scheduler objects
    FCFS fcfs = new FCFS(this); //First Come First Serve
    SJF sjf = new SJF(this); //Shortest Job First
    LJF ljf = new LJF(this);//Longest Job First
    RoundRobin rr = new RoundRobin(this);//Round Robin

    DefaultTableModel appModel = new DefaultTableModel(); //data for the application table 
    DefaultTableModel averageModel = new DefaultTableModel();
    DefaultTableModel overallModel = new DefaultTableModel();
    DefaultComboBoxModel<Integer> processSetModel = new DefaultComboBoxModel<>();
    private Application[] applications; //array of applications
    private ProcessSet[] processSets;
    JLabel processComboBoxLabel = new JLabel("Process Set Size");
    
    //enables scrolling on results table 
    JScrollPane averagePane = new JScrollPane();
    JScrollPane overallPane = new JScrollPane();

    //class constructor
    public GUI(ProcessSet[] processSets, Application[] applications) {
        this.applications = applications;
        this.processSets = processSets;
    }

    public void create() {

        //creating a radio button for each exisiting application 
        appButtons = new JRadioButton[applications.length];
        for (int i = 0; i < applications.length; i++) {
            appButtons[i] = new JRadioButton(applications[i].getName());
        }
        panelTwo.add(processComboBoxLabel);
        processSetBox = new JComboBox<Integer>();
        processSetBox.setModel(processSetModel);
      
        //add process set sizes to process set model
        for (int i = 0; i < processSets.length; i++) {
           processSetModel.addElement(processSets[i].getSize()); 
        }
        panelTwo.add(processSetBox);
        processSetBox.setVisible(false);
        processComboBoxLabel.setVisible(false);
        
        // adding the radiobuttons to a group to enable only one application chosen at a time
        ButtonGroup applicationButtonGroup = new ButtonGroup();
        for (int i = 0; i < appButtons.length; i++) {
            applicationButtonGroup.add(appButtons[i]);
        }
        
        //frame adds panel one and panel two and gives the GUI a title for the simulation
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Application CPU Scheduler Simulation", TitledBorder.CENTER, TitledBorder.TOP));
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panelTwo, BorderLayout.CENTER);
        
        // add button for the application programs to the panel
        for (int i = 0; i < appButtons.length; i++) {
            panel.add(appButtons[i]);
        }  
       
        //the data being added to the table for an application
        String[] columnNames = {"Process name", "Arrival order", "Burst time"};
        for (int i = 0; i < columnNames.length; i++){
            appModel.addColumn(columnNames[i]);
        }
        appTable.setPreferredScrollableViewportSize(new Dimension(350,350));
        //appModel.addTableModelListener(this);
        panelTwo.add(new JScrollPane(appTable));
       
        //the data being added to the table for the average results of a CPU scheduler for an application
        averageModel = (DefaultTableModel) averageResultsTable.getModel();
        String[] averageResultsColumnNames = {"Application", "Scheduler", "Average Turnaround time", "Average Waiting time", "Throughput" };
        averageResultsTable = new JTable();
        for (int i = 0; i < averageResultsColumnNames.length; i++){
            averageModel.addColumn(averageResultsColumnNames[i]);
        }
        averageResultsTable.setModel(averageModel);
        panelTwo.add(new JScrollPane(averageResultsTable)).setVisible(false);

        //the data being added to the table for the overall results of an application from its CPU schedulers performance
        overallModel = (DefaultTableModel) overallResultsTable.getModel();
        String[] overallResultColumnNames = {"Application", "Overall Turnaround time", "Overall Waiting time", "Overall Throughput" };
        overallResultsTable = new JTable();
        for (int i = 0; i < overallResultColumnNames.length; i++){
            overallModel.addColumn(overallResultColumnNames[i]);
        }
        //overallResultsTable.setVisible(false);
        overallResultsTable.setModel(overallModel);
        panelTwo.add(new JScrollPane(overallResultsTable)).setVisible(false);
        

        //create and add the buttons to simulate and reset the simulation 
        JButton simulate = new JButton("Simulate");
        JButton reset = new JButton("Reset Simulation");
        panel.add(simulate);
        panel.add(reset);
        
        //create popup label for when simulate is executing
        JLabel popupLabel = new JLabel("<html>Loading...<br>Please wait</html>");
        popup.setLayout(new FlowLayout());
        popup.setSize(200, 200);
        popup.setLocationRelativeTo(panelTwo);
        popup.add(popupLabel);

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
                    e1.printStackTrace();
                }
                //clear the application radio button choice
                applicationButtonGroup.clearSelection();
                // loop through the components of panelTwo and remove every component object except the first one
                Component[] panelTwoComponents = panelTwo.getComponents();
                for (int i = 3; i < panelTwoComponents.length; i++) {
                    panelTwo.remove(panelTwoComponents[i]);
                }
                //remove rows from default model and reset model
                appModel.setRowCount(0);
                averageModel.setRowCount(0);
                overallModel.setRowCount(0);
                //set the default process set to set one
                processSetModel.setSelectedItem(processSets[0].getSize());
                appTable.setModel(appModel);
                //regenerate the table model
                averageResultsTable.setModel(averageModel);
                overallResultsTable.setModel(overallModel);
                //remove process set box and label
                processSetBox.setVisible(false);
                processComboBoxLabel.setVisible(false);
                //reset panel
                panelTwo.revalidate();
                panelTwo.repaint();
            }
        });

        // display table and results of the CPU Scheduling algorithms for a selection application
        // loops through the array of application buttons 
        for (int i = 0; i < appButtons.length; i++) {
            int index = i;
            appButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {        
                        if (appButtons[index].isSelected()){
                            appModel.setRowCount(0);
                            processComboBoxLabel.setVisible(true);
                            processSetBox.setVisible(true);
                           
                            //sets the default process set size to three
                            for (int j = 0; j < processSets[0].getSize(); j++) {
                                Object[] appData = {processSets[0].getProcesses()[j], processSets[0].getArrivalOrders()[j], applications[index].getBurstTimes()[j]};
                                appModel.addRow(appData);
                            }
                            //set the populated app model data into the application table
                            appTable.setModel(appModel);
                            panelTwo.repaint();
                            
                            appTable.setGridColor(Color.gray);
                            panel.setVisible(true);
                            frame.setVisible(true);
                        }
                }       
            });
        }

        //action listener to when the dropdown value is selected for a specific application
        processSetBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //loops through array of application buttons to find which individual application is selected
                for (int i = 0; i < appButtons.length; i++) {
                    if (appButtons[i].isSelected()) {
                        appModel.setRowCount(0);
                        //index of the combo box selected is processSetBox.getSelectedIndex()
                        int comboIndex = processSetBox.getSelectedIndex();
                        //loop through array of process sets to find which process set is selected and to set the application data
                        for (int j = 0; j < processSets[comboIndex].getSize(); j++) {
                            Object[] appData = {processSets[comboIndex].getProcesses()[j], processSets[comboIndex].getArrivalOrders()[j], applications[i].getBurstTimes()[j]};
                            appModel.addRow(appData);
                        }
                    }
                }
            }    
        });
        
        simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sets the process and application data to each scheduler 
                int comboIndex = processSetBox.getSelectedIndex();
                for (int i = 0; i < appButtons.length; i++) {
                    if (appButtons[i].isSelected()) {
                        fcfs.setData(processSets[0], applications[i]);
                        sjf.setData(processSets[comboIndex], applications[i]);
                        ljf.setData(processSets[comboIndex], applications[i]);
                        rr.setData(processSets[comboIndex], applications[i]);       
                    }
                }
                //sets the schedulers' names
                fcfs.setScheduler("FCFS");
                sjf.setScheduler("SJF");
                ljf.setScheduler("LJF");
                rr.setScheduler("Round Robin");
                //a threaded function that invokes scheduler threads 
                (new Thread(() ->{
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
                            e1.printStackTrace();
                    }

                    //close loading dialog on complete
                    SwingUtilities.invokeLater(()->{ 
                        popup.dispose();
                    });
                })).start();;
                
                //to allow visual thread to be locked while scheduler threads runs
                popup.setVisible(true);

                displayOverallResults(fcfs.getApplication().getName(), 
                    ProcessSimulation.calculateOverallTurnAroundTime(fcfs, sjf, ljf, rr), 
                    ProcessSimulation.calculateOverallWaitingTime(fcfs, sjf, ljf, rr), 
                    ProcessSimulation.calculateOverallThroughput(fcfs, sjf, ljf, rr));    
            } 
        });
    } // end of create method

    /* method to display average results for every scheduler associated with an application on table in gui */
    public void displayAverageResults(String appName, String scheduler, double turnaround, double waiting, double throughput) {
        Object[] data = {appName, scheduler, turnaround, waiting, throughput};
        averageModel = (DefaultTableModel) averageResultsTable.getModel();
        averageModel.addRow(data);
        averageResultsTable.setModel(averageModel);
        averageResultsTable.setGridColor(Color.gray);
        averageResultsTable.setPreferredScrollableViewportSize(new Dimension(600, 350));
        averagePane.setViewportView(averageResultsTable);
        panelTwo.add((averagePane)).setVisible(true);
        panelTwo.setVisible(true);
        frame.setVisible(true);
        panelTwo.revalidate();
        panelTwo.repaint();
    }

    /* method to display overall results of all schedulers for one application on table in gui */
    public void displayOverallResults(String appName, double turnaround, double waiting, double throughput) {
        Object[] overallData = {appName, turnaround, waiting, throughput};
        overallModel = (DefaultTableModel) overallResultsTable.getModel();
        overallModel.addRow(overallData);
        overallResultsTable.setModel(overallModel);
        overallResultsTable.setGridColor(Color.gray);
        overallResultsTable.setPreferredScrollableViewportSize(new Dimension(600, 350));
        overallPane.setViewportView(overallResultsTable);
        panelTwo.add(overallPane).setVisible(true);
        panelTwo.setVisible(true);
        frame.setVisible(true);
        panelTwo.revalidate();
        panelTwo.repaint();
        //new instance of scheduler thread
        fcfs = new FCFS(this);
        sjf = new SJF(this);
        ljf = new LJF(this);
        rr = new RoundRobin(this);   
    }
}