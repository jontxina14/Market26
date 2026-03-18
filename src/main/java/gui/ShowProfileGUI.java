package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Registered;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class ShowProfileGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField mailTextField;
	private JTextField balanceTextField;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public ShowProfileGUI(Registered r) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.name"));
		nameLbl.setBounds(42, 104, 64, 12);
		contentPane.add(nameLbl);
		
		JLabel mailLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mail"));
		mailLbl.setBounds(42, 145, 64, 13);
		contentPane.add(mailLbl);
		
		JLabel balanceLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.balance"));
		balanceLbl.setBounds(42, 181, 64, 12);
		contentPane.add(balanceLbl);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(116, 101, 84, 18);
		nameTextField.setEnabled(false);
		nameTextField.setText(r.getName());
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		mailTextField = new JTextField();
		mailTextField.setBounds(116, 142, 211, 18);
		mailTextField.setEnabled(false);
		mailTextField.setText(r.getEmail());
		contentPane.add(mailTextField);
		mailTextField.setColumns(10);
		
		balanceTextField = new JTextField();
		balanceTextField.setBounds(116, 178, 53, 18);
		balanceTextField.setEnabled(false);
		//TODO balanceTextField.setText(r.getBalance());
		contentPane.add(balanceTextField);
		balanceTextField.setColumns(10);
		
		JButton qBoughtsButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sBoughts"));
		qBoughtsButton.setBounds(53, 272, 103, 45);
		contentPane.add(qBoughtsButton);
		
		JButton qWishListButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sWishList"));
		qWishListButton.setBounds(182, 272, 84, 20);
		contentPane.add(qWishListButton);
		
		JButton sMovementsButtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.sMovements"));
		sMovementsButtton.setBounds(343, 272, 84, 20);
		contentPane.add(sMovementsButtton);
		
		JButton mBalanceButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mBalance"));
		mBalanceButton.setBounds(482, 272, 84, 20);
		contentPane.add(mBalanceButton);
		
		JLabel titleLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.title"));
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLbl.setBounds(42, 30, 158, 39);
		contentPane.add(titleLbl);

	}
}
