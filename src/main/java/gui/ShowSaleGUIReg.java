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
import exceptions.NotEnoughMoneyException;

public class ShowSaleGUIReg extends ShowSaleGUInonReg{

	private JToggleButton toggleWishListButton;
	private JLabel jLabelError = new JLabel();
	private QueryGUI parent;

	public ShowSaleGUIReg(String currentUserMail, Sale sale, JFrame p) {
		super(sale);

		this.parent = (QueryGUI) p;

		BLFacade facade = MainGUInonReg.getBusinessLogic();
		
		jLabelError.setBounds(new Rectangle(6, 350, 320, 20));
		jLabelError.setForeground(Color.red);
		
		this.getContentPane().add(jLabelError, null);

		

		JButton buyButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.buyButton")); //$NON-NLS-1$ //$NON-NLS-2$
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(sale.getSaleNumber());
				try {
					boolean b = facade.buySale(currentUserMail, sale.getSaleNumber());
					if (b) {
						//Aurreko pantaila errefreskatu, berriro bilaturi eman beharrik gabe
						parent.refreshQuery();

						dispose();
					}
				}catch(NotEnoughMoneyException e) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.NotEnoughMoney"));
				}
				
				

			}
		});
		buyButton.setBounds(320, 380, 140, 40);
		getContentPane().add(buyButton);


		//Wish list
		ImageIcon emptyIcon = new ImageIcon(getClass().getResource("/images/heart_empty.png"));
		ImageIcon filledIcon = new ImageIcon(getClass().getResource("/images/heart_filled.png"));
		boolean dago = facade.isInWishList(currentUserMail, sale.getSaleNumber());

		//JToggleButton selekzionatuta mantentzen delako
		toggleWishListButton = new JToggleButton(dago ? filledIcon : emptyIcon);
		toggleWishListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				facade.toggleWishList(currentUserMail, sale.getSaleNumber());
				boolean dago = facade.isInWishList(currentUserMail, sale.getSaleNumber());
				toggleWishListButton.setIcon(dago ? filledIcon : emptyIcon);
			}
		});
		toggleWishListButton.setBorderPainted(false);
		toggleWishListButton.setContentAreaFilled(false);
		toggleWishListButton.setBounds(678, 50, 36, 30);
		getContentPane().add(toggleWishListButton);

	}
}
