package gui;

import java.util.*;
import java.util.List;

import javax.swing.*;

import businessLogic.BLFacade;
import domain.Offer;
import domain.OfferContainer;
import domain.Registered;
import domain.Report;
import domain.ReportContainer;
import exceptions.NotEnoughMoneyException;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;


public class ShowOfferGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField fieldTitle=new JTextField();
	private final JTextField fieldUser = new JTextField();
	private final JTextField fieldPrice = new JTextField();	

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Title"));
	private JLabel jLabelUser = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.User"));
	private final JLabel jLabelPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Price"));
	private final JButton jButtonDecline = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Decline"));
	private final JButton jButtonAccept = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Accept"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Close"));

	private JLabel jLabelMsg = new JLabel();
	private JFrame thisFrame;
	
	private JTextField textFieldDesc;
	private JTextField textFieldStatus;


	public ShowOfferGUI(OfferContainer offer, String requesterEmail, JFrame p) {
				
		
		QueryOfferGUI parent = (QueryOfferGUI) p;
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 440));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Name"));
		
		BLFacade facade = MainGUInonReg.getBusinessLogic();
		List<String> status;
		status=Utils.getStatus();
		
		

		fieldTitle.setText(
			    offer.getRequest().getTitle() != null ? offer.getRequest().getTitle() : ""
			);

		fieldUser.setText(
			    offer.getRegistered().getEmail() != null ? offer.getRegistered().getEmail(): ""
			);

		fieldPrice.setText(String.valueOf(offer.getOffer().getPrice()));
		
		jLabelTitle.setBounds(40, 30, 180, 25);
		jLabelUser.setBounds(40, 75, 180, 25);
		jLabelPrice.setBounds(40, 245, 1800, 25);

		fieldTitle.setBounds(240, 28, 400, 30);
		fieldUser.setBounds(240, 73, 400, 30);
		fieldPrice.setBounds(240, 243, 120, 30);
		
		
		jButtonClose.setBounds(60, 330, 160, 45);
		jButtonClose.setBackground(new Color(231, 76, 60));
		jButtonClose.setForeground(Color.WHITE);
		jButtonClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		jButtonDecline.setBounds(260, 330, 160, 45);
		jButtonDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				facade.declineOffer(offer.getOffer());
				parent.refreshQuery();
				dispose();
			}
		});
		
		
;
		jButtonAccept.setBounds(460, 330, 160, 45);
		jButtonAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					facade.acceptOffer(offer.getOffer());
					parent.refreshQuery();
					dispose();
				}catch (NotEnoughMoneyException ex){
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.NotEnoughMoney"));
				}

			}
		});
		
		

		fieldTitle.setEditable(false);
		fieldUser.setEditable(false);
		fieldPrice.setEditable(false);
		

		jLabelMsg.setBounds(new Rectangle(35, 299, 260, 20));
		jLabelMsg.setForeground(Color.red);



		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jLabelTitle, null);

		fieldTitle.setColumns(10);

		getContentPane().add(jButtonDecline);
		getContentPane().add(jButtonAccept);
		getContentPane().add(fieldTitle);
		getContentPane().add(jLabelUser);
		getContentPane().add(fieldUser);
		getContentPane().add(jLabelPrice);
		getContentPane().add(fieldPrice);
		
		JLabel jLabelDesc = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Description"));
		jLabelDesc.setBounds(40, 120, 180, 25);
		getContentPane().add(jLabelDesc);
		
		textFieldDesc = new JTextField();
		textFieldDesc.setText(offer.getOffer().getDescription());
		textFieldDesc.setEditable(false);
		textFieldDesc.setBounds(240, 118, 400, 60);
		getContentPane().add(textFieldDesc);
		
		
		String stringStatus=status.get(offer.getOffer().getStatus());
		
		textFieldStatus = new JTextField();
		textFieldStatus.setText(stringStatus);
		textFieldStatus.setEditable(false);
		textFieldStatus.setBounds(240, 198, 200, 30);
		getContentPane().add(textFieldStatus);
		
		JLabel jLabelStatus = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Status"));
		jLabelStatus.setBounds(40, 200, 180, 25);
		getContentPane().add(jLabelStatus);
		this.setVisible(true);

	}	 
}



