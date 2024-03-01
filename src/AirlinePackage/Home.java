package AirlinePackage;

//Importation of utilities
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.sql.*;

public class Home extends JFrame{
    //    global variables declaration
    JButton bookbtn;
    JTextField txt1,txt2;
    private JComboBox<String> airlineBox, departureBox, destinationBox;
    private JSpinner seatsSpinner;
    private JTextArea infoArea;
    Connection connection;
    Statement st;

    //    login constructor method
    public Home(String username) {
        //        Production of frame
        createView(username);
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


        //        Jlabel for frame
        JLabel imglabel = new JLabel(new ImageIcon(image));
        imglabel.setSize(500,800);
        imglabel.setLocation(0, 0);

        add(imglabel);
        setSize(500,750);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createView(String username){
        //        Welcome msg
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Sans-serif", Font.BOLD, 14));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setSize(300, 20);
        welcomeLabel.setLocation(100, 250);
        add(welcomeLabel);

        //        motto msg
        JLabel mottoLabel = new JLabel("Fly with us, soar with confidence....");
        mottoLabel.setFont(new Font("Sans-serif", Font.ITALIC, 9));
        mottoLabel.setHorizontalAlignment(JLabel.CENTER);
        mottoLabel.setSize(300, 20);
        mottoLabel.setLocation(110, 270);
        add(mottoLabel);
        Font fnt = new Font("Garamond", Font.BOLD, 12);

        txt1 = new JTextField(50);
        txt1.setFont(fnt);
        txt1.setBounds(180, 353, 125, 20);

        JLabel label1 = new JLabel("Passenger Name: "); // Adding label to panel
        Font font = new Font("Sans-serif", Font.BOLD, 14);
        label1.setFont(font);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setSize(200, 20);
        label1.setLocation(20, 350);
        add(label1);
        add(txt1);

        airlineBox = new JComboBox<>();
        airlineBox.setBounds(360, 353, 120, 20);
        JLabel label2 = new JLabel("Airline: "); // Adding label to panel
        label2.setFont(font);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setSize(100, 20);
        label2.setLocation(285, 350);
        add(label2);
        add(airlineBox);

        departureBox = new JComboBox<>();
        departureBox.setBounds(180, 400, 125, 20);

        JLabel label3 = new JLabel("Departure City: "); // Adding label to panel
        label3.setFont(font);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setSize(200, 20);
        label3.setLocation(20, 400);
        add(label3);
        add(departureBox);

        destinationBox = new JComboBox<>();
        destinationBox.setFont(fnt);
        destinationBox.setBounds(180, 450, 125, 20);

        JLabel label4 = new JLabel("Destination City: "); // Adding label to panel
        label4.setFont(font);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setSize(200, 20);
        label4.setLocation(20, 450);
        add(label4);
        add(destinationBox);

        txt2 = new JTextField(50);
        txt2.setFont(fnt);
        txt2.setBounds(250, 500, 125, 20);

        JLabel label5 = new JLabel("Travel Date(YYY-MM-DD): "); // Adding label to panel
        label5.setFont(font);
        label5.setHorizontalAlignment(JLabel.CENTER);
        label5.setSize(200, 20);
        label5.setLocation(50, 500);
        add(label5);
        add(txt2);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1, 1, 50, 1);
        seatsSpinner = new JSpinner(spinnerNumberModel);
        seatsSpinner.setFont(fnt);
        seatsSpinner.setBounds(180, 550, 125, 20);

        JLabel label6 = new JLabel("Available Seats: "); // Adding label to panel
        label6.setFont(font);
        label6.setHorizontalAlignment(JLabel.CENTER);
        label6.setSize(200, 20);
        label6.setLocation(20, 550);
        add(label6);
        add(seatsSpinner);

        bookbtn = new JButton("Book Flight"); // Initialize bookbtn
        bookbtn.setBounds(100, 600, 125, 20);
        Font bkfnt = new Font("Comic Sans MS", Font.BOLD, 12);
        bookbtn.setFont(bkfnt);
        bookbtn.setBackground(Color.BLUE);
        bookbtn.setForeground(Color.WHITE);
        bookbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txt1.getText().toString();
                String airline = airlineBox.getSelectedItem().toString();
                String departure = departureBox.getSelectedItem().toString();
                String destination = destinationBox.getSelectedItem().toString();
                String date = txt2.getText().toString();
                int seats = (int) seatsSpinner.getValue();

                // Check if first name exists in the database
                try {
                    PreparedStatement stmt = connection.prepareStatement("SELECT Firstname FROM users WHERE Firstname = ?");
                    stmt.setString(1, name);
                    ResultSet resultSet = stmt.executeQuery();
                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Passenger with the name " + name + " does not exist in the database.");
                        return;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Check if necessary information is provided
                if (name.isEmpty() || date.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please provide the passenger name and travel date.");
                    return;
                }

                // Check if the selected flight has available seats
                try {
                    PreparedStatement stmt = connection.prepareStatement("SELECT available_seats FROM flights WHERE airline = ? AND departure_city = ? AND destination_city = ? AND date_of_travel = ?");
                    stmt.setString(1, airline);
                    stmt.setString(2, departure);
                    stmt.setString(3, destination);
                    stmt.setString(4, date);
                    ResultSet resultSet = stmt.executeQuery();
                    if (resultSet.next()) {
                        int availableSeats = resultSet.getInt("seats");
                        if (availableSeats == 0) {
                            JOptionPane.showMessageDialog(null, "Sorry, no available seats for the selected flight.");
                            return;
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Proceed with booking
                try {
                    // Load the JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Establish a connection
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/AirlineApp", "root", "");

                    // Create a statement
                    Statement st = connection.createStatement();

                    // Execute a query to store the booking details
                    String query = "INSERT INTO bookings (Firstname, airline, departure, destination, travelDate, seats) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, name);
                    pstmt.setString(2, airline);
                    pstmt.setString(3, departure);
                    pstmt.setString(4, destination);
                    pstmt.setString(5, date);
                    pstmt.setInt(6, seats);
                    pstmt.executeUpdate();

                    // Close the connection
                    connection.close();

                    JOptionPane.showMessageDialog(null, "Flight booked successfully for " + name + " on " + airline + " from " + departure + " to " + destination + " on " + date + " for " + seats + " passengers.");
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(bookbtn);

        infoArea = new JTextArea(5, 20);
        infoArea.setBounds(300, 700, 125, 20);
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        add(scrollPane);

        //        logout button
        JButton closeBtn = new JButton("Log-out");
        closeBtn.setSize(100, 30);
        closeBtn.setLocation(380, 70);
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
        populateComboBoxes();
    }

    // Update populateComboBoxes method to populate combo boxes with available options
    private void populateComboBoxes() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost/AirlineApp", "root", "");

            // Create a statement
            Statement st = connection.createStatement();

            // Execute a query to get airlines
            ResultSet resultSet = st.executeQuery("SELECT DISTINCT airline FROM flights");
            while (resultSet.next()) {
                airlineBox.addItem(resultSet.getString("airline"));
            }

            // Execute a query to get cities
            resultSet = st.executeQuery("SELECT DISTINCT departure_city FROM flights");
            while (resultSet.next()) {
                String departureCity = resultSet.getString("departure_city");
                departureBox.addItem(departureCity);
            }

            resultSet = st.executeQuery("SELECT DISTINCT destination_city FROM flights");
            while (resultSet.next()) {
                String destinationCity = resultSet.getString("destination_city");
                destinationBox.addItem(destinationCity);
            }

            // Execute a query to get travel dates
            resultSet = st.executeQuery("SELECT DISTINCT date_of_travel FROM flights");
            while (resultSet.next()) {
                String travelDate = resultSet.getString("date_of_travel");
                txt2.setText(travelDate);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}