import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
class User{
    String name;
    String pass;
    User (String name, String pass){
        this.name = name;
        this.pass  = pass;
    }
}
public class LoginPage extends JFrame implements ActionListener{
    JTextField t1,userTextField;
    JPasswordField passwordField;
    JLabel label;
    JButton adminLogin, login, back, registration;
    User[] u =  new User[99999];
    public LoginPage(){
        super("User Login Page");
        JLabel title = new JLabel("Online Library Management System");
        title.setBounds(200,80,1920,100); // Increased width for visibility
        title.setFont(new Font("Arial", Font.BOLD, 70)); // Decreased font size for visibility
        title.setForeground(Color.blue);
        add(title);

        JLabel userLoginTitle = new JLabel("User Login");
        userLoginTitle.setBounds(610, 200, 1920, 100);
        userLoginTitle.setFont(new Font("Arial", Font.BOLD, 40));
        userLoginTitle.setForeground(Color.green);
        add(userLoginTitle);

        JLabel username = new JLabel("Username");
        username.setBounds(600,300,100,30);
        username.setFont(new Font("Arial", Font.BOLD, 20));
        userTextField= new JTextField();
        userTextField.setBounds(700,300,150,30);

        JLabel userpassword = new JLabel("Password");
        userpassword.setBounds(600,360,100,30);
        userpassword.setFont(new Font("Arial", Font.BOLD, 20));
        passwordField = new JPasswordField();
        passwordField.setBounds(700,360,150,30);

        label = new JLabel(" ");
        label.setBounds(650,410,200,30);

        login = new JButton("Login");
        login.setBounds(750,500,100,30);
        login.addActionListener(this);

        back = new JButton("<<Back");
        back.setBounds(610,500,100,30);
        back.addActionListener(this);

        adminLogin = new JButton("Admin Login");
        adminLogin.setBounds(650,650,150,30);
        adminLogin.addActionListener(this);

        registration = new JButton("Create Account");
        registration.setBounds(650,600,150,30);
        registration.addActionListener(this);

        add(username);
        add(userTextField);
        add(userpassword);
        add(passwordField);
        add(label);
        add(login);
        add(back);
        add(adminLogin);
        add(registration);

        setSize(1920,1024);
        setLayout(null);
        setVisible(true);
        JFrame frame = new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        try{
            String userName = userTextField.getText();
            String password = new String(passwordField.getPassword());

            File f1 = new File ("C:\\Users\\ASUS\\IdeaProjects\\LibaryManagement\\src\\Users.txt");
            Scanner sc = null;
            try {
                sc = new Scanner(f1);
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
            int count = 0;
            try{
                while(sc.hasNextLine()){
                    String user = sc.nextLine();
                    String[] arr = user.split(",");
                    u[count] = new User(arr[0],arr[1]);
                    count++;
                }
            }catch(Exception ex){
                System.out.println(ex);
            }finally {
                sc.close();
            }
            boolean validCredentials = false;
            for (int j = 0; j < count; j++) {
                if (userName.equals(u[j].name) && password.equals(u[j].pass)) {
                    validCredentials = true;
                    setVisible(false);
                    break; // Exit the loop if valid credentials are found
                } else {
                    label.setText("Invalid Username or Password");
                }
            }
            if (e.getSource() == login) {
                if (validCredentials) {
                    setVisible(false);
                    new UserDashBoard(userName);
                }
            }
            if(e.getSource() == back){
                setVisible(false);
                new StartPage();
            }
            if(e.getSource() == adminLogin){
                setVisible(false);
                new AdminLoginPage();
            }
            if(e.getSource() == registration){
                setVisible(false);
                new RegistrationPage();
            }
        }catch(Exception ae){
            System.out.println(ae);
        }
    }
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
    }
}


















