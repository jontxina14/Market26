package gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Registered;
import enums.SaleType;

import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

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
		
		setBounds(100, 100, 1350, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.title"));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 22));
		titleLbl.setBounds(525, 25, 250, 40);
		contentPane.add(titleLbl);

		JLabel nameLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.name"));
		nameLbl.setBounds(480, 100, 100, 20);
		contentPane.add(nameLbl);

		nameTextField = new JTextField();
		nameTextField.setDisabledTextColor(Color.BLACK);
		nameTextField.setBounds(600, 100, 260, 25);
		nameTextField.setEnabled(false);
		nameTextField.setText((user != null) ? user.getName() : "");
		contentPane.add(nameTextField);

		JLabel mailLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mail"));
		mailLbl.setBounds(480, 140, 100, 20);
		contentPane.add(mailLbl);

		mailTextField = new JTextField();
		mailTextField.setDisabledTextColor(Color.BLACK);
		mailTextField.setBounds(600, 140, 260, 25);
		mailTextField.setEnabled(false);
		mailTextField.setText((user != null)? user.getEmail() : "");
		contentPane.add(mailTextField);

		JLabel balanceLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.balance"));
		balanceLbl.setBounds(480, 180, 100, 20);
		contentPane.add(balanceLbl);

		balanceTextField = new JTextField();
		balanceTextField.setDisabledTextColor(Color.BLACK);
		balanceTextField.setBounds(600, 180, 150, 25);
		balanceTextField.setEnabled(false);
		balanceTextField.setText((user != null) ? String.valueOf(user.getBalance()) : "");
		contentPane.add(balanceTextField);

		JButton qPurchasedButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sBoughts"));
		qPurchasedButton.setBounds(35, 280, 200, 45);
		contentPane.add(qPurchasedButton);
		qPurchasedButton.addActionListener(e -> query(SaleType.PURCHASED));

		JButton qOnSaleButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.onSale"));
		qOnSaleButton.setBounds(245, 280, 200, 45);
		contentPane.add(qOnSaleButton);
		qOnSaleButton.addActionListener(e -> query(SaleType.ON_SALES));

		JButton qWishListButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sWishList"));
		qWishListButton.setBounds(455, 280, 200, 45);
		contentPane.add(qWishListButton);
		qWishListButton.addActionListener(e -> query(SaleType.WISHLIST));

		JButton sMovementsButtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sMovements"));
		sMovementsButtton.setBounds(665, 280, 200, 45);
		contentPane.add(sMovementsButtton);
		sMovementsButtton.addActionListener(arg0 -> {
			JFrame a = new ShowMovementsGUI(user.getEmail());
			a.setVisible(true);
		});

		JButton mBalanceButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mBalance"));
		mBalanceButton.setBounds(875, 280, 200, 45);
		contentPane.add(mBalanceButton);
		mBalanceButton.addActionListener(e -> {
			JFrame a = new ManageMoneyGUI(user);
			a.setVisible(true);
			dispose();
		});

		JButton shOffersButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.shOffers"));
		shOffersButton.setBounds(1085, 280, 200, 45);
		contentPane.add(shOffersButton);
		shOffersButton.addActionListener(arg0 -> {
			JFrame a = new QueryOfferGUI(r.getEmail());
			a.setVisible(true);
		});

		JButton closeButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.close"));
		closeButton.setBounds(560, 380, 180, 45);
		contentPane.add(closeButton);
		closeButton.addActionListener(e -> thisFrame.setVisible(false));
	}

	private void query(SaleType type) {
		JFrame a = new QuerySaleGUI(user.getEmail(), type);
		a.setVisible(true);
	}

	public void refreshBalance() {
		balanceTextField.setText((user != null) ? String.valueOf(user.getBalance()) : "");
	}
}