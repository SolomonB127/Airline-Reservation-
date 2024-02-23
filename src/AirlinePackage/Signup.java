package AirlinePackage;

//Importation of utilities
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class Signup extends JFrame {
    JTextField txt1,txt2,txt3,txt4; // global text-field variable declaration
    JPasswordField txt5;

//    signup constructor method
    public Signup(){
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

        Font fnt = new Font("Comic Sans MS", Font.BOLD, 20);

        txt1 = new JTextField(50);
        txt1.setFont(fnt);
        txt1.setBounds(200, 303, 150, 20);


        txt2 = new JTextField(50);
        txt2.setFont(fnt);
        txt2.setBounds(200, 350, 150, 20);

        txt3 = new JTextField(50);
        txt3.setFont(fnt);
        txt3.setBounds(200, 400, 150, 20);

        txt4 = new JTextField(50);
        txt4.setFont(fnt);
        txt4.setBounds(200, 450, 150, 20);

        txt5 = new JPasswordField(50);
        txt5.setFont(fnt);
        txt5.setBounds(200, 500, 150, 20);

        JLabel label1 = new JLabel("FirstName: "); // Adding label to panel
        Font font = new Font("Sans-serif", Font.BOLD, 14);
        label1.setFont(font);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setSize(100, 20);
        label1.setLocation(100, 300);
        add(label1);
        add(txt1);

        JLabel label2 = new JLabel("LastName: "); // Adding label to panel
        label2.setFont(font);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setSize(100, 20);
        label2.setLocation(100, 350);
        add(label2);
        add(txt2);

        JLabel label3 = new JLabel("Email: "); // Adding label to panel
        label3.setFont(font);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setSize(100, 20);
        label3.setLocation(100, 400);
        add(label3);
        add(txt3);

        JLabel label4 = new JLabel("Phone: "); // Adding label to panel
        label4.setFont(font);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setSize(100, 20);
        label4.setLocation(100, 450);
        add(label4);
        add(txt4);

        JLabel label5 = new JLabel("Password: "); // Adding label to panel
        label5.setFont(font);
        label5.setHorizontalAlignment(JLabel.CENTER);
        label5.setSize(100, 20);
        label5.setLocation(100, 500);
        add(label5);
        add(txt5);

//     Sigun-up button
        JButton signupbtn = new JButton("Signup");
        signupbtn.setSize(300,30);
        signupbtn.setLocation(100,600);
        signupbtn.setFont(fnt);
        signupbtn.setBackground(Color.BLUE);
        signupbtn.setForeground(Color.WHITE);
        add(signupbtn);


//        Exit button
        JButton closeBtn = new JButton("Exit");
        closeBtn.setSize(80, 30);
        closeBtn.setLocation(400, 70);
        Font clfnt = new Font("Comic Sans MS", Font.BOLD, 13);
        closeBtn.setFont(clfnt);
        closeBtn.setBackground(Color.BLUE);
        closeBtn.setForeground(Color.WHITE);
        add(closeBtn);

//        Jlabel for frame
        JLabel imglabel = new JLabel(new ImageIcon(image));
        imglabel.setSize(500,800);
        imglabel.setLocation(0, 0);

        add(imglabel);
        setSize(500,750);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    public static void main(String[] args) {
        new Signup(); //method call
    }
}