package AirlinePackage;

//Importation of utilities
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener  {
    JButton signupbtn;
    JLabel label7;
    JTextField txt1,txt2,txt3,txt4,txt5; // global text-field variable declaration
    JPasswordField txt6;
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
        setIconImage(img.getImage());
        add(imgLabel);

        //        motto msg
        JLabel mottoLabel = new JLabel("Fly with us, soar with confidence....");
        mottoLabel.setFont(new Font("Sans-serif", Font.ITALIC, 9));
        mottoLabel.setHorizontalAlignment(JLabel.CENTER);
        mottoLabel.setSize(300, 20);
        mottoLabel.setLocation(110, 250);
        add(mottoLabel);

        Font fnt = new Font("Garamond", Font.BOLD, 12);
        // Placeholder text color
        Color placeholderColor = Color.GRAY;

        txt1 = new JTextField(50);
        txt1.setFont(fnt);
        txt1.setBounds(200, 303, 150, 20);
        txt1.setForeground(placeholderColor);
        txt1.setText("John");
        txt1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt1.getText().equals("John")) {
                    txt1.setText("");
                    txt1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt1.getText().isEmpty()) {
                    txt1.setForeground(placeholderColor);
                    txt1.setText("John");
                }
            }
        });


        txt2 = new JTextField(50);
        txt2.setFont(fnt);
        txt2.setBounds(200, 350, 150, 20);
        txt2.setForeground(placeholderColor);
        txt2.setText("Doe");
        txt2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt2.getText().equals("Doe")) {
                    txt2.setText("");
                    txt2.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt2.getText().isEmpty()) {
                    txt2.setForeground(placeholderColor);
                    txt2.setText("Doe");
                }
            }
        });

        txt3 = new JTextField(50);
        txt3.setFont(fnt);
        txt3.setBounds(200, 450, 150, 20);
        txt3.setForeground(placeholderColor);
        txt3.setText("John@mail.com");
        txt3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt3.getText().equals("John@mail.com")) {
                    txt3.setText("");
                    txt3.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt3.getText().isEmpty()) {
                    txt3.setForeground(placeholderColor);
                    txt3.setText("John@mail.com");
                }
            }
        });

        txt4 = new JTextField(50);
        txt4.setFont(fnt);
        txt4.setBounds(200, 500, 150, 20);
        txt4.setForeground(placeholderColor);
        txt4.setText("+234 00-000-000");
        txt4.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt4.getText().equals("+234 00-000-000")) {
                    txt4.setText("");
                    txt4.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt4.getText().isEmpty()) {
                    txt4.setForeground(placeholderColor);
                    txt4.setText("+234 00-000-000");
                }
            }
        });


        txt5 = new JTextField(50);
        txt5.setFont(fnt);
        txt5.setBounds(200, 400, 150, 20);
        txt5.setForeground(placeholderColor);
        txt5.setText("John123");
        txt5.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt5.getText().equals("John123")) {
                    txt5.setText("");
                    txt5.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt5.getText().isEmpty()) {
                    txt5.setForeground(placeholderColor);
                    txt5.setText("John123");
                }
            }
        });

        txt6 = new JPasswordField(50);
        txt6.setFont(fnt);
        txt6.setBounds(200, 550, 150, 20);
        txt6.setForeground(placeholderColor);
        txt6.setText("*******");
        txt6.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt6.getText().equals("*******")) {
                    txt6.setText("");
                    txt6.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt6.getText().isEmpty()) {
                    txt6.setForeground(placeholderColor);
                    txt6.setText("*******");
                }
            }
        });
        showPsw = new JCheckBox("Show Password");
        showPsw.setBounds(200, 575, 150, 20);
        showPsw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPsw.isSelected()){
                    txt6.setEchoChar((char) 0);
                } else {
                    txt6.setEchoChar('*');
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

        JLabel label3 = new JLabel("Username: "); // Adding label to panel
        label3.setFont(font);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setSize(100, 20);
        label3.setLocation(100, 400);
        add(label3);
        add(txt3);


        JLabel label4 = new JLabel("Email: "); // Adding label to panel
        label4.setFont(font);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setSize(100, 20);
        label4.setLocation(100, 450);
        add(label4);
        add(txt4);

        JLabel label5 = new JLabel("Phone: "); // Adding label to panel
        label5.setFont(font);
        label5.setHorizontalAlignment(JLabel.CENTER);
        label5.setSize(100, 20);
        label5.setLocation(100, 500);
        add(label5);
        add(txt5);

        JLabel label6 = new JLabel("Password: "); // Adding label to panel
        label6.setFont(font);
        label6.setHorizontalAlignment(JLabel.CENTER);
        label6.setSize(100, 20);
        label6.setLocation(100, 550);
        add(label6);
        add(txt6);
        add(showPsw);

        label7 = new JLabel("Already have an account? Log in "); // Adding label to panel
        label7.setFont(font);
        label7.setHorizontalAlignment(JLabel.CENTER);
        label7.setSize(400, 20);
        label7.setLocation(50, 650);


//     Sigun-up button
       signupbtn = new JButton("Signup");
        signupbtn.setSize(300,30);
        signupbtn.setLocation(100,610);
        Font signfnt = new Font("Comic Sans MS", Font.BOLD, 20);
        signupbtn.setFont(signfnt);
        signupbtn.setBackground(Color.BLUE);
        signupbtn.setForeground(Color.WHITE);
        signupbtn.setFocusPainted(false);
        add(signupbtn);
        signupbtn.addActionListener(this);
        label7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Login();
            }
        });
        add(label7);// label7


//        Exit button
        JButton closeBtn = new JButton("Exit");
        closeBtn.setSize(80, 30);
        closeBtn.setLocation(400, 70);
        Font clfnt = new Font("Comic Sans MS", Font.BOLD, 13);
        closeBtn.setFont(clfnt);
        closeBtn.setBackground(Color.BLUE);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);
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
                txt4.getText().length() >=3 &&  txt5.getText().length() >=3 &&  txt6.getText().length() >=3){
            loadSql();
            try{
                String query =  "Insert into users values ('" + txt1.getText() + "', '" +  txt2.getText() + "', '" +
                        txt3.getText() + "', '"  + txt4.getText() + "', '" + txt5.getText() + "', '"+ txt6.getText() + "')";
                if (st.execute(query)){
                    JOptionPane.showMessageDialog(null, "An error occurred. Please try again.");
                }else {
                    JOptionPane.showMessageDialog(null, "Successfully inserted values");
                    dispose();
                    new Login();
                }

            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}

    private void loadSql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost: 3306/AirlineApp", "root", "");
            st = conn.createStatement();
            Retrieve_values();
        }catch (ClassNotFoundException e){
            System.err.println("Error finding class");
            e.printStackTrace();
        } catch (SQLException sqlerr){
            sqlerr.printStackTrace();
        }
    }

    // Data Retrieval
    public void Retrieve_values(){
        String select = "Select * from users";
        try {
            ResultSet rs = st.executeQuery(select);
            while (rs.next()){
                System.out.println("Firstname: " + rs.getString(1));
                System.out.println("Lastname: " + rs.getString(2));
                System.out.println("Username: " + rs.getString(3));
                System.out.println("Email: " + rs.getString(4));
                System.out.println("PhoneNo: " + rs.getString(5));
                System.out.println("Password: " + rs.getString(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}