package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Seller;

import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;

    public RegisterGUI() {

        ResourceBundle bundle = ResourceBundle.getBundle("Etiquetas");

        setTitle(bundle.getString("MainGUI.Register"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 320);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(40, 60, 40, 60));
        contentPane.setLayout(new BorderLayout(10, 20));
        setContentPane(contentPane);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));

        // USERNAME
        JLabel lblUsername = new JLabel(bundle.getString("RegisterGUI.Username"));
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldUsername = new JTextField();

        // PASSWORD
        JLabel lblPassword = new JLabel(bundle.getString("RegisterGUI.Password"));
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordField = new JPasswordField();

        // REPEAT PASSWORD
        JLabel lblRepeat = new JLabel(bundle.getString("RegisterGUI.RepeatPassword"));
        lblRepeat.setHorizontalAlignment(SwingConstants.RIGHT);
        repeatPasswordField = new JPasswordField();

        formPanel.add(lblUsername);
        formPanel.add(textFieldUsername);
        formPanel.add(lblPassword);
        formPanel.add(passwordField);
        formPanel.add(lblRepeat);
        formPanel.add(repeatPasswordField);

        contentPane.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        JButton btnRegister = new JButton(bundle.getString("MainGUI.Register"));
        
        btnRegister.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String password = new String(passwordField.getPassword());
        		String password2 = new String(repeatPasswordField.getPassword());
        		BLFacade facade = MainGUI.getBusinessLogic();
        		if(password.equals(password2)) {
        			Seller erabiltzailea = facade.isRegistered(textFieldUsername.getText(), password2);
        			if(erabiltzailea == null) {
        				//TODO new Seller
        				facade.register();
        			}
        			else {
        				//TODO GUI-ean jarri
        				System.out.println("Jada existizen da");
        			}
        		}else {
        			//TODO GUI-ean
        			System.out.println("Pasahitzak ez dira berdinak");
        		}
        			

        		
        		
        	}
        });
        
        
        JButton btnClose = new JButton(bundle.getString("RegisterGUI.CloseButton"));

        btnRegister.setPreferredSize(new Dimension(140, 28));
        btnClose.setPreferredSize(new Dimension(140, 28));

        buttonPanel.add(btnRegister);
        buttonPanel.add(btnClose);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        
        btnClose.addActionListener(e -> dispose());

    }
}
