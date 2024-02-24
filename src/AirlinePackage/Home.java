package AirlinePackage;

// Importation of utilities
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.sql.*;

public class Home extends JFrame {
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
        JLabel welcomeLabel = new JLabel("Hi, " + username + ". Welcome");
        welcomeLabel.setSize(300, 50); // Set appropriate size
        welcomeLabel.setLocation(100, 400); // Set appropriate location
        add(welcomeLabel);

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
