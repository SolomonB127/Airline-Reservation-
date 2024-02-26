package AirlinePackage;

// Importation of utilities
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.imageio.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Home extends JFrame {
    private JTable table;
    public Home(String username) {
        // Production of frame
        setUndecorated(true);
        setLayout(null);
        Image image = null;
        try {
            image = ImageIO.read(new File("src/AirlinePackage/9ce85e495a62999592932927729323bc.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Font fnt = new Font("Garamond", Font.BOLD, 15);

        // Project Logo
        ImageIcon img = new ImageIcon("src/AirlinePackage/Logo1.png");
        JLabel imgLabel = new JLabel();
        imgLabel.setSize(300, 300);
        imgLabel.setLocation(100, 100);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imgLabel.setVerticalAlignment(JLabel.TOP);
        imgLabel.setIcon(img);
        add(imgLabel);

        // Display welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + username + ".");
        welcomeLabel.setSize(300, 50);
        welcomeLabel.setLocation(100, 250);
        welcomeLabel.setFont(fnt);
        add(welcomeLabel);

        // Column Names
        String[] columnNames = {"Airline", "Destination", "Time", "Available Seats"};

        // Flights-Data
        Object[][] data = {
                {"Qatar Airways", "Brisbane, Vienna, Dhaka", "10:00", 5},
                {"Singapore Airline", "Melbourne, Manama, Brussels", "15:00", 0},
                {"Emirates", "Lagos, Abuja, Perth", "20:00", 25},
                {"ANA All Nippon Airways", "Sydney,Buenos Aires, Kuala Lumpar", "11:09", 1},
                {"Qantas Airways", "Arizona, Hiroshima, Seoul", "20:00", 10},
                {"Japan Airlines", "Sao Paulo, Cancun, Durban", "13:00", 0},
                {"Turkish Airlines", "Pretoria, Johannesburg, Los Cabos", "19:00", 0},
                {"Air France", "Dire Dawa, Ibadan, Tel Aviv-Yafo", "16:45", 14},
                {"Korean Air", "Haifa, Bristol, Birmingham", "09:11", 0},
                {"Swiss Int. Airlines", "Medina, Ashdod, Manitoba", "12:08", 1},
        };
        // Create table
        table = new JTable(new DefaultTableModel(data, columnNames));

        // Add mouse listener to handle row clicks
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col == 3 && (int)table.getValueAt(row, col) > 0) {
                    // If the user clicked on a row with available seats, navigate to booking page
                    // TODO: Implement navigation to booking page
                    System.out.println("Navigating to booking page for " + table.getValueAt(row, 0));
                }
            }
        });

        // Add table to frame
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 350, 400, 185);
        this.add(scrollPane);

//        Logout button
        JButton closeBtn = new JButton("Logout");
        closeBtn.setSize(80, 30);
        closeBtn.setLocation(400, 70);
        Font clfnt = new Font("Comic Sans MS", Font.BOLD, 13);
        closeBtn.setFont(clfnt);
        closeBtn.setBackground(Color.BLUE);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        this.add(closeBtn);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Jlabel for frame
        JLabel imglabel = new JLabel(new ImageIcon(image));
        imglabel.setSize(500,800);
        imglabel.setLocation(0, 0);
        add(imglabel);

        setSize(500,750);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
