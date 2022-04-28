import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;

/**
 * User Registration using Swing
 * @author javaguides.net
 *
 */
public class Login extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
    private JTextField username;
    private JTextField mob;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton btnLogin;
    private JButton btnCreateAccount;
    private JButton btnskip;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public Login() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1014, 597);
        setResizable(false);
        setTitle("This is Best Buy Sir");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        contentPane.add(send);
        contentPane.add(reset);
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test");
            }
        });
        btnCreateAccount = new JButton("CreateAccount");
        btnskip = new JButton("skip");
        btnNewButton = new JButton("Register");
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstname.getText();
                String lastName = lastname.getText();
                String emailId = email.getText();
                String userName = username.getText();
                String mobileNumber = mob.getText();
                int len = mobileNumber.length();
                String password = passwordField.getText(); 

                String msg = "" + firstName;
                msg += " \n";
                if (len != 10) {
                    JOptionPane.showMessageDialog(btnNewButton, "Enter a valid mobile number");
                }

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "root");

                    String query = "INSERT INTO account values('" + firstName + "','" + lastName + "','" + userName + "','" +
                        password + "','" + emailId + "','" + mobileNumber + "')";

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Welcome, " + msg + "Your account is sucessfully created");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(399, 447, 259, 74);
        contentPane.add(btnNewButton);

        
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnLogin.setBounds(0, 447, 259, 74);
        contentPane.add(btnLogin);

        btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnCreateAccount.setBounds(399, 350, 259, 74);
        btnCreateAccount.addActionListener(e -> {
            setContentPane(setSignUpPanel());
            pack();
        });
        contentPane.add(btnCreateAccount);

        btnskip.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnskip.setBounds(399, 200, 259, 74);
        contentPane.add(btnskip);
    }

    private JPanel setSignUpPanel() {
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        signUpPanel.setLayout(new GridLayout(0, 1));
        signUpPanel.add(new JLabel("Test Sign Up"));

        signUpPanel.add(new JLabel("Username:"));
        TextField username = new TextField(20);
        signUpPanel.add(username);

        signUpPanel.add(new JLabel("Password:"));
        JPasswordField pw1 = new JPasswordField(20);
        signUpPanel.add(pw1);

        signUpPanel.add(new JLabel("Confirm Password:"));
        JPasswordField pw2 = new JPasswordField(20);
        signUpPanel.add(pw2);

        signUpPanel.add(new JLabel());
        JButton button = new JButton("Create Account");
        button.addActionListener(e -> {
            System.out.println("Poggers!");
            System.out.println(username.getText());
            System.out.println(pw1.getText());
            System.out.println(pw2.getText());
        });
        signUpPanel.add(button);

        return signUpPanel;
    }

}