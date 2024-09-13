import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
class UserReg {
    String username;
    String password;
    String email;
    String phoneNumber;
    UserReg(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

public class RegistrationPage extends JFrame implements ActionListener {
    JLabel title, username, password, confirmPassword, email, phoneNumber;
    JTextField usernameTextField, emailTextField, phoneNumberTextField;
    JPasswordField passwordTextField, confirmPasswordTextField;
    JButton register, back;
    RegistrationPage() {
        title = new JLabel("Registration Page");
        title.setBounds(300, 40, 1920, 100);
        title.setFont(new Font("Arial", Font.BOLD, 70));
        title.setForeground(Color.BLACK);
        add(title);

        username = new JLabel("Username");
        username.setBounds(400, 200, 100, 30);
        username.setFont(new Font("Arial", Font.BOLD, 20));
        usernameTextField = new JTextField();
        usernameTextField.setBounds(700, 200, 150, 30);
        add(username);
        add(usernameTextField);

        password = new JLabel("Password");
        password.setBounds(400, 250, 100, 30);
        password.setFont(new Font("Arial", Font.BOLD, 20));
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(700, 250, 150, 30);
        add(password);
        add(passwordTextField);

        confirmPassword = new JLabel("Confirm Password");
        confirmPassword.setBounds(400, 300, 200, 30);
        confirmPassword.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPasswordTextField = new JPasswordField();
        confirmPasswordTextField.setBounds(700, 300, 150, 30);
        add(confirmPassword);
        add(confirmPasswordTextField);

        email = new JLabel("Email");
        email.setBounds(400, 350, 100, 30);
        email.setFont(new Font("Arial", Font.BOLD, 20));
        emailTextField = new JTextField();
        emailTextField.setBounds(700, 350, 150, 30);
        add(email);
        add(emailTextField);

        phoneNumber = new JLabel("Phone Number");
        phoneNumber.setBounds(400, 400, 200, 30);
        phoneNumber.setFont(new Font("Arial", Font.BOLD, 20));
        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(700, 400, 150, 30);
        add(phoneNumber);
        add(phoneNumberTextField);

        back = new JButton("<<Back");
        back.setBounds(700, 500, 100, 30);
        back.addActionListener(this);
        add(back);

        register = new JButton("Sign Up");
        register.setBounds(500, 500, 100, 30);
        register.addActionListener(this);
        add(register);

        setLayout(null);
        setSize(1920, 1024);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == register) {
                String userName = usernameTextField.getText();
                String password = new String(passwordTextField.getPassword());
                String confirmPassword = new String(confirmPasswordTextField.getPassword());
                String email = emailTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();

                if (userName.equals("") || password.equals("") || confirmPassword.equals("") || email.equals("") || phoneNumber.equals("")) {
                    JOptionPane.showMessageDialog(this, "Please fill all the fields");
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(this, "Password and Confirm Password do not match");
                } else if (!(email.contains("@")) || !(email.contains("."))) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid email address");
                } else if (!(phoneNumber.length() == 11)) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid phone number");
                } else {
                    File file = new File("C:\\Users\\ASUS\\IdeaProjects\\LibaryManagement\\src\\Users.txt");
                    Scanner sc = null;
                    try {
                        sc = new Scanner(file);
                    } catch (FileNotFoundException e2) {
                        throw new RuntimeException(e2);
                    }
                    UserReg[] temp = new UserReg[99999];
                    int i = 0;
                    try {
                        while (sc.hasNextLine()) {
                            String user = sc.nextLine();
                            String[] arr1 = user.split(",", 0);
                            temp[i] = new UserReg(arr1[0], arr1[1], arr1[2], arr1[3]);
                            i++;
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    } finally {
                        sc.close();
                    }
                    boolean validCredentials = true;
                    for (int j = 0; j < i; j++) {
                        if (phoneNumber.equals(temp[j].phoneNumber)) {
                            validCredentials = false;
                            JOptionPane.showMessageDialog(this, "Account already exists");
                            break;
                        }
                    }
                    if (validCredentials) {
                        try {
                            FileWriter fw = new FileWriter(file, true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            String userInfo = userName + "," + password + "," + email + "," + phoneNumber;
                            bw.write(userInfo);
                            bw.write("\n");
                            bw.close();
                            fw.close();
                            JOptionPane.showMessageDialog(this, "Successfully Registered");
                            setVisible(false);
                            new LoginPage();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
            if (e.getSource() == back) {
                setVisible(false);
                new LoginPage();
            }
        } catch (Exception a) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        RegistrationPage registrationPage = new RegistrationPage();
    }
}
