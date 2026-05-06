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

public class ShowOtherProfileGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField mailTextField;
	private JTextField balanceTextField;
	private JFrame thisFrame; 
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

	public ShowOtherProfileGUI(Registered seller) {
		thisFrame = this;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 560, 395);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.Seller"));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 22));
		titleLbl.setBounds(143, 28, 250, 40);
		contentPane.add(titleLbl);

		JLabel nameLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.name"));
		nameLbl.setBounds(61, 98, 100, 20);
		contentPane.add(nameLbl);

		nameTextField = new JTextField();
		nameTextField.setDisabledTextColor(Color.BLACK);
		nameTextField.setBounds(181, 98, 260, 25);
		nameTextField.setEnabled(false);
		nameTextField.setText((seller != null) ? seller.getName() : "");
		contentPane.add(nameTextField);

		JLabel mailLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.mail"));
		mailLbl.setBounds(61, 138, 100, 20);
		contentPane.add(mailLbl);

		mailTextField = new JTextField();
		mailTextField.setDisabledTextColor(Color.BLACK);
		mailTextField.setBounds(181, 138, 260, 25);
		mailTextField.setEnabled(false);
		mailTextField.setText((seller != null)? seller.getEmail() : "");
		contentPane.add(mailTextField);

		
		JButton closeButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.close"));
		closeButton.setBounds(173, 282, 180, 45);
		contentPane.add(closeButton);
		
		JLabel ratingLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.rating"));
		ratingLbl.setBounds(61, 186, 100, 20);
		contentPane.add(ratingLbl);
		
		ratingTextField = new JTextField();
		ratingTextField.setText(String.valueOf(seller.getRating()));
		ratingTextField.setEnabled(false);
		ratingTextField.setDisabledTextColor(Color.BLACK);
		ratingTextField.setBounds(181, 181, 260, 25);
		contentPane.add(ratingTextField);
		closeButton.addActionListener(e -> thisFrame.setVisible(false));
	}
}