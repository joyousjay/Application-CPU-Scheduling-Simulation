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
        System.out.println("You have chosen the following application: " + input);

        //switch case:
        String algorithm = "algorithm name";
        switch(algorithm){
            case "Microsoft Outlook":
                // JFrame frame = new JFrame();
                // String[] columnNames = {"Process Name, Arrival time, Burst time"};
                // JTable table = new JTable(null, columnNames);
                // frame.add(table);
                // frame.setVisible(false);
                break;
            case "YouTube":
                break;
            case "Google Chrome":     
        }
        scanner.close();

    }
   
}
