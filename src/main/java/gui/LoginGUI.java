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
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(40, 60, 40, 60));
        contentPane.setLayout(new BorderLayout(10, 20));
        setContentPane(contentPane);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 15));

        // USERNAME
        JLabel lblUsername = new JLabel(bundle.getString("LoginGUI.Username"));
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldUsername = new JTextField();

        // PASSWORD
        JLabel lblPassword = new JLabel(bundle.getString("LoginGUI.Password"));
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldPassword = new JPasswordField();

        formPanel.add(lblUsername);
        formPanel.add(textFieldUsername);
        formPanel.add(lblPassword);
        formPanel.add(textFieldPassword);

        contentPane.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        JButton btnLogin = new JButton(bundle.getString("LoginGUI.LoginButton"));
        btnLogin.setPreferredSize(new Dimension(140, 28));

        JButton btnClose = new JButton(bundle.getString("LoginGUI.CloseButton"));
        btnClose.setPreferredSize(new Dimension(140, 28));

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnClose);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        btnLogin.addActionListener((ActionEvent e) -> {

            BLFacade facade = MainGUI.getBusinessLogic();
            boolean b = facade.isRegistered(
                    textFieldUsername.getText(),
                    new String(textFieldPassword.getPassword())
            );

            if (b) {
                new RegisteredMainGUI(null).setVisible(true);
                dispose();
            }
        });

        btnClose.addActionListener(e -> dispose());
    }
}
