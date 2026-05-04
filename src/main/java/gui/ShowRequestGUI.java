package gui;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;

import businessLogic.BLFacade;
import domain.Request;
import domain.Sale;
import domain.SaleContainer;


public class ShowRequestGUI extends JFrame {
	
    File targetFile;
    BufferedImage targetImg;
    private static final int baseSize = 220;
	private static final String basePath="src/main/resources/images/";
	
	private static final long serialVersionUID = 1L;

	private JTextField fieldTitle=new JTextField();
	private JTextField fieldDescription=new JTextField();

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.Title"));
	private JLabel jLabelDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Description")); 
	private JLabel jLabelSuggestedPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"));
	private JTextField fieldSuggestedPrice = new JTextField();
	private File selectedFile;
    private String irudia;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	DefaultComboBoxModel<String> statusOptions = new DefaultComboBoxModel<String>();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	JButton jButtonMakeAnOffer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowRequestGUI.MakeAnOffer"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel statusField=new JLabel();
	private JFrame thisFrame;
	
	public ShowRequestGUI(Request request, String currentMail) { 
		thisFrame=this; 
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(900, 500));
		this.setLocationRelativeTo(null);

		fieldTitle.setText(request.getTitle());
		fieldDescription.setText(request.getDescription());

		fieldSuggestedPrice.setText(Double.toString(request.getPrice()));
		
		jLabelTitle.setBounds(new Rectangle(50, 50, 140, 25));
		
		jLabelSuggestedPrice.setBounds(new Rectangle(50, 220, 140, 25));
		fieldSuggestedPrice.setEditable(false);
		fieldSuggestedPrice.setBounds(new Rectangle(200, 220, 100, 30));

		
		scrollPaneEvents.setBounds(new Rectangle(50, 280, 346, 80));
		jButtonClose.setBounds(new Rectangle(250, 380, 150, 40));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);			}
		});

		jLabelMsg.setBounds(new Rectangle(320, 225, 305, 20));
		jLabelMsg.setForeground(Color.red);

		

		this.getContentPane().add(jLabelMsg, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jLabelTitle, null);
		
		
		this.getContentPane().add(jLabelSuggestedPrice, null);
		this.getContentPane().add(fieldSuggestedPrice, null);
		
		jLabelDescription.setBounds(50, 95, 140, 25);
		getContentPane().add(jLabelDescription);
		fieldTitle.setEditable(false);
		
		
		fieldTitle.setBounds(200, 50, 630, 30);
		getContentPane().add(fieldTitle);
		fieldTitle.setColumns(10);
		fieldDescription.setEditable(false);
		
		
		fieldDescription.setBounds(200, 95, 630, 100);
		getContentPane().add(fieldDescription);
		fieldDescription.setColumns(10);
		jButtonMakeAnOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new MakeAnOfferGUI(currentMail,request);
				a.setVisible(true);
			}
		});
		
		jButtonMakeAnOffer.setBounds(450, 380, 150, 40);
		getContentPane().add(jButtonMakeAnOffer);
				
	}	 
	
}

