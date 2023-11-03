import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class GUI  {
    static JTable baseTable = new JTable();
    JFrame frame = new JFrame();

    
    public void create() 
    {
        JRadioButton appOne, appTwo, appThree;
        appOne = new JRadioButton("Microsoft Outlook");
        appTwo = new JRadioButton("YouTube");
        appThree = new JRadioButton("Google Chrome");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Application CPU Scheduler Simulation", TitledBorder.CENTER, TitledBorder.TOP));
        frame.add(panel);

        //add button to GUI screen
        panel.add(appOne);
        panel.add(appTwo);
        panel.add(appThree);

        String[] MicrosoftOutlookProcesses = {"P1", "P2", "P3"};
        int[] MicrosoftOutlookProcessesArrivalTimes = {1,2,3};
        int[] MicrosoftOutlookProcessesBurstTimes = {1,2,3};
        Object[][] data = {
        { MicrosoftOutlookProcesses[0], MicrosoftOutlookProcessesArrivalTimes[0], MicrosoftOutlookProcessesBurstTimes[0] } ,
        { MicrosoftOutlookProcesses[1], MicrosoftOutlookProcessesArrivalTimes[1], MicrosoftOutlookProcessesBurstTimes[1] } ,
        { MicrosoftOutlookProcesses[2], MicrosoftOutlookProcessesArrivalTimes[2], MicrosoftOutlookProcessesBurstTimes[2] }
        };
        String[] columnNames = {"Process Name", "Arrival time", "Burst time"};
        

        //to enable only one application chosen at a time
        ButtonGroup applicationButtonGroup = new ButtonGroup();
        applicationButtonGroup.add(appOne);
        applicationButtonGroup.add(appTwo);
        applicationButtonGroup.add(appThree);

        JButton showAppDetailsButton = new JButton("Simulate");
        JButton reset = new JButton("Reset Simulation");
        panel.add(showAppDetailsButton);
        panel.add(reset);

        // clear user selection of application
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationButtonGroup.clearSelection();
                baseTable.setVisible(false);
                panel.remove(new JScrollPane(baseTable));
                //need to figure out how to make table itself disappear
                
            }     
        });
        //display table and results of CPU Scheduling algorithm
        //FIX: have blank table show but show data of app when selected
        appOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // table.setRowCount(0);
                if (appOne.isSelected()) {
                    // ArrayList<Process> appOneProcesses = new ArrayList<Process>();
                    // appOneProcesses.add(new Process("P1", 1, 2));
                    // appOneProcesses.add(new Process("P2", 2, 4));
                    // appOneProcesses.add(new Process("P3", 3, 6));
                    baseTable = new JTable(data, columnNames);

                    // appOneTable.setSize(400,800);
                    baseTable.setLocation(10,-100);
                    baseTable.setVisible(true);
                    baseTable.setGridColor(Color.gray);
                    panel.add(new JScrollPane(baseTable));
                    panel.setVisible(true);
                    frame.setVisible(true);
                }
                
            }
        });

        showAppDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // table.setRowCount(0);
                if (appOne.isSelected()) {
                    // ArrayList<Process> appOneProcesses = new ArrayList<Process>();
                    // appOneProcesses.add(new Process("P1", 1, 2));
                    // appOneProcesses.add(new Process("P2", 2, 4));
                    // appOneProcesses.add(new Process("P3", 3, 6));
                    appTwo.setSelected(false);
                    appThree.setSelected(false);



                    // appOneTable.setSize(400,800);
                    baseTable.setLocation(10,-100);
                    baseTable.setVisible(true);
                    baseTable.setGridColor(Color.gray);
                    panel.add(new JScrollPane(baseTable));
                    panel.setVisible(true);
                    frame.setVisible(true);
                }
                
            }
        });
        // frame.setJMenuBar(applicatioMenuBar);
        // frame.setSize(1200,1200);
        // frame.setVisible(true);
        // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    
    //the last three are what will be displayed on GUI
    //for all four scheduling algorithms
    public double calculateOverallTurnAroundTime(ProcessSimulation... processSchedulers) {
        double sumOfSchedulersTurnAroundTimes = 0;
        for (ProcessSimulation processScheduler : processSchedulers) {
            sumOfSchedulersTurnAroundTimes += processScheduler.getAverageTurnAroundTime();
        }
        return sumOfSchedulersTurnAroundTimes/processSchedulers.length;
    }

    // public double calculateOverallThroughput(){
    //     return 0.0;
    // }

    // public double calculateOverallWaitingTime(){
    //     return 0.0;
    // }
    

        //switch case for each user input option:
        // switch(input){
        //     case "Microsoft Outlook":
        //         System.out.println("You have chosen the following application: " + input);
        //         //System.out.println("\n" + "Enter the CPU Scheduling algorithm you want to simulate: FCFS, SJF, LJF, or Round Robin?");   
        //         String[] MicrosoftOutlookProcesses = {"P1", "P2", "P3"};
        //         int[] MicrosoftOutlookProcessesArrivalTimes = {1,2,3};
        //         int[] MicrosoftOutlookProcessesBurstTimes = {1,2,3};
        //         int[] MicrosoftOutlookProcessesCompletionTimes = {};

        //         int[] MicrosoftOutlookProcessesCompletionTimesForLJF = {};


        //         //String schedulingAlgorithm = scanner.nextLine();
        //         JPanel panel = new JPanel();
        //         panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Microsoft Outlook Application Data", TitledBorder.CENTER, TitledBorder.TOP));
        //         Object[][] data = {
        //             { MicrosoftOutlookProcesses[0], MicrosoftOutlookProcessesArrivalTimes[0], MicrosoftOutlookProcessesBurstTimes[0] } ,
        //             { MicrosoftOutlookProcesses[1], MicrosoftOutlookProcessesArrivalTimes[1], MicrosoftOutlookProcessesBurstTimes[1] } ,
        //             { MicrosoftOutlookProcesses[2], MicrosoftOutlookProcessesArrivalTimes[2], MicrosoftOutlookProcessesBurstTimes[2] }
        //         };
        //         String[] columnNames = {"Process Name", "Arrival time", "Burst time"};
        //         //First Come First Serve
        //                 JTable outlookFCFSTable = new JTable(data, columnNames);
        //                 JPanel FCFSheader = new JPanel();
        //                 FCFSheader.add(new JLabel("First Come First Serve"));  
        //                 FCFSheader.setLocation(600,400);
        //                 panel.add(FCFSheader);
        //                 // JButton closeButton = new JButton("Close Table");
        //                 // closeButton.setBounds(650, 500, 120, 30);
        //                 outlookFCFSTable.setGridColor(Color.gray);
        //                 // frame.add(outlookFCFSTable);
        //                 // frame.add(closeButton);
        //                 panel.add(new JScrollPane(outlookFCFSTable));
        //                 frame.add(panel);
        //                 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //                 frame.setSize(500,400);
        //                 frame.setVisible(true);
        //             //Shortest Job First
        //                 JTable outlookSJFTable = new JTable(data, columnNames);
        //                 JPanel SJFheader = new JPanel();
        //                 SJFheader.add(new JLabel("Shortest Job First"));  
        //                 panel.add(SJFheader);
        //                 panel.add(new JScrollPane(outlookSJFTable));
        //                 frame.add(panel);
        //                 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //                 frame.setSize(500,400);
        //                 frame.setVisible(true);
        //             // case "LJF":
        //             // case "Round Robin":
                

        //         //need to incorporate scheduling algorithms 
        //         // JFrame frame = new JFrame();
        //         // JPanel panel = new JPanel();
        //         // panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Microsoft Outlook Application Data", TitledBorder.CENTER, TitledBorder.TOP));
        //         // Object[][] data = {
        //         //     { "P1", "David", "AUS" },
        //         //     { "P2", "Steve", "AUS" },
        //         //     { "008", "Rohit", "IND" }
        //         // };
        //         // String[] columnNames = {"Process Name", "Arrival time", "Burst time"};
        //         // JTable table = new JTable(data, columnNames);
        //         // table.setGridColor(Color.gray);
        //         // frame.add(table);
        //         // panel.add(new JScrollPane(table));
        //         // frame.add(panel);
        //         // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //         // frame.setSize(500,400);
        //         // frame.setVisible(true);

        //         break;
        //     case "YouTube":
        //         System.out.println("You have chosen the following application: " + input); 
        //         break;
        //     case "Google Chrome":    
        //         System.out.println("You have chosen the following application: " + input);
        //         break;
        //     case "EXIT":
        //         break;
        // }
        // scanner.close();
   
}
