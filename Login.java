import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.imageio.ImageIO;
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
import java.util.*;
import java.util.List;


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

    private List<String> shoppingCart;
    private boolean guest;

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
        guest = true;

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
        btnGuest.addActionListener(e -> {
            try {
                guest = true;
                setContentPane(catalogPanel());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
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
                guest = false;
                try {
                    setContentPane(catalogPanel());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
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

    // catalog
    private JPanel catalogPanel() throws IOException {
        JPanel catalogPanel = new JPanel();
        catalogPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        catalogPanel.setBounds(new Rectangle(1014, 800));
        catalogPanel.setLayout(null);
        shoppingCart = new ArrayList<>();

        // catalog header
        JLabel headerLabel = new JLabel("Best Buy Catalog:");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        headerLabel.setBounds(370, -10, 1000, 75);
        catalogPanel.add(headerLabel);

        // stuff for chair
        BufferedImage imgChair = ImageIO.read(new File("devokochair22.jpg"));
        JLabel imgChair2 = new JLabel(new ImageIcon(imgChair));
        imgChair2.setBounds(200, 50, 100, 171);
        JLabel chairDeets = new JLabel("Gaming Chair");
        chairDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chairDeets.setBounds(200, 200, 1000, 75);
        JLabel chairPrice = new JLabel("Price: €420.69"); //todo ADD QUERY to UPDATE PRICE
        chairPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chairPrice.setBounds(200, 215, 1000, 75);
        JButton chairAdd = new JButton("Add to Cart");
        chairAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chairAdd.setBounds(200, 280, 120, 30);
        chairAdd.addActionListener(e -> {
            shoppingCart.add("Chair");
        });
        catalogPanel.add(imgChair2);
        catalogPanel.add(chairDeets);
        catalogPanel.add(chairPrice);
        catalogPanel.add(chairAdd);

        // stuff for keyboard
        BufferedImage imgKeyboard = ImageIO.read(new File("lolkeyboard2.jpg"));
        JLabel imgKeyboard2 = new JLabel(new ImageIcon(imgKeyboard));
        imgKeyboard2.setBounds(200, 300, 171, 171);
        JLabel keyboardDeets = new JLabel("League of Legends Keyboard");
        keyboardDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        keyboardDeets.setBounds(200, 440, 1000, 75);
        JLabel keyboardPrice = new JLabel("Price: €600"); //todo ADD QUERY to UPDATE PRICE
        keyboardPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        keyboardPrice.setBounds(200, 455, 1000, 75);
        JButton keyboardAdd = new JButton("Add to Cart");
        keyboardAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        keyboardAdd.setBounds(200, 515, 120, 30);
        keyboardAdd.addActionListener(e -> {
            shoppingCart.add("Keyboard");
        });
        catalogPanel.add(imgKeyboard2);
        catalogPanel.add(keyboardDeets);
        catalogPanel.add(keyboardPrice);
        catalogPanel.add(keyboardAdd);

        // stuff for microphone
        BufferedImage imgMicro = ImageIO.read(new File("solocast2.jpg"));
        JLabel imgMicro2 = new JLabel(new ImageIcon(imgMicro));
        imgMicro2.setBounds(450, 50, 100, 171);
        JLabel microDeets = new JLabel("Microphone");
        microDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        microDeets.setBounds(450, 200, 1000, 75);
        JLabel microPrice = new JLabel("Price: €420.69"); //todo ADD QUERY to UPDATE PRICE
        microPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        microPrice.setBounds(450, 215, 1000, 75);
        JButton microAdd = new JButton("Add to Cart");
        microAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        microAdd.setBounds(450, 280, 120, 30);
        microAdd.addActionListener(e -> {
            shoppingCart.add("Microphone");
        });
        catalogPanel.add(imgMicro2);
        catalogPanel.add(microDeets);
        catalogPanel.add(microPrice);
        catalogPanel.add(microAdd);

        // stuff for headset
        BufferedImage imgHeadset = ImageIO.read(new File("razerkraken2.jpg"));
        JLabel imgHeadset2 = new JLabel(new ImageIcon(imgHeadset));
        imgHeadset2.setBounds(450, 300, 171, 171);
        JLabel headsetDeets = new JLabel("Headset");
        headsetDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        headsetDeets.setBounds(450, 440, 1000, 75);
        JLabel headsetPrice = new JLabel("Price: €420.69"); //todo ADD QUERY to UPDATE PRICE
        headsetPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        headsetPrice.setBounds(450, 455, 1000, 75);
        JButton headsetAdd = new JButton("Add to Cart");
        headsetAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        headsetAdd.setBounds(450, 515, 120, 30);
        headsetAdd.addActionListener(e -> {
            shoppingCart.add("Headset");
        });
        catalogPanel.add(imgHeadset2);
        catalogPanel.add(headsetDeets);
        catalogPanel.add(headsetPrice);
        catalogPanel.add(headsetAdd);

        // stuff for mouse
        BufferedImage imgMouse = ImageIO.read(new File("gpro2.jpg"));
        JLabel imgMouse2 = new JLabel(new ImageIcon(imgMouse));
        imgMouse2.setBounds(700, 50, 171, 171);
        JLabel mouseDeets = new JLabel("Gaming Mouse");
        mouseDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        mouseDeets.setBounds(700, 200, 1000, 75);
        JLabel mousePrice = new JLabel("Price: €420.69"); //todo ADD QUERY to UPDATE PRICE
        mousePrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        mousePrice.setBounds(700, 215, 1000, 75);
        JButton mouseAdd = new JButton("Add to Cart");
        mouseAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        mouseAdd.setBounds(700, 280, 120, 30);
        mouseAdd.addActionListener(e -> {
            shoppingCart.add("Mouse");
        });
        catalogPanel.add(imgMouse2);
        catalogPanel.add(mouseDeets);
        catalogPanel.add(mousePrice);
        catalogPanel.add(mouseAdd);

        // stuff for printer
        BufferedImage imgPrinter = ImageIO.read(new File("printer2.jpg"));
        JLabel imgPrinter2 = new JLabel(new ImageIcon(imgPrinter));
        imgPrinter2.setBounds(700, 300, 171, 171);
        JLabel printerDeets = new JLabel("Printer");
        printerDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        printerDeets.setBounds(700, 440, 1000, 75);
        JLabel printerPrice = new JLabel("Price: €600"); //todo ADD QUERY to UPDATE PRICE
        printerPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        printerPrice.setBounds(700, 455, 1000, 75);
        JButton printerAdd = new JButton("Add to Cart");
        printerAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        printerAdd.setBounds(700, 515, 120, 30);
        printerAdd.addActionListener(e -> {
            shoppingCart.add("Printer");
        });
        catalogPanel.add(imgPrinter2);
        catalogPanel.add(printerDeets);
        catalogPanel.add(printerPrice);
        catalogPanel.add(printerAdd);

        JButton checkout = new JButton("Checkout");
        checkout.setFont(new Font("Tahoma", Font.PLAIN, 15));
        checkout.setBounds(850, 515, 120, 30);
        checkout.addActionListener(e -> {
            System.out.println(shoppingCart);
            setContentPane(showCart());
        });
        catalogPanel.add(checkout);

        return catalogPanel;
    }

    private JPanel showCart() {
        JPanel cartPanel = new JPanel();
        cartPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        cartPanel.setBounds(new Rectangle(1014, 800));
        cartPanel.setLayout(null);

        // cart header
        JLabel headerLabel = new JLabel("Your Cart:");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        headerLabel.setBounds(370, -10, 1000, 75);
        cartPanel.add(headerLabel);

        // display items in cart
        JLabel itemsLabel = new JLabel(shoppingCart.toString());
        itemsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        itemsLabel.setBounds(370, 100, 1000, 75);
        cartPanel.add(itemsLabel);

        // display total prices
        // todo make this get prices
        double total = 0;
        for (int i = 0; i < shoppingCart.size(); i++) {
            total += 420.69;
        }
        JLabel totalLabel = new JLabel("Total Cost: " + total);
        totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        totalLabel.setBounds(370, 150, 1000, 75);
        cartPanel.add(totalLabel);

        JButton checkout = new JButton("Checkout");
        checkout.setFont(new Font("Tahoma", Font.PLAIN, 20));
        checkout.setBounds(370, 300, 250, 75);
        checkout.addActionListener(e -> {
            if (guest) {
                setContentPane(addCreditCardPanelGuest());
            } else {
                setContentPane(contentPane);
            }
        });
        cartPanel.add(checkout);

        return cartPanel;
    }

}