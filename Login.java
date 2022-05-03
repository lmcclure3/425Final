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

    private static final Font CLASS_FONT = new Font("Tahoma", Font.PLAIN, 20);

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
        btnCreateAccount = new JButton("Create Account");
        btnGuest = new JButton("Continue as Guest");
        JButton btnInfo = new JButton("Account Info");

        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnLogin.setBounds(180, 300, 259, 74);
        btnLogin.addActionListener(e -> {
            setContentPane(loginPanel());
        });
        contentPane.add(btnLogin);

        btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnCreateAccount.setBounds(580, 300, 259, 74);
        btnCreateAccount.addActionListener(e -> {
            setContentPane(setSignUpPanel());
        });
        contentPane.add(btnCreateAccount);

        btnGuest.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnGuest.setBounds(180, 420, 259, 74);
        contentPane.add(btnGuest);

        btnInfo.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnInfo.setBounds(580, 420, 259, 74);
        btnInfo.addActionListener(e -> {
            setContentPane(acctInfoPanel());
        });
        contentPane.add(btnInfo);
    }

    //todo center
    private JPanel setSignUpPanel() {
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        signUpPanel.setBounds(new Rectangle(1014, 597));
        signUpPanel.setLayout(null);

        JLabel signUpLabel = new JLabel("Sign Up for a Best Buy Account");
        signUpLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        signUpLabel.setBounds(320, 0, 1000, 75);
        signUpPanel.add(signUpLabel);

        // email address = username
        JLabel usernameLabel = new JLabel("Email Address:");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        usernameLabel.setBounds(555, 100, 1000, 30);
        signUpPanel.add(usernameLabel);
        TextField username = new TextField();
        username.setBounds(555, 140, 210, 30);
        username.setFont(new Font("Tahoma", Font.PLAIN, 20));
        signUpPanel.add(username);

        // name
        JLabel nameLabel = new JLabel("First/Last:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameLabel.setBounds(335, 100, 1000, 30);
        signUpPanel.add(nameLabel);
        TextField name = new TextField();
        name.setBounds(335, 140, 210, 30);
        name.setFont(new Font("Tahoma", Font.PLAIN, 20));
        signUpPanel.add(name);

        // password
        JLabel pwLabel = new JLabel("Password:");
        pwLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pwLabel.setBounds(335, 200, 1000, 30);
        signUpPanel.add(pwLabel);
        JPasswordField pw1 = new JPasswordField(20);
        pw1.setBounds(335, 240, 210, 30);
        pw1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        signUpPanel.add(pw1);

        // confirm password
        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        confirmLabel.setBounds(555, 200, 1000, 30);
        signUpPanel.add(confirmLabel);
        JPasswordField pw2 = new JPasswordField(20);
        pw2.setBounds(555, 240, 210, 30);
        pw2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        signUpPanel.add(pw2);

        // phone number
        JLabel mobileLabel = new JLabel("Mobile Number");
        mobileLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mobileLabel.setBounds(335, 300, 1000, 30);
        signUpPanel.add(mobileLabel);
        TextField mobileField = new TextField();
        mobileField.setBounds(335, 340, 210, 30);
        mobileField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        signUpPanel.add(mobileField);

        signUpPanel.add(new JLabel());
        JButton button = new JButton("Add Credit Card");
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBounds(420, 420, 259, 74);
        button.addActionListener(e -> {
            System.out.println("Poggers!");
            System.out.println(username.getText());
            System.out.println(pw1.getText());
            System.out.println(pw2.getText());

            if (!username.getText().endsWith(".com") && !username.getText().contains("@")) {
                signUpLabel.setText("Error: Invalid Email!");
            } else if (!pw1.getText().equals(pw2.getText())) {
                signUpLabel.setText("Error: Passwords do not match!");
            } else {
                // make this go to the product page when it is done!
                setContentPane(addCreditCardPanel());
            }
        });
        signUpPanel.add(button);

        return signUpPanel;
    }

    // login panel
    private JPanel loginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        loginPanel.setBounds(new Rectangle(1014, 597));
        loginPanel.setLayout(null);

        JLabel loginLabel = new JLabel("Please enter login info:");
        loginLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        loginLabel.setBounds(320, 0, 1000, 75);
        loginPanel.add(loginLabel);

        // email address = username
        JLabel usernameLabel = new JLabel("Email Address:");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        usernameLabel.setBounds(335, 100, 1000, 30);
        loginPanel.add(usernameLabel);
        TextField username = new TextField();
        username.setBounds(335, 140, 420, 30);
        username.setFont(new Font("Tahoma", Font.PLAIN, 20));
        loginPanel.add(username);

        // password
        JLabel pwLabel = new JLabel("Password:");
        pwLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pwLabel.setBounds(335, 200, 1000, 30);
        loginPanel.add(pwLabel);
        JPasswordField pw1 = new JPasswordField(20);
        pw1.setBounds(335, 240, 420, 30);
        pw1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        loginPanel.add(pw1);

        JButton button = new JButton("Login");
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBounds(420, 340, 259, 74);
        button.addActionListener(e -> {
            System.out.println("Poggers!");
            System.out.println(username.getText());
            System.out.println(pw1.getText());

            if (!username.getText().endsWith(".com") && !username.getText().contains("@")) {
                loginLabel.setText("Error: Invalid Email!");
            } else {
                // make this go to the product page when it is done!
                setContentPane(contentPane);
            }
        });
        loginPanel.add(button);

        return loginPanel;
    }

    // add card panel
    private JPanel addCreditCardPanel() {
        JPanel ccPanel = new JPanel();
        ccPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        ccPanel.setBounds(new Rectangle(1014, 597));
        ccPanel.setLayout(null);

        JLabel addCcLabel = new JLabel("Add a Credit Card:");
        addCcLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        addCcLabel.setBounds(320, 0, 1000, 75);
        ccPanel.add(addCcLabel);

        // credit card number
        JLabel numberLabel = new JLabel("Credit Card Number:");
        numberLabel.setBounds(335, 100, 1000, 30);
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(numberLabel);
        TextField ccNumber = new TextField();
        ccNumber.setBounds(335, 140, 420, 30);
        ccNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(ccNumber);

        // expiration date
        JLabel expLabel = new JLabel("Expiration Date:");
        expLabel.setBounds(335, 200, 420, 30);
        expLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(expLabel);
        TextField expDate = new TextField();
        expDate.setBounds(335, 240, 210, 30);
        expDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(expDate);

        // security code
        JLabel secCodeLabel = new JLabel("Security Code:");
        secCodeLabel.setBounds(555, 200, 420, 30);
        secCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(secCodeLabel);
        TextField secCode = new TextField();
        secCode.setBounds(555, 240, 210, 30);
        secCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(secCode);

        JButton button = new JButton("Finish Sign Up");
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBounds(420, 340, 259, 74);
        button.addActionListener(e -> {
            System.out.println("Poggers!");
            setContentPane(contentPane);
        });
        ccPanel.add(button);

        return ccPanel;
    }

    // credit card panel for guest checkouts
    private JPanel addCreditCardPanelGuest() {
        JPanel ccPanel = new JPanel();
        ccPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        ccPanel.setBounds(new Rectangle(1014, 597));
        ccPanel.setLayout(null);

        JLabel addCcLabel = new JLabel("Add a Credit Card:");
        addCcLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        addCcLabel.setBounds(320, 0, 1000, 75);
        ccPanel.add(addCcLabel);

        // credit card number
        JLabel numberLabel = new JLabel("Credit Card Number:");
        numberLabel.setBounds(335, 100, 1000, 30);
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(numberLabel);
        TextField ccNumber = new TextField();
        ccNumber.setBounds(335, 140, 420, 30);
        ccNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(ccNumber);

        // expiration date
        JLabel expLabel = new JLabel("Expiration Date:");
        expLabel.setBounds(335, 200, 420, 30);
        expLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(expLabel);
        TextField expDate = new TextField();
        expDate.setBounds(335, 240, 210, 30);
        expDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(expDate);

        // security code
        JLabel secCodeLabel = new JLabel("Security Code:");
        secCodeLabel.setBounds(555, 200, 420, 30);
        secCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(secCodeLabel);
        TextField secCode = new TextField();
        secCode.setBounds(555, 240, 210, 30);
        secCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccPanel.add(secCode);

        JButton button = new JButton("Finish Check Out");
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBounds(420, 340, 259, 74);
        button.addActionListener(e -> {
            System.out.println("Poggers!");
            setContentPane(contentPane);
        });
        ccPanel.add(button);

        return ccPanel;
    }

    //todo major updates needed here, pull acct info from db
    private JPanel acctInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        infoPanel.setBounds(new Rectangle(1014, 597));
        infoPanel.setLayout(null);

        // header
        JLabel headerLabel = new JLabel("Account Information:");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        headerLabel.setBounds(320, 0, 1000, 75);
        infoPanel.add(headerLabel);

        // email
        JLabel emailLabel = new JLabel("Email: " + "PLACEHOLDER");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        emailLabel.setBounds(335, 100, 1000, 30);
        infoPanel.add(emailLabel);

        // customer name
        JLabel nameLabel = new JLabel("Name: " + "PLACEHOLDER");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameLabel.setBounds(335, 140, 1000, 30);
        infoPanel.add(nameLabel);

        // mobile number
        JLabel mobileNumberLabel = new JLabel("Mobile Number: " + "PLACEHOLDER");
        mobileNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mobileNumberLabel.setBounds(335, 180, 1000, 30);
        infoPanel.add(mobileNumberLabel);

        // go back to menu
        JButton exitButton = new JButton("Return to Menu");
        exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        exitButton.setBounds(335, 240, 200, 74);
        exitButton.addActionListener(e -> {
            System.out.println("Poggers!");
            setContentPane(contentPane);
        });
        infoPanel.add(exitButton);

        // delete acct
        JButton deleteButton = new JButton("Delete Account");
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deleteButton.setBounds(555, 240, 200, 74);
        deleteButton.addActionListener(e -> {
            System.out.println("PLACEHOLDER - Delete Logic Here");
        });
        infoPanel.add(deleteButton);

        return infoPanel;
    }

}