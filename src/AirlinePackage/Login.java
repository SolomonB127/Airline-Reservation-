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

        Font fnt = new Font("Garamond", Font.BOLD, 12);

//        textFields
        txt1 = new JTextField(50);
        txt1.setFont(fnt);
        txt1.setBounds(200, 350, 150, 20);

        txt2 = new JPasswordField(50);
        txt2.setFont(fnt);
        txt2.setBounds(200, 450, 150, 20);

//        Labels for user info
        Font font = new Font("Sans-serif", Font.BOLD, 14);

        JLabel label1 = new JLabel("Email: "); // Adding label to panel
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
        
//     login button   
        loginbtn = new JButton("Log-in");
        loginbtn.setSize(300,30);
        loginbtn.setLocation(100,600);
        Font logfnt = new Font("Comic Sans MS", Font.BOLD, 20);
        loginbtn.setFont(logfnt);
        loginbtn.setBackground(Color.BLUE);
        loginbtn.setForeground(Color.WHITE);
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
        this.setDefaultCloseOperation(3);


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