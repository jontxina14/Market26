package gui;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;

import businessLogic.BLFacade;
import domain.Sale;
import domain.SaleContainer;
import enums.QueryFilterType;
import exceptions.NotEnoughMoneyException;

public class ShowSaleGUIReg extends ShowSaleGUInonReg{

	private JToggleButton toggleWishListButton;
	private JLabel jLabelError = new JLabel();
	private QuerySaleGUI parent;

	public ShowSaleGUIReg(String currentUserMail, Sale s, JFrame p) {
		super(s);

		this.parent = (QuerySaleGUI) p;

		BLFacade facade = MainGUInonReg.getBusinessLogic();

		jLabelError.setBounds(new Rectangle(6, 350, 320, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelError, null);


		JButton buyButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.buyButton")); //$NON-NLS-1$ //$NON-NLS-2$
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(s.getSaleNumber());


				//Aurreko pantaila errefreskatu, berriro bilaturi eman beharrik gabe
				parent.addTotheBasket(s,s.getSeller().getEmail());
				parent.refreshQuery();
				dispose();





			}
		});
		buyButton.setBounds(320, 380, 140, 40);
		getContentPane().add(buyButton);


		//Wish list
		ImageIcon emptyIcon = new ImageIcon(getClass().getResource("/images/heart_empty.png"));
		ImageIcon filledIcon = new ImageIcon(getClass().getResource("/images/heart_filled.png"));
		boolean dago = facade.isInWishList(currentUserMail, s.getSaleNumber());

		//JToggleButton selekzionatuta mantentzen delako
		toggleWishListButton = new JToggleButton(dago ? filledIcon : emptyIcon);
		toggleWishListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				facade.toggleWishList(currentUserMail, s.getSaleNumber());
				boolean dago = facade.isInWishList(currentUserMail, s.getSaleNumber());
				toggleWishListButton.setIcon(dago ? filledIcon : emptyIcon);
			}
		});
		toggleWishListButton.setBorderPainted(false);
		toggleWishListButton.setContentAreaFilled(false);
		toggleWishListButton.setBounds(678, 50, 36, 30);
		getContentPane().add(toggleWishListButton);

		JButton reportButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.reportButton"));
		if(facade.hasReported(currentUserMail,s)) {
			reportButton.setEnabled(false);
			reportButton.setToolTipText(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.AlredyReported"));
		}

		reportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MakeReportGUI(currentUserMail, s).setVisible(true);
				dispose();
			}
		});

		reportButton.setBounds(738, 50, 100, 30);
		getContentPane().add(reportButton);

	}
}
