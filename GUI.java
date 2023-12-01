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
    JFrame textFrame = new JFrame();
    JRadioButton[] appButtons; //array of application buttons
   
    JPanel panel = new JPanel(); //holds the application, simulate, and reset buttons
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
    private Application[] applications; //array of applications
   
    //class constructor
    public GUI(Application... applications) {
        this.applications = applications;
    }

    public void create() {
        //creating a radio button for each exisiting application 
        appButtons = new JRadioButton[applications.length];
        for (int i = 0; i < applications.length; i++) {
            appButtons[i] = new JRadioButton(applications[i].getName());
        }

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
        appModel.addTableModelListener(this);
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

        // display table and results of the CPU Scheduling algorithms for a selection application
        // loops through the array of application buttons 
        for (int i = 0; i < appButtons.length; i++) {
            int index = i;
            appButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   //for (int j = 0; j < appButtons.length; j++) {
                       
                        if (appButtons[index].isSelected()){
                            appModel.setRowCount(0);
                            // loops through the processes an application from the application list contains
                            for (int j = 0; j < applications[index].getProcesses().length; j++) {
                                Object[] appData = {applications[index].getProcesses()[j], applications[index].getArrivalOrders()[j], applications[index].getBurstTimes()[j]};
                                appModel.addRow(appData);
                            }

                            appTable.setModel(appModel);
                            //appTable.setPreferredScrollableViewportSize(new Dimension(350, 350));
                            panelTwo.repaint();
                            
                            appTable.setGridColor(Color.gray);
                            panel.setVisible(true);
                            frame.setVisible(true);
                        }
                }       
            });
        }
        
        simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sets the application and the application data to each scheduler 
                for (int i = 0; i < appButtons.length; i++) {
                    if (appButtons[i].isSelected()) {
                        fcfs.setApplication(applications[i]);
                        sjf.setApplication(applications[i]);
                        ljf.setApplication(applications[i]);
                        rr.setApplication(applications[i]);         
                    }
                }
                
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
                        // popup.setModal(true);
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
        panelTwo.add(new JScrollPane(averageResultsTable)).setVisible(true);
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
        panelTwo.add(new JScrollPane(overallResultsTable)).setVisible(true);
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
            for (int i = 0; i < appButtons.length; i++) {
                if (appButtons[i].isSelected()){
                    this.applications[i].getBurstTimes()[e.getFirstRow()] = Integer.parseInt((String)updatedAppModel.getValueAt(e.getFirstRow(), e.getColumn()));
                }
            }
        }
    }
}