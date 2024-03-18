package AirlinePackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class RetrievePassword extends JFrame {

    Connection conn;

    Statement st;

    JButton backBtn, resetBtn;

    JCheckBox showPsw;

    JTextField usernameField;

    JPasswordField passwordField, confirmPasswd;

    public RetrievePassword() {

        setUndecorated(true);
        setLayout(null);
        Image image = null;
        try {
            image = ImageIO.read(new FileInputStream("src/AirlinePackage/9ce85e495a62999592932927729323bc.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon img = new ImageIcon("src/AirlinePackage/Logo1.png");
        JLabel imgLabel = new JLabel();
        imgLabel.setSize(300, 300);
        imgLabel.setLocation(100, 100);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imgLabel.setVerticalAlignment(JLabel.TOP);
        setIconImage(img.getImage());
        imgLabel.setIcon(img);
        add(imgLabel);

        Font clfnt = new Font("Comic Sans MS", Font.BOLD, 13);


        backBtn = new JButton("Back");
        backBtn.setSize(80, 30);
        backBtn.setLocation(30, 70);
        backBtn.setFont(clfnt);
        backBtn.setBackground(Color.BLUE);
        backBtn.setForeground(Color.WHITE);

        add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new Signup().setVisible(true);
            }
        });

        Font font = new Font("Sans-serif", Font.BOLD, 14);

        usernameField = new JTextField(50);
        usernameField.setFont(font);
        usernameField.setBounds(210, 300, 150, 20);

        passwordField = new JPasswordField(50);
        passwordField.setFont(font);
        passwordField.setBounds(210, 370, 150, 20);

        confirmPasswd = new JPasswordField(50);
        confirmPasswd.setFont(font);
        confirmPasswd.setBounds(210, 450, 150, 20);

        showPsw = new JCheckBox("Show Password"); //show password
        showPsw.setBounds(200, 500, 150, 20);
        showPsw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPsw.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    confirmPasswd.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                    confirmPasswd.setEchoChar('*');
                }
            }
        });


        JLabel label1 = new JLabel("Username: "); // Adding label to panel
        label1.setFont(font);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setSize(100, 20);
        label1.setLocation(80, 300);


        JLabel label2 = new JLabel("New- Password: "); // Adding label to panel
        label2.setFont(font);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setSize(150, 20);
        label2.setLocation(45, 370);

        JLabel label3 = new JLabel("Confirm Password: "); // Adding label to panel
        label3.setFont(font);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setSize(150, 20);
        label3.setLocation(48, 450);


        add(label1);
        add(label2);
        add(label3);
        add(usernameField);
        add(passwordField);
        add(confirmPasswd);
        add(showPsw);


        resetBtn = new JButton("Reset Password");
        resetBtn.setSize(300, 30);
        resetBtn.setLocation(100, 600);
        Font logfnt = new Font("Comic Sans MS", Font.BOLD, 20);
        resetBtn.setFont(logfnt);
        resetBtn.setBackground(Color.BLUE);
        resetBtn.setForeground(Color.WHITE);


        resetBtn.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String newPassword = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswd.getPassword());

                // Check if username exists in database
                if (isUserPresent(username) && newPassword.equals(confirmPassword)) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the password for " + username + "?", "Confirm Password Reset", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Update password in database if confirmed
                        if (updatePassword(username, newPassword)) {
                            JOptionPane.showMessageDialog(null, "Password reset successfully!");
                            // Clear username and password fields after successful reset
                            usernameField.setText("");
                            passwordField.setText("");
                            confirmPasswd.setText("");
                            dispose();
                            new Login();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to reset password. Please try again.");
                        }
                    }
                } else {
                    if (!isUserPresent(username)) {
                        JOptionPane.showInternalMessageDialog(null, "Username not found. Please try again");
                    } else if (!newPassword.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(null,"Passwords do not Match");
                    }
                }
            }
        });



        add(resetBtn);


        JLabel imglabel = new JLabel(new ImageIcon(image));
        imglabel.setSize(500, 800);
        imglabel.setLocation(0, 0);

        add(imglabel);
        setSize(500, 750);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private boolean isUserPresent(String username) {
        boolean userExists = false;
        try {
            // Replace with your actual database connection details
            String dbURL = "jdbc:mysql://localhost:3306/AirlineApp";
            String user = "root";
            String password = "";

            conn = DriverManager.getConnection(dbURL, user, password);
            st = conn.createStatement();

            String query = "SELECT username FROM users WHERE username = '" + username + "'";
            ResultSet rs = st.executeQuery(query);

            // Check if any rows are returned (meaning username exists)
            userExists = rs.next();

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userExists;
    }

    private boolean updatePassword(String username, String newPassword) {
        boolean passwordUpdated = false;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/AirlineApp";
            String user = "root";
            String password = "";

            conn = DriverManager.getConnection(dbURL, user, password);
            st = conn.createStatement();

            // Use PreparedStatement to prevent SQL injection
            String query = "UPDATE users SET password = ? WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setString(2, username);

            int rowsUpdated = ps.executeUpdate();
            passwordUpdated = rowsUpdated > 0; // Check if at least one row was updated

            ps.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return passwordUpdated;
    }

}
