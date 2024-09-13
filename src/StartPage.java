import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartPage extends JFrame {
    JButton start, pic;
   public StartPage() {
        super("Library Management System");
        JLabel title = new JLabel("Online Library Management System");
        title.setBounds(200,80,1920,100); // Increased width for visibility
        title.setFont(new Font("Arial", Font.BOLD, 70)); // Decreased font size for visibility
        title.setForeground(Color.WHITE);
        add(title);

        JLabel myLabel = new JLabel(new ImageIcon("bg.jpg"));
        myLabel.setBounds(0,0,1620,800);
        add(myLabel);

        start = new JButton("Get Started");
        start.setBounds(700,600,150,50);
        start.setFont(new Font("Arial", Font.BOLD, 20));
        //removed border  from the text
        start.setFocusPainted(false);
        start.setBorderPainted(false);
        start.addActionListener(e -> {
            try {
                new LoginPage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        add(start);

        setLayout(null);
        setSize(1920,1024); // Set an appropriate size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        StartPage frame = new StartPage();

    }
}
