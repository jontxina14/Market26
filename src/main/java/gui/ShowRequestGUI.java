package gui;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.image.BufferedImage;


import domain.RequestContainer;

public class ShowRequestGUI extends JFrame {
	
    File targetFile;
    BufferedImage targetImg;

	
	private static final long serialVersionUID = 1L;

	private JTextField fieldTitle=new JTextField();
	private JTextField fieldDescription=new JTextField();

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.Title"));
	private JLabel jLabelDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Description")); 
	private JLabel jLabelSuggestedPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"));
	private JTextField fieldSuggestedPrice = new JTextField();

	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton jButtonMakeAnOffer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowRequestGUI.MakeAnOffer"));
	private JLabel jLabelMsg = new JLabel();
	private JFrame thisFrame;
	
	public ShowRequestGUI(RequestContainer r, String currentMail, boolean hasOffer) { 
		thisFrame=this; 
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(900, 500));
		this.setLocationRelativeTo(null);

		fieldTitle.setText(r.getRequest().getTitle());
		fieldDescription.setText(r.getRequest().getDescription());

		fieldSuggestedPrice.setText(Double.toString(r.getRequest().getPrice()));
		
		jLabelTitle.setBounds(new Rectangle(50, 50, 140, 25));
		
		jLabelSuggestedPrice.setBounds(new Rectangle(50, 220, 140, 25));
		fieldSuggestedPrice.setEditable(false);
		fieldSuggestedPrice.setBounds(new Rectangle(200, 220, 100, 30));

		
		scrollPaneEvents.setBounds(new Rectangle(50, 280, 346, 80));
		jButtonClose.setBounds(new Rectangle(250, 380, 150, 40));
		jButtonClose.setBackground(new Color(231, 76, 60));
		jButtonClose.setForeground(Color.WHITE);
		jButtonClose.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		
		jButtonMakeAnOffer.setEnabled(!hasOffer);
		jButtonMakeAnOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new MakeAnOfferGUI(currentMail,r,thisFrame);
				a.setVisible(true);
			}
		});
		
		jButtonMakeAnOffer.setBounds(450, 380, 150, 40);
		getContentPane().add(jButtonMakeAnOffer);
				
	}
	
	public void lehioaItxi() {
		this.dispose();
	}
	
}

