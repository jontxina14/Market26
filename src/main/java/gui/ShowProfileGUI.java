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
		setBounds(100, 100, 1100, 500);
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.title"));
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLbl.setBounds(425, 30, 250, 40);
		contentPane.add(titleLbl);

		JLabel nameLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.name"));
		nameLbl.setBounds(330, 120, 100, 20);
		contentPane.add(nameLbl);

		JLabel mailLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mail"));
		mailLbl.setBounds(330, 160, 100, 20);
		contentPane.add(mailLbl);

		JLabel balanceLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.balance"));
		balanceLbl.setBounds(330, 200, 100, 20);
		contentPane.add(balanceLbl);

		nameTextField = new JTextField();
		nameTextField.setDisabledTextColor(Color.BLACK);
		nameTextField.setBounds(450, 120, 260, 25);
		nameTextField.setEnabled(false);
		nameTextField.setText((user != null) ? user.getName() : "");
		contentPane.add(nameTextField);

		mailTextField = new JTextField();
		mailTextField.setDisabledTextColor(Color.BLACK);
		mailTextField.setBounds(450, 160, 260, 25);
		mailTextField.setEnabled(false);
		mailTextField.setText((user != null)? user.getEmail() : "");
		contentPane.add(mailTextField);

		balanceTextField = new JTextField();
		balanceTextField.setDisabledTextColor(Color.BLACK);
		balanceTextField.setBounds(450, 200, 150, 25);
		balanceTextField.setEnabled(false);
		balanceTextField.setText((user != null) ? String.valueOf(user.getBalance()) : "");
		contentPane.add(balanceTextField);

		JButton qPurchasedButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sBoughts"));
		qPurchasedButton.setBounds(30, 300, 200, 45);
		contentPane.add(qPurchasedButton);
		qPurchasedButton.addActionListener(e -> query(QueryType.PURCHASED));
		
		JButton qOnSaleButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.onSale"));
		qOnSaleButton.setBounds(240, 300, 200, 45);
		contentPane.add(qOnSaleButton);
		qOnSaleButton.addActionListener(e -> query(QueryType.ON_SALES));

		JButton qWishListButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sWishList"));
		qWishListButton.setBounds(450, 300, 200, 45);
		contentPane.add(qWishListButton);
		qWishListButton.addActionListener(e -> query(QueryType.WISHLIST));


		JButton sMovementsButtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sMovements"));
		sMovementsButtton.setBounds(660, 300, 200, 45);
		contentPane.add(sMovementsButtton);

		JButton mBalanceButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mBalance"));
		mBalanceButton.setBounds(870, 300, 200, 45);
		contentPane.add(mBalanceButton);
		mBalanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ManageMoneyGUI(user);
				a.setVisible(true);
				dispose();
			}
		});

		
		JButton closeButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.close"));
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
			}
		});
		closeButton.setBounds(500, 380, 100, 45);
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