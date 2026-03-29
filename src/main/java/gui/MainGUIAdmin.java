package gui;

import javax.swing.*;
import businessLogic.BLFacade;
import domain.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUIAdmin extends JFrame {

    private String adminMail;
    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;
    private JButton jButtonComplaints = null;
    private JButton jButtonReports = null;

    protected JLabel jLabelSelectOption;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnNewRadioButton_1;
    private JRadioButton rdbtnNewRadioButton_2;
    private JPanel panel;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public MainGUIAdmin(Admin admin) {

        this.adminMail = admin.getEmail();

        this.setSize(495, 290);
        jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
        jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
        jLabelSelectOption.setForeground(Color.BLACK);
        jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);

        rdbtnNewRadioButton = new JRadioButton("English");
        rdbtnNewRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Locale.setDefault(new Locale("en"));
                paintAgain();
            }
        });
        buttonGroup.add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
        rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Locale.setDefault(new Locale("eus"));
                paintAgain();
            }
        });
        buttonGroup.add(rdbtnNewRadioButton_1);

        rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
        rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Locale.setDefault(new Locale("es"));
                paintAgain();
            }
        });
        buttonGroup.add(rdbtnNewRadioButton_2);

        panel = new JPanel();
        panel.add(rdbtnNewRadioButton_1);
        panel.add(rdbtnNewRadioButton_2);
        panel.add(rdbtnNewRadioButton);

        jButtonComplaints = new JButton();
        jButtonComplaints.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIAdmin.ShowComplaints"));
        jButtonComplaints.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        jButtonReports = new JButton();
        jButtonReports.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIAdmin.ShowReports"));
        jButtonReports.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        jContentPane = new JPanel();
        jContentPane.setLayout(new GridLayout(4, 1, 0, 0));
        jContentPane.add(jLabelSelectOption);
        jContentPane.add(jButtonComplaints);
        jContentPane.add(jButtonReports);
        jContentPane.add(panel);

        setContentPane(jContentPane);
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle") + ": " + adminMail);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    private void paintAgain() {
        jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
        jButtonComplaints.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIAdmin.ShowComplaints"));
        jButtonReports.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIAdmin.ShowReports"));
        this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle") + ": " + adminMail);
    }
}