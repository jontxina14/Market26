package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.MovementType;
import domain.Registered;
import domain.Sale;
import exceptions.NotEnoughMoneyException;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MakeComplaintGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JFrame thisFrame; 
	private JLabel lblMessage;
	private JTextArea textField;


	public MakeComplaintGUI(String currentUsermail, Sale s) {
		ResourceBundle bundle = ResourceBundle.getBundle("Etiquetas");

		thisFrame = this;

		setTitle(bundle.getString("MakeComplaintGUI.title"));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 320);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnConfirm = new JButton(bundle.getString("ManageMoneyGUI.confirm"));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUInonReg.getBusinessLogic();

			}
		});
		btnConfirm.setBounds(70, 230, 110, 30);
		contentPane.add(btnConfirm);

		JButton btnClose = new JButton(bundle.getString("ManageMoneyGUI.close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(210, 230, 110, 30); 
		contentPane.add(btnClose);

		lblMessage = new JLabel(" ");
		lblMessage.setBounds(80, 43, 240, 25);
		lblMessage.setForeground(Color.red);
		lblMessage.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(lblMessage);

		JLabel lblNewLabel = new JLabel(bundle.getString("MakeComplaintGUI.WriteHere")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(80, 95, 240, 25);
		lblMessage.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(lblNewLabel);

		textField = new JTextArea();
		textField.setBounds(84, 130, 215, 90);
		textField.setLineWrap(true);
		textField.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textField);
		scrollPane.setBounds(84, 130, 215, 90);
		contentPane.add(scrollPane);
	}
}