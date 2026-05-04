package gui;

import java.util.*;

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
	private final JButton jButtonDecline = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Decline"));
	private final JButton jButtonAccept = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Ban"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Close"));
	private JButton jButtonShowSale = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.ShowSale"));

	private JLabel jLabelMsg = new JLabel();
	private JFrame thisFrame;
	
	private QueryReportGUI parent;
	private JTextField textFieldDesc;


	public ShowOfferGUI(Offer offer, String requesterEmail) {
				
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 360));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		BLFacade facade = MainGUInonReg.getBusinessLogic();
		
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
		jLabelPrice.setBounds(50, 120, 140, 25);

		fieldTitle.setBounds(220, 30, 400, 30);
		fieldUser.setBounds(220, 75, 300, 30);
		fieldPrice.setBounds(220, 120, 120, 30);

		jButtonClose.setBounds(40, 250, 140, 40);
		jButtonDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
			}
		});
		
		
		jButtonDecline.setBounds(200, 250, 140, 40);
		jButtonAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		
		
		jButtonAccept.setBounds(360, 250, 140, 40);
		jButtonShowSale.setBounds(520, 250, 140, 40);

		fieldTitle.setEditable(false);
		fieldUser.setEditable(false);
		fieldPrice.setEditable(false);



		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);			
			}
		});
		
		
		
		
		jButtonShowSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});	
		

		jLabelMsg.setBounds(new Rectangle(220, 210, 300, 20));
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
		getContentPane().add(jButtonShowSale);
		
		JLabel jLabelDesc = new JLabel("JON: ALDATU DESC && DENA");
		jLabelDesc.setBounds(50, 175, 130, 25);
		getContentPane().add(jLabelDesc);
		
		textFieldDesc = new JTextField();
		textFieldDesc.setText(offer.getDescription());
		textFieldDesc.setEditable(false);
		textFieldDesc.setBounds(220, 175, 378, 55);
		getContentPane().add(textFieldDesc);
		this.setVisible(true);

	}	 
}



