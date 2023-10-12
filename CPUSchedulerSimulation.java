import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class CPUSchedulerSimulation {
    public static void main(String args[]){
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

        //switch case:
        switch(input){
            case "Microsoft Outlook":
                System.out.println("You have chosen the following application: " + input);
                JFrame frame = new JFrame();
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Microsoft Outlook Application Data", TitledBorder.CENTER, TitledBorder.TOP));
                Object[][] data = {
                    { "001", "David", "AUS" },
         { "002", "Steve", "AUS" },
         { "003", "Yuvraj", "IND" },
         { "004", "Kane", "NZ" },
         { "005", "Ben", "ENG" },
         { "006", "Eion", "ENG" },
         { "007", "Miller", "SA" },
         { "008", "Rohit", "IND" }
                };
                String[] columnNames = {"Process Name", "Arrival time", "Burst time"};
                JTable table = new JTable(data, columnNames);
                table.setGridColor(Color.gray);
                frame.add(table);
                panel.add(new JScrollPane(table));
                frame.add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400,400);
                frame.setVisible(true);
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
   
}
