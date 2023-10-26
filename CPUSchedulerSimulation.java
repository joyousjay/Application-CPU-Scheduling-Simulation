import java.util.*;
import javax.swing.*;
import javax.swing.border.*; 
import java.awt.*;

public class CPUSchedulerSimulation {
    public static void main(String args[]){

       // GUI frame = new GUI();
        //ask the user to select one of the three applications to do the simulation on
        System.out.println("Which application would you like to simulate? (Microsoft Outlook, YouTube, Google Chrome)?"
        + "\n" + "To exit the application, enter EXIT");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // a user input check to ensure the correct input was entered 
        while (!(input.equals("YouTube") || (input.equals("Google Chrome")) || (input.equals("Microsoft Outlook")) || (input.equals("EXIT")))){
            System.out.println("Invalid application. Please enter one of the given applications: Microsoft Outlook, YouTube, or Google Chrome ");
            input = scanner.nextLine();
        }
        
        

        //switch case for each user input option:
        switch(input){
            case "Microsoft Outlook":
                System.out.println("You have chosen the following application: " + input);
                //System.out.println("\n" + "Enter the CPU Scheduling algorithm you want to simulate: FCFS, SJF, LJF, or Round Robin?");   
                String[] MicrosoftOutlookProcesses = {"P1", "P2", "P3"};
                int[] MicrosoftOutlookProcessesArrivalTimes = {1,2,3};
                int[] MicrosoftOutlookProcessesBurstTimes = {1,2,3};
                int[] MicrosoftOutlookProcessesCompletionTimes = {};

                int[] MicrosoftOutlookProcessesCompletionTimesForLJF = {};


                //String schedulingAlgorithm = scanner.nextLine();
                JFrame frame = new JFrame();
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Microsoft Outlook Application Data", TitledBorder.CENTER, TitledBorder.TOP));
                Object[][] data = {
                    { MicrosoftOutlookProcesses[0], MicrosoftOutlookProcessesArrivalTimes[0], MicrosoftOutlookProcessesBurstTimes[0] } ,
                    { MicrosoftOutlookProcesses[1], MicrosoftOutlookProcessesArrivalTimes[1], MicrosoftOutlookProcessesBurstTimes[1] } ,
                    { MicrosoftOutlookProcesses[2], MicrosoftOutlookProcessesArrivalTimes[2], MicrosoftOutlookProcessesBurstTimes[2] }
                };
                String[] columnNames = {"Process Name", "Arrival time", "Burst time"};
                //First Come First Serve
                        JTable outlookFCFSTable = new JTable(data, columnNames);
                        JPanel FCFSheader = new JPanel();
                        FCFSheader.add(new JLabel("First Come First Serve"));  
                        FCFSheader.setLocation(600,400);
                        panel.add(FCFSheader);
                        // JButton closeButton = new JButton("Close Table");
                        // closeButton.setBounds(650, 500, 120, 30);
                        // outlookFCFSTable.setGridColor(Color.gray);
                        // frame.add(outlookFCFSTable);
                        // frame.add(closeButton);
                        panel.add(new JScrollPane(outlookFCFSTable));
                        frame.add(panel);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setSize(500,400);
                        frame.setVisible(true);
                    //Shortest Job First
                        JTable outlookSJFTable = new JTable(data, columnNames);
                        JPanel SJFheader = new JPanel();
                        SJFheader.add(new JLabel("Shortest Job First"));  
                        panel.add(SJFheader);
                        panel.add(new JScrollPane(outlookSJFTable));
                        frame.add(panel);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setSize(500,400);
                        frame.setVisible(true);
                    // case "LJF":
                    // case "Round Robin":
                

                //need to incorporate scheduling algorithms 
                // JFrame frame = new JFrame();
                // JPanel panel = new JPanel();
                // panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Microsoft Outlook Application Data", TitledBorder.CENTER, TitledBorder.TOP));
                // Object[][] data = {
                //     { "P1", "David", "AUS" },
                //     { "P2", "Steve", "AUS" },
                //     { "008", "Rohit", "IND" }
                // };
                // String[] columnNames = {"Process Name", "Arrival time", "Burst time"};
                // JTable table = new JTable(data, columnNames);
                // table.setGridColor(Color.gray);
                // frame.add(table);
                // panel.add(new JScrollPane(table));
                // frame.add(panel);
                // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // frame.setSize(500,400);
                // frame.setVisible(true);

                break;
            case "YouTube":
                System.out.println("You have chosen the following application: " + input); 
                break;
            case "Google Chrome":    
                System.out.println("You have chosen the following application: " + input);
                break;
            case "EXIT":
                break;
        }
        scanner.close();
        }
    
    //FCFS
    public int firstComeFirstServe(){
        return 0;
    }

    //SJF

    //LJF


    //Round Robin
   
}
