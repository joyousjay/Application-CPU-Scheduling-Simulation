import java.awt.*;
import javax.swing.*;

public class GUI {
    public static void main(String [] args) {
        //set the GUI application with title, label, and parameters
        JFrame application = new JFrame("CPU Scheduler Application Simulation");
        JLabel label = new JLabel("Welcome!");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Times",Font.PLAIN, 24));
        label.setForeground(Color.white);
        label.setBackground(Color.black);
        label.setOpaque(true);
        application.setSize(1200, 1200);
        application.add(label);
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

       
       
        
    
    }
}