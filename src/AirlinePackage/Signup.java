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

public class Signup extends JFrame implements ActionListener  {
    JButton signupbtn;
    JLabel label6;
    JTextField txt1,txt2,txt3,txt4; // global text-field variable declaration
    JPasswordField txt5;
    JCheckBox showPsw;
    Connection conn;
    Statement st;

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
        showPsw = new JCheckBox("Show Password");
        showPsw.setBounds(200, 530, 150, 20);
        showPsw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPsw.isSelected()){
                    txt5.setEchoChar((char) 0);
                } else {
                    txt5.setEchoChar('*');
                }
            }
        });

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
        add(showPsw);

        label6 = new JLabel("Already have an account? Log in "); // Adding label to panel
        label6.setFont(font);
        label6.setHorizontalAlignment(JLabel.CENTER);
        label6.setSize(400, 20);
        label6.setLocation(50, 650);


//     Sigun-up button
       signupbtn = new JButton("Signup");
        signupbtn.setSize(300,30);
        signupbtn.setLocation(100,600);
        signupbtn.setFont(fnt);
        signupbtn.setBackground(Color.BLUE);
        signupbtn.setForeground(Color.WHITE);
        add(signupbtn);
        signupbtn.addActionListener(this);
        label6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Login();
            }
        });
        add(label6);// label6


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

    public static void main(String[] args) {
        new Signup(); //method call
    }



//    SQL Server connection
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked");
        if (!txt1.getText().isEmpty() && !txt2.getText().isEmpty() && !txt3.getText().isEmpty() &&
                !txt4.getText().isEmpty() && !txt5.getText().isEmpty()){
            System.out.println("All fields are filled");
            if (txt1.getText().length() >= 3 && txt2.getText().length() >=3 &&  txt3.getText().length() >=3 &&
                    txt4.getText().length() >=3 &&  txt5.getText().length() >=3){
                loadSql();
                try{
                    System.out.println("Insert into signup values (' " + txt1.getText() + " ' ,  ' " +  txt2.getText() + " ' ,  ' " +
                            txt3.getText() + " ' ,  ' "  + txt4.getText() + " ' ,  ' " + txt5.getText() + "')"
                    ) ;
                    String query =  "Insert into signup values (' " + txt1.getText() + " ' ,  ' " +  txt2.getText() + " ' ,  ' " +
                            txt3.getText() + " ' ,  ' "  + txt4.getText() + " ' ,  ' " + txt5.getText() + "')";
                    st.execute(query);
                    System.out.println();
                    JOptionPane.showMessageDialog(null, "Successfully inserted values");
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private void loadSql() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch (ClassNotFoundException e){
                System.err.println("Error finding class");
            }
            conn = DriverManager.getConnection("jdbc:mysql://localhost: 3306" + "/AirlineApp", "root", "");
            st = conn.createStatement();

           Retrieve_values();

        }catch (SQLException sqlerr){

        }
    }

//    Data Retrieval
public void Retrieve_values(){
    String select = "Select * from users";
    ResultSet rs;
    try {
        rs = st.executeQuery(select);
        while (rs.next()){
            System.out.println("Firstname: " + rs.getString(1));
            System.out.println("Lastname: " + rs.getString(2));
            System.out.println("Email: " + rs.getString(3));
            System.out.println("PhoneNo: " + rs.getString(4));
            System.out.println("Password: " + rs.getString(5));
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
}
}