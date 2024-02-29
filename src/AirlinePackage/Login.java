package AirlinePackage;

//Importation of utilities
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.sql.*;

public class Login  extends JFrame{
//    global variables declaration
    JButton loginbtn;
    JTextField txt1;
    JPasswordField txt2;
    JCheckBox showPsw;
    Connection conn;
    Statement st;
    
//    login constructor method
    public Login(){
        //        Production of frame
        setUndecorated(true);
        setLayout(null);
        Image image = null;
        try {
            image = ImageIO.read(new File("src/AirlinePackage/9ce85e495a62999592932927729323bc.jpg"));
        } catch (IOException e){
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
        mottoLabel.setLocation(110, 270);
        add(mottoLabel);

        Font fnt = new Font("Garamond", Font.BOLD, 12);

//        textFields
        txt1 = new JTextField(50);
        txt1.setFont(fnt);
        txt1.setBounds(200, 350, 150, 20);

        txt2 = new JPasswordField(50);
        txt2.setFont(fnt);
        txt2.setBounds(200, 450, 150, 20);
        showPsw = new JCheckBox("Show Password"); //show password
        showPsw.setBounds(200, 530, 150, 20);
        showPsw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPsw.isSelected()){
                    txt2.setEchoChar((char) 0);
                } else {
                    txt2.setEchoChar('*');
                }
            }
        });

//        Labels for user info
        Font font = new Font("Sans-serif", Font.BOLD, 14);

        JLabel label1 = new JLabel("Username: "); // Adding label to panel
        label1.setFont(font);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setSize(100, 20);
        label1.setLocation(100, 350);
        add(label1);
        add(txt1);


        JLabel label2 = new JLabel("Password: "); // Adding label to panel
        label2.setFont(font);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setSize(100, 20);
        label2.setLocation(100, 450);
        add(label2);
        add(txt2);
        add(showPsw);
        
//     login button   
        loginbtn = new JButton("Log-in");
        loginbtn.setSize(300,30);
        loginbtn.setLocation(100,600);
        Font logfnt = new Font("Comic Sans MS", Font.BOLD, 20);
        loginbtn.setFont(logfnt);
        loginbtn.setBackground(Color.BLUE);
        loginbtn.setForeground(Color.WHITE);
        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loginbtn) {
                    String Username = txt1.getText();
                    String Password = txt2.getText();

                    try {
                        // Establish database connection securely
                        conn = DriverManager.getConnection("jdbc:mysql://localhost: 3306/AirlineApp", "root", "");
                        st = conn.createStatement();

                        // Check login credentials using statement
                        String query = "SELECT * FROM users WHERE Username = '" + Username + "' AND Password = '" + Password + "'";

                        ResultSet rs = st.executeQuery(query);

                        if (rs.next()) {
                            // User found
                            JOptionPane.showMessageDialog(null, "Successfully logged in");
                            dispose();
//                            new Home(Username);
                        } else {
                            // User not found
                            JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error connecting to database: " + ex.getMessage());
                    }
                }
            }
        });



        add(loginbtn);

//        Exit button
        JButton closeBtn = new JButton("Exit");
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
            }
        });
        this.add(closeBtn);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //        Jlabel for frame
        JLabel imglabel = new JLabel(new ImageIcon(image));
        imglabel.setSize(500,800);
        imglabel.setLocation(0, 0);

        add(imglabel);
        setSize(500,750);
        setLocationRelativeTo(null);
        setVisible(true);
    }



}