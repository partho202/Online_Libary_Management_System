import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
class Admin {
    String name;
    String pass;
    public Admin(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
}

public class AdminLoginPage extends JFrame implements ActionListener {
    JTextField t1, adminTextField;
    JPasswordField adminPasswordField;
    JLabel label;
    JButton login, back;
    public AdminLoginPage() {
        super("Admin Login Page");

        JLabel title = new JLabel("Library Management System");
        title.setBounds(300,80,1920,100); // Increased width for visibility
        title.setFont(new Font("Arial", Font.BOLD, 70)); // Decreased font size for visibility
        title.setForeground(Color.blue);
        add(title);

        JLabel adminLoginTitle = new JLabel("Admin Login");
        adminLoginTitle.setBounds(600, 200, 1920, 100);
        adminLoginTitle.setFont(new Font("Arial", Font.BOLD, 40));
        adminLoginTitle.setForeground(Color.red);
        add(adminLoginTitle);

        JLabel username = new JLabel("Username");
        username.setBounds(600, 300, 100, 30);
        username.setFont(new Font("Arial", Font.BOLD, 20));
        adminTextField = new JTextField();
        adminTextField.setBounds(700, 300, 150, 30);

        JLabel userpassword = new JLabel("Password");
        userpassword.setBounds(600, 350, 100, 30);
        userpassword.setFont(new Font("Arial", Font.BOLD, 20));
        adminPasswordField = new JPasswordField();
        adminPasswordField.setBounds(700, 350, 150, 30);

        label = new JLabel(" ");
        label.setBounds(640, 410, 200, 30);

        login = new JButton("Login");
        login.setBounds(750,500,100,30);
        login.addActionListener(this);

        back = new JButton("<<Back");
        back.setBounds(610,500,100,30);
        back.addActionListener(this);

        add(username);
        add(adminTextField);
        add(userpassword);
        add(adminPasswordField);
        add(label);
        add(login);
        add(back);

        setSize(1920, 1024);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Set default close operation here
    }
    @Override
    public void actionPerformed(ActionEvent x) {
        try {
            String userName = adminTextField.getText();
            String password = new String(adminPasswordField.getPassword());

            File f1 = new File("C:\\Users\\ASUS\\IdeaProjects\\LibaryManagement\\src\\Admin.txt");
            Scanner sc = null;
            try {
                sc = new Scanner(f1);
            } catch (FileNotFoundException e2) {
                throw new RuntimeException(e2);
            }

            Admin[] A = new Admin[99999];
            int i = 0;
            try {
                while (sc.hasNextLine()) {  // Fixing the infinite loop issue
                    String user = sc.nextLine();
                    String[] arr1 = user.split(",", 0);
                    A[i] = new Admin(arr1[0], arr1[1]);
                    i++;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                sc.close();  // Close the scanner
            }

            boolean validCredentials = false;
            for (int j = 0; j < i; j++) {
                if (userName.equals(A[j].name) && password.equals(A[j].pass)) {
                    validCredentials = true;
                    break;  // Exit the loop if valid credentials are found
                }
            }

            if (validCredentials) {
                if(x.getSource() == login) {
                    setVisible(false);
                    new AdminDashBoard(userName);
                }
            } else {
                label.setText("Invalid Username or Password");
            }

            if (x.getSource() == back) {
                setVisible(false);
                new LoginPage();
            }

        } catch (Exception ae) {
            System.out.println(ae);
        }
    }
    public static void main(String[] args) {
        AdminLoginPage adminloginPage = new AdminLoginPage();
    }
}
