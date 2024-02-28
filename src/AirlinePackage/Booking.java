package AirlinePackage;

// Importation of utilities
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;
import javax.imageio.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Booking extends JFrame{
    private JTable table;

    public Booking (String username,List<Map<String, Object>> selectedRows) {
        // Frame Setup
        setUndecorated(true);
        setLayout(null);

        // Background Image
        Image image = null;
        try {
            image = ImageIO.read(new File("src/AirlinePackage/9ce85e495a62999592932927729323bc.jpg")); // Replace with your image path
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel imgLabel = new JLabel(new ImageIcon(image));
        imgLabel.setSize(500, 800);
        imgLabel.setLocation(0, 0);
        add(imgLabel);

        // Project Logo
        ImageIcon img = new ImageIcon("src/AirlinePackage/Logo1.png"); // Replace with your logo path
        JLabel imgLabel2 = new JLabel();
        imgLabel2.setSize(300, 300);
        imgLabel2.setLocation(100, 100);
        imgLabel2.setHorizontalAlignment(JLabel.CENTER);
        imgLabel2.setVerticalAlignment(JLabel.TOP);
        imgLabel2.setIcon(img);
        add(imgLabel2);

        // Display welcome message
        Font fnt = new Font("Garamond", Font.BOLD, 15);
        JLabel welcomeLabel = new JLabel("Welcome, " + username + ". Here are your bookings:");
        welcomeLabel.setSize(300, 50);
        welcomeLabel.setLocation(100, 250);
        welcomeLabel.setFont(fnt);
        add(welcomeLabel);

        // Column Names
        String[] columnNames = {"booking_id", "airline", "destination", "departure", "arrival"};

        // Database Connection (Replace with your credentials)
        String DB_URL = "jdbc:mysql://localhost:3306/AirlineApp";
        String USER = "root";
        String PASSWORD = "";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Execute query to fetch bookings data
            String query = "SELECT booking_id, airline, destination, departure, arrival FROM bookings WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);  // Set the username parameter
            ResultSet rs = statement.executeQuery();

            // Check if ResultSet is empty
            if (!rs.next()) {
                System.out.println("No bookings found for username: " + username);
            } else {
                // Create table with data from database
                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                do {
                    Object[] row = {rs.getInt("booking_id"), rs.getString("airline"),
                            rs.getString("destination"), rs.getString("departure"), rs.getString("arrival")};
                    tableModel.addRow(row);
                } while (rs.next());

                // Create table
                table = new JTable(tableModel);

                // Add table to JScrollPane
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(50, 350, 400, 185); // Set the bounds as per your requirement

                // Add JScrollPane to JFrame
                add(scrollPane);

                // Force the table to update
                scrollPane.revalidate();
                scrollPane.repaint();

                // Make JFrame visible
                setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection errors (e.g., display error message)
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }


        // Logout button
        JButton closeBtn = new JButton("Logout and Close");
        closeBtn.setSize(150, 30);
        closeBtn.setLocation(300, 550); // Move to bottom right
        Font clfnt = new Font("Comic Sans MS", Font.BOLD, 13);
        closeBtn.setFont(clfnt);
        closeBtn.setBackground(Color.BLUE);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(closeBtn);
        // Jlabel for frame
        JLabel imglabel = new JLabel(new ImageIcon(image));
        imglabel.setSize(500, 800);
        imglabel.setLocation(0, 0);
        add(imglabel);

        setSize(500, 750);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}