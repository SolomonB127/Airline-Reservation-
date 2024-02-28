package AirlinePackage;

//Importation of utilities
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.sql.*;

public class Home extends JFrame{
    //    global variables declaration
    JButton loginbtn;
    JTextField txt1,txt2,txt3,txt4; // global text-field variable declaration
    Connection conn;
    Statement st;

    //    login constructor method
    public Home() {
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

        Font fnt = new Font("Garamond", Font.BOLD, 12);

        txt1 = new JTextField(50);
        txt1.setFont(fnt);
        txt1.setBounds(280, 303, 150, 20);


        txt2 = new JTextField(50);
        txt2.setFont(fnt);
        txt2.setBounds(280, 350, 150, 20);

        txt3 = new JTextField(50);
        txt3.setFont(fnt);
        txt3.setBounds(280, 403, 150, 20);

        txt4 = new JTextField(50);
        txt4.setFont(fnt);
        txt4.setBounds(280, 450, 150, 20);


        JLabel label1 = new JLabel("Departure City: "); // Adding label to panel
        Font font = new Font("Sans-serif", Font.BOLD, 14);
        label1.setFont(font);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setSize(150, 20);
        label1.setLocation(49, 300);
        add(label1);
        add(txt1);

        JLabel label2 = new JLabel("Destination City: "); // Adding label to panel
        label2.setFont(font);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setSize(150, 20);
        label2.setLocation(54, 350);
        add(label2);
        add(txt2);

        JLabel label3 = new JLabel("Date of Travel(YYYY-MM-DD: "); // Adding label to panel
        label3.setFont(font);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setSize(250, 20);
        label3.setLocation(47, 400);
        add(label3);
        add(txt3);


        JLabel label4 = new JLabel("Number of Passengers: "); // Adding label to panel
        label4.setFont(font);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setSize(250, 20);
        label4.setLocation(28, 450);
        add(label4);
        add(txt4);

        //       Log-out button
        JButton closeBtn = new JButton("Log-out");
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



        //        Jlabel for frame
        JLabel imglabel = new JLabel(new ImageIcon(image));
        imglabel.setSize(500,800);
        imglabel.setLocation(0, 0);

        add(imglabel);
        setSize(500,750);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args){
        new Home();
    }

}