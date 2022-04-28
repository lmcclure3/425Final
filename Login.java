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
    private JButton btnGuest;

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

        JLabel welcomeMessage = new JLabel("Welcome to Best Buy Online!");
        contentPane.add(welcomeMessage);
        welcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 42));
        welcomeMessage.setBounds(235, 100, 1000, 74);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test");
            }
        });
        btnCreateAccount = new JButton("Create Account");
        btnGuest = new JButton("Continue as Guest");

        
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnLogin.setBounds(180, 300, 259, 74);
        contentPane.add(btnLogin);

        btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnCreateAccount.setBounds(580, 300, 259, 74);
        btnCreateAccount.addActionListener(e -> {
            setContentPane(setSignUpPanel());
            pack();
        });
        contentPane.add(btnCreateAccount);

        btnGuest.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnGuest.setBounds(380, 420, 259, 74);
        contentPane.add(btnGuest);
    }

    private JPanel setSignUpPanel() {
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        signUpPanel.setLayout(null);
        signUpPanel.add(new JLabel("Test Sign Up"));

        JLabel usernameLabel = new JLabel("Username:");
        signUpPanel.add(usernameLabel);
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