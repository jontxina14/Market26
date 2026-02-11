package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ResourceBundle;

import businessLogic.BLFacade;

public class LoginGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsername;
    private JPasswordField textFieldPassword;

    public LoginGUI() {

        ResourceBundle bundle = ResourceBundle.getBundle("Etiquetas");

        setTitle(bundle.getString("MainGUI.Login"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 280);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(40, 60, 40, 60));
        contentPane.setLayout(new BorderLayout(10, 20));
        setContentPane(contentPane);

        // ===== PANEL FORMULARIO =====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // USERNAME
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JLabel lblUsername = new JLabel(bundle.getString("LoginGUI.Username"));
        textFieldUsername = new JTextField();
        textFieldUsername.setPreferredSize(new Dimension(120, 25));

        userPanel.add(lblUsername);
        userPanel.add(textFieldUsername);

        // PASSWORD
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JLabel lblPassword = new JLabel(bundle.getString("LoginGUI.Password"));
        textFieldPassword = new JPasswordField();
        textFieldPassword.setPreferredSize(new Dimension(120, 25));

        passPanel.add(lblPassword);
        passPanel.add(textFieldPassword);

        formPanel.add(userPanel);
        formPanel.add(passPanel);

        contentPane.add(formPanel, BorderLayout.CENTER);

        // ===== BOTÓN =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        JButton btnLogin = new JButton(bundle.getString("LoginGUI.LoginButton"));
        btnLogin.setPreferredSize(new Dimension(100, 28));
        
        JButton btnClose = new JButton(bundle.getString("LoginGUI.CloseButton")); 
        btnClose.setPreferredSize(new Dimension(100, 28));
        
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnClose);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // ===== ACCIÓN =====
        btnLogin.addActionListener((ActionEvent e) -> {

        	BLFacade facade = MainGUI.getBusinessLogic();
        	boolean b = facade.isRegistered(
        			textFieldUsername.getText(),
        			new String(textFieldPassword.getPassword())
        			);

        	if (b) {
        		new RegisteredMainGUI(null).setVisible(true);
        		dispose();
        	} else {
        		JOptionPane.showMessageDialog(
        				null,
        				bundle.getString("LoginGUI.LoginError"),
        				bundle.getString("LoginGUI.LoginButton"),
        				JOptionPane.ERROR_MESSAGE
        				);
        	}
        });
        btnClose.addActionListener(e -> dispose());

    }
}
