package gui;

import java.util.*;

import javax.swing.*;

import businessLogic.BLFacade;
import domain.Complaint;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;


public class ShowComplaintGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField fieldTitle=new JTextField();
	private JTextField fieldDescription=new JTextField();
	private JTextField fieldPrice = new JTextField();
	private final JTextField fieldUser = new JTextField();
	private final JTextField fieldDate = new JTextField();	

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowComplaintGUI.Title"));
	private JLabel jLabelDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowComplaintGUI.Description")); 
	private JLabel jLabelPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowComplaintGUI.Price"));
	private JLabel jLabelUser = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowComplaintGUI.User"));
	private final JLabel jLabelDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowComplaintGUI.Date"));
	private final JButton jButtonDecline = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowComplaintGUI.Decline"));
	private final JButton jButtonAccept = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowComplaintGUI.Accept"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowComplaintGUI.Close"));

	private JLabel jLabelMsg = new JLabel();
	private JFrame thisFrame;


	public ShowComplaintGUI(Complaint complaint) {
		thisFrame=this; 
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(900, 500));
		this.setLocationRelativeTo(null);
		
		BLFacade facade = MainGUInonReg.getBusinessLogic();

		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ShowComplaintGUI.Name"));
		fieldTitle.setText(complaint.getSaleTitle());
		fieldPrice.setText(Float.toString(complaint.getSale().getPrice()));
		fieldUser.setText(complaint.getUser().getEmail());
		fieldDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(complaint.getDate()));
		fieldDescription.setText(complaint.getDescription());

		jLabelTitle.setBounds(40, 30, 250, 25);
		jLabelPrice.setBounds(40, 80, 250, 25);
		jLabelUser.setBounds(40, 130, 250, 25);
		jLabelDate.setBounds(40, 180, 250, 25);
		jLabelDescription.setBounds(40, 240, 250, 25);

		fieldTitle.setBounds(250, 30, 550, 30);
		fieldPrice.setBounds(250, 80, 150, 30);
		fieldUser.setBounds(250, 130, 250, 30);
		fieldDate.setBounds(250, 180, 150, 30);
		fieldDescription.setBounds(40, 270, 760, 100);

		jButtonClose.setBounds(130, 395, 180, 45);
		
		
		jButtonDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.declineComplaint(complaint);
				dispose();
			}
		});
		jButtonDecline.setBounds(350, 395, 180, 45);
		jButtonAccept.setBounds(570, 395, 180, 45);

		fieldTitle.setEditable(false);
		fieldDescription.setEditable(false);
		fieldPrice.setEditable(false);
		fieldUser.setEditable(false);
		fieldDate.setEditable(false);



		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);			
			}
		});
		
		

		jLabelMsg.setBounds(new Rectangle(275, 214, 305, 20));
		jLabelMsg.setForeground(Color.red);



		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jLabelTitle, null);		
		this.getContentPane().add(jLabelPrice, null);
		this.getContentPane().add(fieldPrice, null);

		fieldTitle.setColumns(10);
		fieldDescription.setColumns(10);

		getContentPane().add(jButtonDecline);
		getContentPane().add(jButtonAccept);
		getContentPane().add(fieldTitle);
		getContentPane().add(jLabelUser);
		getContentPane().add(jLabelDescription);
		getContentPane().add(fieldDescription);
		getContentPane().add(fieldUser);
		getContentPane().add(jLabelDate);
		getContentPane().add(fieldDate);
		this.setVisible(true);



	}	 

}



