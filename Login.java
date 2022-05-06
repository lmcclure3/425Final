   
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
import java.sql.ResultSet;
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
    public static String GlobalUsername;
    public static String GlobalFirstName;
    public static String GlobalLastName;
    //public static String GlobalLastName;
    public static String GlobalPhoneNumber;
    public static String GlobalPassword;
    public static String GlobalCardNumber;
    public static String GlobalExpDate;
    public static String GlobalSecCode;
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

        btnGuest.setText("Catalog");
        btnGuest.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnGuest.setBounds(180, 420, 259, 74);
        btnGuest.addActionListener(e -> {
            try {
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

        JButton btnHist = new JButton("Purchase History");
        btnHist.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnHist.setBounds(385, 200, 259, 74);
        btnHist.addActionListener(e -> {
            setContentPane(purchaseHistoryPanel());
        });
        contentPane.add(btnHist);
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
                GlobalUsername = username.getText();
                GlobalFirstName = name.getText();
                System.out.print(GlobalFirstName);
                GlobalPassword = pwLabel.getText();
                GlobalPhoneNumber = mobileField.getText();
                GlobalPassword = pw2.getText();
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

            String usrnm = username.getText();
            String pswd= pw1.getText();

            if (!username.getText().endsWith(".com") && !username.getText().contains("@")) {
                loginLabel.setText("Error: Invalid Email!");
            } else {
                guest = false;
                contentPane.repaint();
                setContentPane(contentPane);
            }
            //doing
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
                Statement statement = connection.createStatement();
                ResultSet ddpswd = statement.executeQuery("SELECT * FROM customers WHERE customerID = '"+ usrnm + "'");
                //String queryusrnm = "";
                ddpswd.next();
                String querypswd = ddpswd.getString("customerPassword");
                if (querypswd.equals(pw1.getText())) {

                	System.out.println("MATCHING PASSWORDS");
                	GlobalUsername = usrnm;
                	GlobalPassword = ddpswd.getString("customerPassword");
                	GlobalFirstName = ddpswd.getString("firstName");
                	GlobalPhoneNumber = ddpswd.getString("phone");	

                }
                else {
                    System.out.println("WRONG PASSWORD");
                }


                System.out.println("Poggers!");
                connection.close();
                //setContentPane(contentPane);
            } catch (Exception exception) {
                exception.printStackTrace();
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
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
                String query = "INSERT INTO \"HR\".\"CUSTOMERS\" (CUSTOMERID, CUSTOMERPASSWORD, FIRSTNAME, PHONE) VALUES ('"+ GlobalUsername +"', '"+GlobalPassword+ "','"+GlobalFirstName+ "','"+ GlobalPhoneNumber + "')";
                Statement sta = connection.createStatement();
                int x = sta.executeUpdate(query);
                System.out.println("Poggers!");
                connection.close();
                setContentPane(contentPane);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            GlobalCardNumber = ccNumber.getText();
            GlobalExpDate = expDate.getText();
            GlobalSecCode = secCode.getText();
            

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
            GlobalSecCode = secCode.getText();
            GlobalExpDate = expDate.getText();
            GlobalCardNumber = ccNumber.getText();
            System.out.println("Poggers!");
            setContentPane(contentPane);
        });
        ccPanel.add(button);

        return ccPanel;

    }

    //todo major updates needed here, pull acct info from db. these now get cached in the program variables upon login or account creation.
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

        // email Global
        JLabel emailLabel = new JLabel("Email: " + GlobalUsername);
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        emailLabel.setBounds(335, 100, 1000, 30);
        infoPanel.add(emailLabel);
        System.out.print(GlobalUsername);

        // customer name
        JLabel nameLabel = new JLabel("Name: " + GlobalFirstName);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameLabel.setBounds(335, 140, 1000, 30);
        infoPanel.add(nameLabel);
        System.out.print(GlobalFirstName);
        
        // mobile number
        JLabel mobileNumberLabel = new JLabel("Mobile Number: " + GlobalPhoneNumber);
        mobileNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mobileNumberLabel.setBounds(335, 180, 1000, 30);
        infoPanel.add(mobileNumberLabel);
        System.out.print(GlobalPhoneNumber);
        
        // go back to menu
        JButton exitButton = new JButton("Return to Menu");
        exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        exitButton.setBounds(335, 240, 200, 74);
        exitButton.addActionListener(e -> {
            System.out.println("Poggers!");
            setContentPane(contentPane);
        });
        infoPanel.add(exitButton);
        
        // update account info
        JButton updateButton = new JButton("Update Info");
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updateButton.setBounds(335, 340, 200, 74);
        updateButton.addActionListener(e -> {
            setContentPane(updateAccount());
        });
        infoPanel.add(updateButton);

        // delete acct
        JButton deleteButton = new JButton("Delete Account");
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deleteButton.setBounds(555, 240, 200, 74);
        deleteButton.addActionListener(e -> {
            // todo add query to delete account
            System.out.println("PLACEHOLDER - Delete Logic Here");

            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
                Statement statement = connection.createStatement();
                //ResultSet ddpswd = statement.executeQuery("SELECT * FROM customers WHERE customerID = '"+ usrnm + "'");
                //String queryusrnm = "";
                
                int x = statement.executeUpdate("DELETE FROM customers WHERE customerID = '" + GlobalUsername +"'");
                
               
                
                System.out.println("Poggers!");
                connection.close();
                //setContentPane(contentPane);
            } catch (Exception exception) {
                 exception.printStackTrace();
            }
            
          
        });
        infoPanel.add(deleteButton);

        return infoPanel;
    }

    // catalog
    private JPanel catalogPanel() throws IOException {
    	String queryCount= "";
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
        try {
	        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
	        Statement statement = connection.createStatement();
	        ResultSet ddpswd = statement.executeQuery("SELECT * FROM products WHERE productID = 'Gaming Chair'");
	        System.out.print(ddpswd.next());
	        queryCount = "" + ddpswd.getString("price");
	        connection.close();
        } catch (Exception exception) {
	        exception.printStackTrace();
	        queryCount = "0";
	    }
        JLabel chairPrice = new JLabel("Price: $" + queryCount);
        chairPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chairPrice.setBounds(200, 215, 1000, 75);
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * FROM stores WHERE storeID = 1");
            System.out.print(ddpswd.next());
            queryCount = "" + ddpswd.getString("quantity");
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            queryCount = "0";
        }
        JLabel chairQuantity = new JLabel("Quantity: " + queryCount); 
        chairQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chairQuantity.setBounds(200, 230, 1000, 75);
        JButton chairAdd = new JButton("Add to Cart");
        chairAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        chairAdd.setBounds(200, 280, 120, 30);
        chairAdd.addActionListener(e -> {
            shoppingCart.add("Gaming Chair");
        });
        catalogPanel.add(imgChair2);
        catalogPanel.add(chairDeets);
        catalogPanel.add(chairPrice);
        catalogPanel.add(chairQuantity);
        catalogPanel.add(chairAdd);

        // stuff for keyboard
        BufferedImage imgKeyboard = ImageIO.read(new File("lolkeyboard2.jpg"));
        JLabel imgKeyboard2 = new JLabel(new ImageIcon(imgKeyboard));
        imgKeyboard2.setBounds(200, 300, 171, 171);
        JLabel keyboardDeets = new JLabel("League of Legends Keyboard");
        keyboardDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        keyboardDeets.setBounds(200, 440, 1000, 75);
        try {
	        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
	        Statement statement = connection.createStatement();
	        ResultSet ddpswd = statement.executeQuery("SELECT * FROM products WHERE productID = 'League of Legends Keyboard'");
	        System.out.print(ddpswd.next());
	        queryCount = "" + ddpswd.getString("price");
	        connection.close();
        } catch (Exception exception) {
	        exception.printStackTrace();
	        queryCount = "0";
	    }
        JLabel keyboardPrice = new JLabel("Price: $" + queryCount);
        keyboardPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        keyboardPrice.setBounds(200, 455, 1000, 75);
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * FROM stores WHERE storeID = 4");
            
            //String queryusrnm = "";
            System.out.print(ddpswd.next());
            
            queryCount = "" + ddpswd.getString("quantity");
            //System.out.println("Ture");
            //System.out.println(queryCount);
            connection.close();
            
            //setContentPane(contentPane);
        } catch (Exception exception) {
            exception.printStackTrace();
            queryCount = "0";
        }
        JLabel keyboardQuantity = new JLabel("Quantity: " + queryCount);
        keyboardQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        keyboardQuantity.setBounds(200, 470, 1000, 75);
        JButton keyboardAdd = new JButton("Add to Cart");
        keyboardAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        keyboardAdd.setBounds(200, 515, 120, 30);
        keyboardAdd.addActionListener(e -> {
            shoppingCart.add("League of Legends Keyboard");
        });
        catalogPanel.add(imgKeyboard2);
        catalogPanel.add(keyboardDeets);
        catalogPanel.add(keyboardPrice);
        catalogPanel.add(keyboardQuantity);
        catalogPanel.add(keyboardAdd);

        // stuff for microphone
        BufferedImage imgMicro = ImageIO.read(new File("solocast2.jpg"));
        JLabel imgMicro2 = new JLabel(new ImageIcon(imgMicro));
        imgMicro2.setBounds(450, 50, 100, 171);
        JLabel microDeets = new JLabel("Microphone");
        microDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        microDeets.setBounds(450, 200, 1000, 75);
        try {
	        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
	        Statement statement = connection.createStatement();
	        ResultSet ddpswd = statement.executeQuery("SELECT * FROM products WHERE productID = 'Microphone'");
	        System.out.print(ddpswd.next());
	        queryCount = "" + ddpswd.getString("price");
	        connection.close();
        } catch (Exception exception) {
	        exception.printStackTrace();
	        queryCount = "0";
	    }
        JLabel microPrice = new JLabel("Price: $" + queryCount); 
        microPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        microPrice.setBounds(450, 215, 1000, 75);
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * FROM stores WHERE storeID = 2");
            
            //String queryusrnm = "";
            System.out.print(ddpswd.next());
            
            queryCount = "" + ddpswd.getString("quantity");
            //System.out.println("Ture");
            //System.out.println(queryCount);
            connection.close();
            
            //setContentPane(contentPane);
        } catch (Exception exception) {
            exception.printStackTrace();
            queryCount = "0";
        }
        JLabel microQuantity = new JLabel("Quantity: "+queryCount);
        microQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        microQuantity.setBounds(450, 230, 1000, 75);
        JButton microAdd = new JButton("Add to Cart");
        microAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        microAdd.setBounds(450, 280, 120, 30);
        microAdd.addActionListener(e -> {
            shoppingCart.add("Microphone");
        });
        catalogPanel.add(imgMicro2);
        catalogPanel.add(microDeets);
        catalogPanel.add(microPrice);
        catalogPanel.add(microQuantity);
        catalogPanel.add(microAdd);

        // stuff for headset
        BufferedImage imgHeadset = ImageIO.read(new File("razerkraken2.jpg"));
        JLabel imgHeadset2 = new JLabel(new ImageIcon(imgHeadset));
        imgHeadset2.setBounds(450, 300, 171, 171);
        JLabel headsetDeets = new JLabel("Headset");
        headsetDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        headsetDeets.setBounds(450, 440, 1000, 75);
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * FROM products WHERE productID = 'Headset'");
            System.out.print(ddpswd.next());
            queryCount = "" + ddpswd.getString("price");
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            queryCount = "0";
        }
        JLabel headsetPrice = new JLabel("Price: $" + queryCount); 
        headsetPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        headsetPrice.setBounds(450, 455, 1000, 75);
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * FROM stores WHERE storeID = 5");
            System.out.print(ddpswd.next());
            queryCount = "" + ddpswd.getString("quantity");
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            queryCount = "0";
        }
        JLabel headsetQuantity = new JLabel("Quantity: "+queryCount);
        headsetQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        headsetQuantity.setBounds(450, 470, 1000, 75);
        JButton headsetAdd = new JButton("Add to Cart");
        headsetAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        headsetAdd.setBounds(450, 515, 120, 30);
        headsetAdd.addActionListener(e -> {
            shoppingCart.add("Headset");
        });
        catalogPanel.add(imgHeadset2);
        catalogPanel.add(headsetDeets);
        catalogPanel.add(headsetPrice);
        catalogPanel.add(headsetQuantity);
        catalogPanel.add(headsetAdd);

        // stuff for mouse
        BufferedImage imgMouse = ImageIO.read(new File("gpro2.jpg"));
        JLabel imgMouse2 = new JLabel(new ImageIcon(imgMouse));
        imgMouse2.setBounds(700, 50, 171, 171);
        JLabel mouseDeets = new JLabel("Gaming Mouse");
        mouseDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        mouseDeets.setBounds(700, 200, 1000, 75);
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * FROM products WHERE productID = 'Gaming Chair'");
            System.out.print(ddpswd.next());
            queryCount = "" + ddpswd.getString("price");
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            queryCount = "0";
        }
        JLabel mousePrice = new JLabel("Price: $" + queryCount); 
        mousePrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        mousePrice.setBounds(700, 215, 1000, 75);
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * FROM stores WHERE storeID = 3");
            System.out.print(ddpswd.next());
            queryCount = "" + ddpswd.getString("quantity");
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            queryCount = "0";
        }
        JLabel mouseQuantity = new JLabel("Quantity: "+queryCount);
        mouseQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mouseQuantity.setBounds(700, 230, 1000, 75);
        JButton mouseAdd = new JButton("Add to Cart");
        mouseAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        mouseAdd.setBounds(700, 280, 120, 30);
        mouseAdd.addActionListener(e -> {
            shoppingCart.add("Gaming Mouse");
        });
        catalogPanel.add(imgMouse2);
        catalogPanel.add(mouseDeets);
        catalogPanel.add(mousePrice);
        catalogPanel.add(mouseQuantity);
        catalogPanel.add(mouseAdd);

        // stuff for printer
        BufferedImage imgPrinter = ImageIO.read(new File("printer2.jpg"));
        JLabel imgPrinter2 = new JLabel(new ImageIcon(imgPrinter));
        imgPrinter2.setBounds(700, 300, 171, 171);
        JLabel printerDeets = new JLabel("Printer");
        printerDeets.setFont(new Font("Tahoma", Font.PLAIN, 15));
        printerDeets.setBounds(700, 440, 1000, 75);
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * FROM products WHERE productID = 'Printer'");
            System.out.print(ddpswd.next());
            queryCount = "" + ddpswd.getString("price");
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            queryCount = "0";
        }
        JLabel printerPrice = new JLabel("Price: $" + queryCount);
        printerPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        printerPrice.setBounds(700, 455, 1000, 75);
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * FROM stores WHERE storeID = 6");
            System.out.print(ddpswd.next());
            queryCount = "" + ddpswd.getString("quantity");
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            queryCount = "0";
        }
        JLabel printerQuantity = new JLabel("Quantity: " + queryCount);
        printerQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        printerQuantity.setBounds(700, 470, 1000, 75);
        JButton printerAdd = new JButton("Add to Cart");
        printerAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        printerAdd.setBounds(700, 515, 120, 30);
        printerAdd.addActionListener(e -> {
            shoppingCart.add("Printer");
        });
        catalogPanel.add(imgPrinter2);
        catalogPanel.add(printerDeets);
        catalogPanel.add(printerPrice);
        catalogPanel.add(printerQuantity);
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
        String queryCount = "";
        String maxSaleID = "";
        String pid = "";
        for (int i = 0; i < shoppingCart.size(); i++) {
        	 try {
                 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
                 Statement statement = connection.createStatement();
                 ResultSet ddpswd = statement.executeQuery("SELECT * FROM products WHERE productID = '"+ shoppingCart.get(i) +"'");
                 System.out.print(ddpswd.next());
                 queryCount = "" + ddpswd.getString("price");
                 connection.close();
             } catch (Exception exception) {
            	 System.out.print("6");
            	 
                 exception.printStackTrace();
                 queryCount = "0";
             }
            total += Double.parseDouble( queryCount);
            
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
                Statement statement = connection.createStatement();
                ResultSet ddpswd = statement.executeQuery("SELECT * FROM STORES WHERE productID = '"+ shoppingCart.get(i) +"'");
                System.out.print(ddpswd.next());
                queryCount = "" + ddpswd.getString("quantity");
                connection.close();
            } catch (Exception exception) {
            	System.out.print("5");
                exception.printStackTrace();
                queryCount = "0";
            }
            
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
                Statement statement = connection.createStatement();
                ResultSet ddpswd = statement.executeQuery("UPDATE stores SET quantity = "+(Integer.parseInt(queryCount)-1) +" WHERE productID = '"+ shoppingCart.get(i) +"'");
                //System.out.print(ddpswd.next());
                
                //queryCount = "" + ddpswd.getString("price");
                connection.close();
            } catch (Exception exception) {
            	System.out.print("4");
                exception.printStackTrace();
                queryCount = "0";
                
            }
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
                Statement statement = connection.createStatement();
                ResultSet ddpswd = statement.executeQuery("UPDATE sales SET quantity = "+(Integer.parseInt(queryCount)-1) +" WHERE productID = '"+ shoppingCart.get(i) +"'");
                
                //queryCount = "" + ddpswd.getString("price");
                connection.close();
            } catch (Exception exception) {
            	System.out.print("3");
                exception.printStackTrace();
                queryCount = "0";
                
            }
           
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
                Statement statement = connection.createStatement();
                ResultSet ddpswd = statement.executeQuery("SELECT saleID from sales ORDER BY saleID DESC");
                System.out.print(ddpswd.next());
                maxSaleID = ddpswd.getString("saleID");
                //queryCount = "" + ddpswd.getString("price");
                connection.close();
            } catch (Exception exception) {
            	System.out.print("2");
                exception.printStackTrace();
              
            }
            //INSERT INTO "HR"."sales" (saleID, productID, quantity, saleDate, storeID, customerID,shipperID) VALUES (saleID, productID, quantity, saleDate, storeID, customerID,shipperID);
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
                Statement statement = connection.createStatement();
                ResultSet ddpswd = statement.executeQuery("INSERT INTO \"HR\".\"SALES\" (SALEID, PRODUCTID, QUANTITY, CUSTOMERID) VALUES ("+ (Integer.parseInt(maxSaleID)+1) +", '"+shoppingCart.get(i)+"', 1, '"+ GlobalUsername +"')");
                System.out.print(ddpswd.next());
                //queryCount = "" + ddpswd.getString("price");
                connection.close();
            } catch (Exception exception) {
            	System.out.print("1");
                exception.printStackTrace();
              
            }
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
                // todo check account credit card
                setContentPane(contentPane);
            }
        });
        cartPanel.add(checkout);

        return cartPanel;
    }

    // purchase history
    private JPanel purchaseHistoryPanel() {
        JPanel historyPanel = new JPanel();
        historyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        historyPanel.setBounds(new Rectangle(1014, 800));
        historyPanel.setLayout(null);

        // history header
        JLabel headerLabel = new JLabel("Your 5 most recent purchases:");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        headerLabel.setBounds(280, -10, 1000, 75);
        historyPanel.add(headerLabel);

        JLabel itemsLabel = new JLabel("Items:");
        itemsLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        itemsLabel.setBounds(100, 50, 1000, 75);
        historyPanel.add(itemsLabel);

        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        totalLabel.setBounds(500, 50, 1000, 75);
        historyPanel.add(totalLabel);

        // todo update this with results from the query, break loop early if there isnt 5 recent purchases
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","hr","oracle");
            Statement statement = connection.createStatement();
            ResultSet ddpswd = statement.executeQuery("SELECT * from sales where customerID = '"+GlobalUsername+"' ORDER BY saleID DESC");
            //ddpswd.next();
            //maxSaleID = ddpswd.getString("saleID");
            //queryCount = "" + ddpswd.getString("price");
            String product = "";
            for (int i = 0; i < 5 && ddpswd.next(); i++) {
            	product = ddpswd.getString("ProductID");
                JLabel tempItems = new JLabel(product);
                tempItems.setFont(new Font("Tahoma", Font.PLAIN, 20));
                tempItems.setBounds(100, 100 + (30 * i), 1000, 75);
                historyPanel.add(tempItems);
                Statement statement2 = connection.createStatement();
                ResultSet price = statement2.executeQuery("SELECT * from products where productID = '"+ product +"'");
                price.next();
                JLabel tempPrice = new JLabel(price.getString("price"));
                tempPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
                tempPrice.setBounds(500, 100 + (30 * i), 1000, 75);
                historyPanel.add(tempPrice);
            }
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
          
        }
        

        JButton returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        returnButton.setBounds(370, 420, 250, 75);
        returnButton.addActionListener(e -> {
            setContentPane(contentPane);
        });
        historyPanel.add(returnButton);

        return historyPanel;
    }
    
        // update account info
    private JPanel updateAccount() {
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        infoPanel.setBounds(new Rectangle(1014, 597));
        infoPanel.setLayout(null);

        // header
        JLabel headerLabel = new JLabel("Update Account Information:");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        headerLabel.setBounds(320, 0, 1000, 75);
        infoPanel.add(headerLabel);

        // email Global
        JTextField nameField = new JTextField("Name");
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameField.setBounds(335, 100, 420, 30);
        infoPanel.add(nameField);

        // customer name
        JTextField pwField = new JTextField("Password");
        pwField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pwField.setBounds(335, 140, 420, 30);
        infoPanel.add(pwField);

        // mobile number
        JTextField mobileNumberLabel = new JTextField("Mobile Number");
        mobileNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mobileNumberLabel.setBounds(335, 180, 420, 30);
        infoPanel.add(mobileNumberLabel);

        // update button
        JButton updateButton = new JButton("Confirm Updates");
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updateButton.setBounds(420, 250, 250, 75);
        updateButton.addActionListener(e -> {
            // todo make this update stuff
            setContentPane(contentPane);
        });
        infoPanel.add(updateButton);

        return infoPanel;
    }

}