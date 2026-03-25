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
import exceptions.NotEnoughMoneyException;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageMoneyGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldAmount;
	private JTextField textFieldPass;
	private JFrame thisFrame; 
	private JLabel lblMessage;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageMoneyGUI frame = new ManageMoneyGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManageMoneyGUI(Registered r) {
		ResourceBundle bundle = ResourceBundle.getBundle("Etiquetas");

		thisFrame = this;

		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ManageMoneyGUI.title"));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 320);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAmount = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ManageMoneyGUI.amount"));
		lblAmount.setBounds(80, 80, 100, 20);
		contentPane.add(lblAmount);

		JLabel lblPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ManageMoneyGUI.password"));
		lblPassword.setBounds(80, 120, 100, 20);
		contentPane.add(lblPassword);

		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(180, 80, 140, 25);
		contentPane.add(textFieldAmount);

		textFieldPass = new JPasswordField();
		textFieldPass.setBounds(180, 120, 140, 25);
		contentPane.add(textFieldPass);

		JPanel panel = new JPanel();
		panel.setBounds(70, 170, 260, 40);
		contentPane.add(panel);

		JRadioButton rdbtnWithdraw = new JRadioButton(
				ResourceBundle.getBundle("Etiquetas").getString("ManageMoneyGUI.withdraw"));
		panel.add(rdbtnWithdraw);
		buttonGroup.add(rdbtnWithdraw);

		JRadioButton rdbtnDeposit = new JRadioButton(
				ResourceBundle.getBundle("Etiquetas").getString("ManageMoneyGUI.deposit"));
		panel.add(rdbtnDeposit);
		buttonGroup.add(rdbtnDeposit);

		JButton btnConfirm = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ManageMoneyGUI.confirm"));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUInonReg.getBusinessLogic();
				if(!rdbtnDeposit.isSelected() && !rdbtnWithdraw.isSelected()) { 
					lblMessage.setText(bundle.getString("ManageMoneyGUI.NotSelected"));
				}else{
					double amount = 0;
					try{
						amount = Double.parseDouble(textFieldAmount.getText());
						if(!r.getPass().equals(textFieldPass.getText())) {
							lblMessage.setText(bundle.getString("ManageMoneyGUI.IncorrectPassword"));
						}else {
							Registered reg = facade.manageMoney(r, amount, rdbtnDeposit.isSelected() ? MovementType.DEPOSIT : MovementType.WITHDRAW);
							dispose();
							JFrame a = new ShowProfileGUI(reg);
							a.setVisible(true);
						}


					}catch(NumberFormatException e1) {
						lblMessage.setText(bundle.getString("ManageMoneyGUI.AmountError"));
					}catch(NotEnoughMoneyException e2) {
						lblMessage.setText(bundle.getString("ManageMoneyGUI.NotEnoughMoney"));
					}


				}
			}
		});
		btnConfirm.setBounds(70, 230, 110, 30);
		contentPane.add(btnConfirm);

		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ManageMoneyGUI.close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
				JFrame a = new ShowProfileGUI(r);
				a.setVisible(true);
			}
		});
		btnClose.setBounds(210, 230, 110, 30); 
		contentPane.add(btnClose);

		lblMessage = new JLabel(" ");
		lblMessage.setBounds(80, 43, 240, 25);
		lblMessage.setForeground(Color.red);
		lblMessage.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(lblMessage);
	}
}