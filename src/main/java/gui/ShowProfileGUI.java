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
import javax.swing.border.LineBorder;

public class ShowProfileGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField mailTextField;
	private JTextField balanceTextField;
	private JFrame thisFrame; 
	private Registered user;
	private JTextField ratingTextField;

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
		
		setBounds(100, 100, 750, 650);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.title"));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 26));
		titleLbl.setBounds(0, 30, 750, 40);
		contentPane.add(titleLbl);

		JLabel nameLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.name"));
		nameLbl.setBounds(180, 100, 100, 20);
		contentPane.add(nameLbl);

		nameTextField = new JTextField();
		nameTextField.setDisabledTextColor(Color.BLACK);
		nameTextField.setBounds(300, 100, 260, 25);
		nameTextField.setEnabled(false);
		nameTextField.setText((user != null) ? user.getName() : "");
		contentPane.add(nameTextField);

		JLabel mailLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mail"));
		mailLbl.setBounds(180, 140, 100, 20);
		contentPane.add(mailLbl);

		mailTextField = new JTextField();
		mailTextField.setDisabledTextColor(Color.BLACK);
		mailTextField.setBounds(300, 140, 260, 25);
		mailTextField.setEnabled(false);
		mailTextField.setText((user != null)? user.getEmail() : "");
		contentPane.add(mailTextField);

		JLabel balanceLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.balance"));
		balanceLbl.setBounds(180, 180, 100, 20);
		contentPane.add(balanceLbl);

		balanceTextField = new JTextField();
		balanceTextField.setDisabledTextColor(Color.BLACK);
		balanceTextField.setBounds(300, 180, 260, 25);
		balanceTextField.setEnabled(false);
		balanceTextField.setText((user != null) ? String.valueOf(user.getBalance()) : "");
		contentPane.add(balanceTextField);
		
		JLabel ratingLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.rating"));
		ratingLbl.setBounds(180, 220, 100, 20);
		contentPane.add(ratingLbl);
		
		ratingTextField = new JTextField();
		ratingTextField.setText((user != null) ? String.valueOf(user.getRating()) : "");
		ratingTextField.setEnabled(false);
		ratingTextField.setDisabledTextColor(Color.BLACK);
		ratingTextField.setBounds(300, 220, 260, 25);
		contentPane.add(ratingTextField);

		JButton qPurchasedButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sBoughts"));
		qPurchasedButton.setBounds(80, 290, 180, 45);
		contentPane.add(qPurchasedButton);
		qPurchasedButton.addActionListener(e -> query(SaleType.PURCHASED));

		JButton qOnSaleButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.onSale"));
		qOnSaleButton.setBounds(280, 290, 180, 45);
		contentPane.add(qOnSaleButton);
		qOnSaleButton.addActionListener(e -> query(SaleType.ON_SALES));

		JButton qWishListButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sWishList"));
		qWishListButton.setBounds(480, 290, 180, 45);
		contentPane.add(qWishListButton);
		qWishListButton.addActionListener(e -> query(SaleType.WISHLIST));

		JButton sMovementsButtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sMovements"));
		sMovementsButtton.setBounds(80, 355, 180, 45);
		contentPane.add(sMovementsButtton);
		sMovementsButtton.addActionListener(arg0 -> {
			JFrame a = new ShowMovementsGUI(user.getEmail());
			a.setVisible(true);
		});

		JButton mBalanceButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mBalance"));
		mBalanceButton.setBounds(280, 355, 180, 45);
		contentPane.add(mBalanceButton);
		mBalanceButton.addActionListener(e -> {
			JFrame a = new ManageMoneyGUI(user);
			a.setVisible(true);
			dispose();
		});

		JButton shOffersButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.shOffers"));
		shOffersButton.setBounds(480, 355, 180, 45);
		contentPane.add(shOffersButton);
		shOffersButton.addActionListener(arg0 -> {
			JFrame a = new QueryOfferGUI(r.getEmail());
			a.setVisible(true);
		});

		JButton shReviewsButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.shReviews"));
		shReviewsButton.setBounds(280, 420, 180, 45);
		contentPane.add(shReviewsButton);
		shReviewsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new QueryReviewGUI(r.getEmail());
				a.setVisible(true);
			}
		});

		JButton closeButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.close"));
		closeButton.setBounds(280, 520, 180, 45);
		closeButton.setBackground(new Color(231, 76, 60));
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Tahoma", Font.BOLD, 12));
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