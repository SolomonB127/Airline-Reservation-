package AirlinePackage;

//Importation of utilities
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class Signup extends JFrame {

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