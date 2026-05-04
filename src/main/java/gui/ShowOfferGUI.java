package gui;

import java.util.*;
import java.util.List;

import javax.swing.*;

import businessLogic.BLFacade;
import domain.Offer;
import domain.Report;
import domain.ReportContainer;

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
	private final JLabel jLabelPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Date"));
	private final JButton jButtonDecline = new JButton("ALDATU, bazter");
	private final JButton jButtonAccept = new JButton("ALDATU, onart");
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Close"));

	private JLabel jLabelMsg = new JLabel();
	private JFrame thisFrame;
	
	private QueryReportGUI parent;
	private JTextField textFieldDesc;
	private JTextField textFieldStatus;


	public ShowOfferGUI(Offer offer, String requesterEmail) {
				
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 420));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		BLFacade facade = MainGUInonReg.getBusinessLogic();
		
		List<String> status;
		status=Utils.getStatus();
		
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Name"));

		fieldTitle.setText(
			    offer.getRequest().getTitle() != null ? offer.getRequest().getTitle() : ""
			);

		fieldUser.setText(
			    offer.getRegistered().getEmail() != null ? offer.getRegistered().getEmail() : ""
			);

		fieldPrice.setText(String.valueOf(offer.getPrice()));
		
		jLabelTitle.setBounds(50, 30, 250, 25);
		jLabelUser.setBounds(50, 75, 250, 25);
		jLabelPrice.setBounds(53, 256, 140, 25);

		fieldTitle.setBounds(220, 30, 400, 30);
		fieldUser.setBounds(220, 75, 300, 30);
		fieldPrice.setBounds(223, 256, 120, 30);
		
		
		jButtonClose.setBounds(44, 321, 140, 40);
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				facade.acceptOffer(offer);
				dispose();
			}
		});

		jButtonDecline.setBounds(204, 321, 140, 40);
		jButtonDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//facade.declineOffer();
				dispose();
			}
		});
		
		
;
		jButtonAccept.setBounds(364, 321, 140, 40);
		jButtonAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		
		

		fieldTitle.setEditable(false);
		fieldUser.setEditable(false);
		fieldPrice.setEditable(false);
		

		jLabelMsg.setBounds(new Rectangle(376, 261, 300, 20));
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
		
		JLabel jLabelDesc = new JLabel("JON: ALDATU DESC && DENA");
		jLabelDesc.setBounds(30, 124, 130, 25);
		getContentPane().add(jLabelDesc);
		
		textFieldDesc = new JTextField();
		textFieldDesc.setText(offer.getDescription());
		textFieldDesc.setEditable(false);
		textFieldDesc.setBounds(220, 130, 378, 55);
		getContentPane().add(textFieldDesc);
		
		
		String stringStatus=status.get(offer.getStatus());
		
		textFieldStatus = new JTextField();
		textFieldStatus.setText(stringStatus);
		textFieldStatus.setEditable(false);
		textFieldStatus.setBounds(220, 200, 300, 30);
		getContentPane().add(textFieldStatus);
		
		JLabel jLabelStatus = new JLabel("JON: ALDATU STATUS && DENA");
		jLabelStatus.setBounds(30, 200, 130, 25);
		getContentPane().add(jLabelStatus);
		this.setVisible(true);

	}	 
}



