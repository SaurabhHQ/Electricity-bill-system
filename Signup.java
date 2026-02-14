package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {

    Choice loginASCho;
    JTextField meterText, employerText, userNameText, nameText;
    JPasswordField passwordText;
    JButton create, back;

    JLabel meterNoLabel, employerLabel;

    Signup() {

        super("Signup Page");
        getContentPane().setBackground(new Color(168, 235, 255));
        setLayout(null);

        // Create Account As
        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        loginASCho = new Choice();
        loginASCho.add("Admin");
        loginASCho.add("Customer");
        loginASCho.setBounds(170,50,120,20);
        add(loginASCho);



        // Employer ID (Admin)
        employerLabel = new JLabel("Employer ID");
        employerLabel.setBounds(30, 100, 125, 20);
        add(employerLabel);

        employerText = new JTextField();
        employerText.setBounds(170, 100, 125, 20);
        add(employerText);

        // Meter Number (Customer)
        meterNoLabel = new JLabel("Meter Number");
        meterNoLabel.setBounds(30, 100, 125, 20);
        meterNoLabel.setVisible(false);
        add(meterNoLabel);

        meterText = new JTextField();
        meterText.setBounds(170, 100, 125, 20);
        meterText.setVisible(false);
        add(meterText);

        // Username
        JLabel userName = new JLabel("Username");
        userName.setBounds(30, 140, 125, 20);
        add(userName);

        userNameText = new JTextField();
        userNameText.setBounds(170, 140, 125, 20);
        add(userNameText);



        // Name
        JLabel name = new JLabel("Name");
        name.setBounds(30, 180, 125, 20);
        add(name);

        nameText = new JTextField();
        nameText.setBounds(170, 180, 125, 20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    Database c = new Database();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup  where meter_no = '"+meterText.getText()+"'");
                    if (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });


        // Password
        JLabel password = new JLabel("Password");
        password.setBounds(30, 220, 125, 20);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(170, 220, 125, 20);
        add(passwordText);

        // Choice Listener
        loginASCho.addItemListener(e -> {
            String user = loginASCho.getSelectedItem();

            if (user.equals("Customer")) {
                employerLabel.setVisible(false);
                nameText.setEditable(false);
                employerText.setVisible(false);
                meterNoLabel.setVisible(true);
                meterText.setVisible(true);
            } else {
                employerLabel.setVisible(true);
                employerText.setVisible(true);
                meterNoLabel.setVisible(false);
                meterText.setVisible(false);
            }
        });

        // Buttons
        create = new JButton("Create");
        create.setBounds(50, 285, 100, 25);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBounds(180, 285, 100, 25);
        back.addActionListener(this);
        add(back);

        // Image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(img));
        image.setBounds(320, 30, 250, 250);
        add(image);

        setSize(600, 380);
        setLocation(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == create) {
            String sloginAs = loginASCho.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();

           try{

               Database c = new Database();
               String query= null;

               if (loginASCho.equals("Admin")) {
                   query = "insert into Signup value('" + smeter + "', '" + susername + "', '" + sname + "','" + spassword + "','" + sloginAs + "')";
               }else {
                   query = "update Signup set username = '"+susername+"', password = '"+spassword+"', usertype = '"+sloginAs+"' where meter_no = '"+smeter+"'";
               }
               c.statement.executeUpdate(query);

              JOptionPane.showMessageDialog(null,"Account Created");
              setVisible(false);
              new Login();

           }catch (Exception E){
               E.printStackTrace();
           }
        }

        if (e.getSource() == back) {
            setVisible(false);
            new Login(); // make sure Login class exists
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
