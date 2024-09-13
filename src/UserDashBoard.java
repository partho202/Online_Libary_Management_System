import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class BookInfo {
    String id;
    String name;
    String author;
    String publisher;
    String status;
    BookInfo(String id, String name, String author, String publisher, String status) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
    }
}

public class UserDashBoard{
    UserDashBoard(String username) throws FileNotFoundException {
        JFrame frame = new JFrame("User Dashboard");

        JLabel title = new JLabel("Library Management System");
        title.setBounds(400, 30, 1920, 100); // Increased width for visibility
        title.setFont(new Font("Arial", Font.BOLD, 50)); // Decreased font size for visibility
        title.setForeground(Color.black);
        frame.add(title);

        JLabel dashboard = new JLabel("USER DASHBOARD");
        dashboard.setBounds(100, 150, 250, 40);
        dashboard.setFont(new Font("Arial", Font.BOLD, 20));
        dashboard.setForeground(Color.green);
        frame.add(dashboard);

        JLabel divider = new JLabel("-------------------------------");
        divider.setBounds(100, 170, 300, 40);
        divider.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(divider);

        JLabel name = new JLabel(username);
        name.setBounds(170, 200, 150, 40);
        name.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(name);

        JButton logout = new JButton("Logout");
        logout.setBounds(150, 250, 100, 40);
        logout.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginPage();
            }
        });

        // get book info
        Object[][] bookInfo = getBookInfo();
        String[] columnNames = {"ID", "Name", "Author", "Publisher", "Status"};
        DefaultTableModel model = new DefaultTableModel(bookInfo, columnNames);
        // Create a JTable with the DefaultTableModel
        JTable table = new JTable(model);
        // Create a JScrollPane to add the JTable to and handle scrolling
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(400, 250, 600, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the JScrollPane to the frame
        frame.add(scrollPane);

        JTextField searchField = new JTextField();
        searchField.setBounds(400, 200, 400, 40);
        searchField.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(searchField);

        JButton search = new JButton("Search");
        search.setBounds(820, 200, 180, 40);
        search.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(search);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchValue = searchField.getText();
                if(searchValue.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Please enter a value to search");
                }else{
                    // Search the table
                    for (int row = 0; row < table.getRowCount(); row++) {
                        for (int col = 0; col < table.getColumnCount(); col++) {
                            if (searchValue.equals(table.getValueAt(row, col))) {
                                // this will automatically set the view of the scroll in the location of the value
                                table.scrollRectToVisible(table.getCellRect(row, 0, true));
                                // this will automatically set the focus of the searched/selected row/value
                                table.setRowSelectionInterval(row, row);
                            }
                        }
                    }
                }
            }
        });



        // Frame Size
        frame.setSize(1920, 1080);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public Object[][] getBookInfo() throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\IdeaProjects\\LibaryManagement\\src\\bookList.txt");
        Scanner sc = new Scanner(file);

        ArrayList<BookInfo> bookInfoList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] book = line.split(",");
            BookInfo bookInfo = new BookInfo(book[0], book[1], book[2], book[3], book[4]);
            bookInfoList.add(bookInfo);
        }
        // Convert to a 2D array
        Object[][] bookInfoArray = new Object[bookInfoList.size()][5];
        for (int i = 0; i < bookInfoList.size(); i++) {
            bookInfoArray[i][0] = bookInfoList.get(i).id;
            bookInfoArray[i][1] = bookInfoList.get(i).name;
            bookInfoArray[i][2] = bookInfoList.get(i).author;
            bookInfoArray[i][3] = bookInfoList.get(i).publisher;
            bookInfoArray[i][4] = bookInfoList.get(i).status;
        }
        // Close the scanner
        sc.close();
        return bookInfoArray;
    }

    public static void main(String[] args) {
        try {
            UserDashBoard userDashBoard = new UserDashBoard("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



