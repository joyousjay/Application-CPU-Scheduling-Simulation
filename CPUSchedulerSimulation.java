import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class CPUSchedulerSimulation {
    public static void main(String args[]){
        //ask the user to select one of the three applications to do the simulation on
        System.out.println("Which application would you like to simulate? (Microsoft Outlook, YouTube, Google Chrome)? ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // a user input check to ensure the correct input was entered 
        while (!(input.equals("YouTube") || (input.equals("Google Chrome")) || (input.equals("Microsoft Outlook")))){
            System.out.println("Invalid application. Please enter one of the given applications: Microsoft Outlook, YouTube, or Google Chrome ");
            input = scanner.nextLine();
        }

        //switch case:
        switch(input){
            case "Microsoft Outlook":
                System.out.println("You have chosen the following application: " + input);
                JFrame frame = new JFrame();
                String[] columnNames = {"Process Name", "Arrival time", "Burst time"};
                Object[][] data = {
                    {"P1", new Integer(1), new Integer(1)}
                };
                JTable table = new JTable(data, columnNames);
                frame.add(table);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400,400);
                frame.setLocationRelativeTo(null);  
                frame.setVisible(true);
                break;
            case "YouTube":
                System.out.println("You have chosen the following application: " + input);    
                break;
            case "Google Chrome":    
                System.out.println("You have chosen the following application: " + input);
                break;
        }
        scanner.close();

    }
   
}
