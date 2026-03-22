package gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Registered;

import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowProfileGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField mailTextField;
	private JTextField balanceTextField;
	private JFrame thisFrame; 
	private Registered user;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowProfileGUI frame = new ShowProfileGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ShowProfileGUI(Registered r) {
		user = r;
		thisFrame = this;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.title"));
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLbl.setBounds(300, 20, 250, 40);
		contentPane.add(titleLbl);

		JLabel nameLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.name"));
		nameLbl.setBounds(150, 100, 100, 20);
		contentPane.add(nameLbl);

		JLabel mailLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mail"));
		mailLbl.setBounds(150, 140, 100, 20);
		contentPane.add(mailLbl);

		JLabel balanceLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.balance"));
		balanceLbl.setBounds(150, 180, 100, 20);
		contentPane.add(balanceLbl);

		nameTextField = new JTextField();
		nameTextField.setDisabledTextColor(Color.BLACK);
		nameTextField.setBounds(280, 100, 300, 25);
		nameTextField.setEnabled(false);
		nameTextField.setText((user != null) ? user.getName() : "");
		contentPane.add(nameTextField);

		mailTextField = new JTextField();
		mailTextField.setDisabledTextColor(Color.BLACK);
		mailTextField.setBounds(280, 140, 300, 25);
		mailTextField.setEnabled(false);
		mailTextField.setText((user != null)? user.getEmail() : "");
		contentPane.add(mailTextField);

		balanceTextField = new JTextField();
		balanceTextField.setDisabledTextColor(Color.BLACK);
		balanceTextField.setBounds(280, 180, 150, 25);
		balanceTextField.setEnabled(false);
		balanceTextField.setText((user != null) ? String.valueOf(user.getBalance()) : "");
		contentPane.add(balanceTextField);

		JButton qPurchasedButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sBoughts"));
		qPurchasedButton.setBounds(10, 280, 150, 45);
		contentPane.add(qPurchasedButton);
		qPurchasedButton.addActionListener(e -> query(QueryType.PURCHASED));
		
		JButton qOnSaleButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.onSale"));
		qOnSaleButton.setBounds(128, 335, 150, 45);
		contentPane.add(qOnSaleButton);
		qOnSaleButton.addActionListener(e -> query(QueryType.ON_SALES));

		JButton qWishListButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sWishList"));
		qWishListButton.setBounds(266, 280, 150, 45);
		contentPane.add(qWishListButton);
		qWishListButton.addActionListener(e -> query(QueryType.WISHLIST));


		JButton sMovementsButtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sMovements"));
		sMovementsButtton.setBounds(421, 280, 175, 45);
		contentPane.add(sMovementsButtton);

		JButton mBalanceButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mBalance"));
		mBalanceButton.setBounds(608, 280, 170, 45);
		contentPane.add(mBalanceButton);
		mBalanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ManageMoneyGUI(user);
				a.setVisible(true);
			}
		});

		
		JButton closeButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.close"));
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
			}
		});
		closeButton.setBounds(350, 345, 100, 45);
		contentPane.add(closeButton);
		

	}
	
	public void query(QueryType type) {
		JFrame a = new QueryGUI(user.getEmail(), type);
		a.setVisible(true);
	}
	
	public void refreshBalance() {
		balanceTextField.setText((user != null) ? String.valueOf(user.getBalance()) : "");
	}
}