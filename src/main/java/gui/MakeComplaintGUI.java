package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import businessLogic.BLFacade;
import domain.Sale;
import domain.SaleContainer;

public class MakeComplaintGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textField;
	private JLabel lblMessage;

	public MakeComplaintGUI(String currentUsermail, Sale s) {
		ResourceBundle bundle = ResourceBundle.getBundle("Etiquetas");

		setTitle(bundle.getString("MakeComplaintGUI.title"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 320);
		setResizable(false);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMessage = new JLabel("");
		lblMessage.setForeground(Color.RED);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(10, 11, 364, 25);
		contentPane.add(lblMessage);

		JLabel lblNewLabel = new JLabel(bundle.getString("MakeComplaintGUI.WriteHere"));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 47, 364, 25);
		contentPane.add(lblNewLabel);

		textField = new JTextArea();
		textField.setLineWrap(true);
		textField.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textField);
		scrollPane.setBounds(50, 83, 284, 120);
		contentPane.add(scrollPane);

		JButton btnConfirm = new JButton(bundle.getString("ManageMoneyGUI.confirm"));
		btnConfirm.setBounds(65, 225, 120, 30);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUInonReg.getBusinessLogic();
				String text = textField.getText().trim();
				if(!text.isEmpty()) {
					facade.makeComplaint(currentUsermail, s, text);
					dispose();
				} else {
					lblMessage.setText(bundle.getString("MakeComplaintGUI.Empty"));
				}
			}
		});
		contentPane.add(btnConfirm);

		JButton btnClose = new JButton(bundle.getString("ManageMoneyGUI.close"));
		btnClose.setBounds(195, 225, 120, 30);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnClose);
	}
}