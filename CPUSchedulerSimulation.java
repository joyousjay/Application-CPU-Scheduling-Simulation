import java.util.*;

public class CPUSchedulerSimulation {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which application would you like to simulate? (Microsoft Outlook, YouTube, Google Chrome)?");
        String input = scanner.nextLine();
        if (!(input.equals("YouTube") || (input.equals("Google Chrome")) || (input.equals("Microsoft Outlook")))){
            System.out.println("Invalid application. Please enter one of the given applications: Microsoft Outlook, YouTube, Google Chrome.");
        }else {
            System.out.println("You have chosen the following application: " + input);
        }
        scanner.close();

    }
   
}
