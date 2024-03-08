package AirlinePackage;

//Importation of utilities
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.Book;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.sql.*;

public class Booked extends JFrame{
    //    global variables declaration
    JButton clearFlightbtn;
    JTextField txt1;
    private JTable bookedFlightsTable;
    private JScrollPane scrollPane;
    String username;

    //    login constructor method
    public Booked(String username) {
        this.username = username;
        //        Production of frame
        setUndecorated(true);
        setLayout(null);
        Image image = null;
        try {
            image = ImageIO.read(new File("src/AirlinePackage/9ce85e495a62999592932927729323bc.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //        Project Logo
        ImageIcon img = new ImageIcon("src/AirlinePackage/Logo1.png");
        JLabel imgLabel = new JLabel();
        imgLabel.setSize(300, 300);
        imgLabel.setLocation(100, 100);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imgLabel.setVerticalAlignment(JLabel.TOP);
        imgLabel.setIcon(img);
        add(imgLabel);

        //        motto msg
        JLabel mottoLabel = new JLabel("Fly with us, soar with confidence....");
        mottoLabel.setFont(new Font("Sans-serif", Font.ITALIC, 9));
        mottoLabel.setHorizontalAlignment(JLabel.CENTER);
        mottoLabel.setSize(300, 20);
        mottoLabel.setLocation(110, 250);
        add(mottoLabel);

        Font fnt = new Font("Garamond", Font.BOLD, 12);

        // Display username
        JLabel userLabel = new JLabel(username + ", your list of booked flights");
        userLabel.setFont(new Font("Sans-serif", Font.BOLD, 15));
        userLabel.setSize(300, 20);
        userLabel.setLocation(150, 270); // Adjust location as needed
        add(userLabel);

        // Cancel Label
        JLabel cancelLabel = new JLabel("Click on airline of choice under 'Airline' column to cancel flight.");
        cancelLabel.setFont(new Font("Sans-serif", Font.ITALIC, 12));
        cancelLabel.setSize(400, 20);
        cancelLabel.setLocation(100, 300); // Adjust location as needed
        add(cancelLabel);

        // Initialize components
        bookedFlightsTable = new JTable();
        scrollPane = new JScrollPane(bookedFlightsTable);
        scrollPane.setBounds(50,330,400,300);
        add(scrollPane);

//        Clear all flights
        clearFlightbtn = new JButton("Clear all Flights"); // Initialize bookbtn
        clearFlightbtn.setBounds(350, 650, 130, 30);
        Font bkfnt = new Font("Comic Sans MS", Font.BOLD, 12);
        clearFlightbtn.setFont(bkfnt);
        clearFlightbtn.setBackground(Color.BLUE);
        clearFlightbtn.setForeground(Color.WHITE);
        add(clearFlightbtn);
        clearFlightbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all flights?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    clearAllFlights();
                }
            }
        });

        //        Back button
        JButton backBtn = new JButton("Back to Home");
        backBtn.setSize(150, 30);
        backBtn.setLocation(330, 70);
        Font clfnt = new Font("Comic Sans MS", Font.BOLD, 13);
        backBtn.setFont(clfnt);
        backBtn.setBackground(Color.BLUE);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Home(username);
            }
        });
        this.add(backBtn);

        //        Jlabel for frame
        JLabel imglabel = new JLabel(new ImageIcon(image));
        imglabel.setSize(500,800);
        imglabel.setLocation(0, 0);

        add(imglabel);
        setSize(500,750);
        setLocationRelativeTo(null);
        setVisible(true);

        // Populate the table with booked flights
        populateBookedFlightsTable();
    }

    // Method to clear all flights for the current user
    private void clearAllFlights() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AirlineApp", "root", "")) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM bookings WHERE username = ?");
            ps.setString(1, username);
            ps.executeUpdate();

            // Clear the table in the UI
            DefaultTableModel tableModel = (DefaultTableModel) bookedFlightsTable.getModel();
            tableModel.setRowCount(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Define a method to handle flight cancellation
    private void cancelFlight(String airline) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AirlineApp", "root", "")) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM bookings WHERE username = ? AND airline = ?");
            ps.setString(1, username);
            ps.setString(2, airline);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void populateBookedFlightsTable() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost: 3306/AirlineApp", "root", "");

            // Create a statement
            Statement st = connection.createStatement();

            // Execute a query to retrieve booked flights for the specific user
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM bookings WHERE username = ?");
            ps.setString(1, this.username);
            ResultSet resultSet = ps.executeQuery();

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Passenger Name");
            tableModel.addColumn("Airline");
            tableModel.addColumn("Departure City");
            tableModel.addColumn("Destination City");
            tableModel.addColumn("Travel Date");
            tableModel.addColumn("Seats");

            // Add booked flights to the table model
            while (resultSet.next()) {
                String passengerName = resultSet.getString("username");
                String airline = resultSet.getString("airline");
                String departure = resultSet.getString("departure");
                String destination = resultSet.getString("destination");
                String travelDate = resultSet.getString("travelDate");
                String seats = resultSet.getString("seats");
                tableModel.addRow(new Object[]{passengerName, airline, departure, destination, travelDate, seats});
            }

            // Set the table model to the JTable
            bookedFlightsTable.setModel(tableModel);

            // Add a MouseListener to the JTable
            bookedFlightsTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int row = bookedFlightsTable.rowAtPoint(e.getPoint());
                    int col = bookedFlightsTable.columnAtPoint(e.getPoint());
                    if (col == 1) { // Assuming the Airline column is at index 1
                        String airline = (String)tableModel.getValueAt(row, col);
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to cancel the flight with " + airline + "?", "Warning", JOptionPane.YES_NO_OPTION);
                        if(dialogResult == JOptionPane.YES_OPTION){
                            // User clicked yes, perform the deletion
                            cancelFlight(airline);
                            // Remove the row from the table model
                            tableModel.removeRow(row);
                        }
                    }
                }
            });
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}