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

public class ShowSaleGUIReg extends ShowSaleGUInonReg{

	private JToggleButton toggleWishListButton;
	private QueryGUI parent;

	public ShowSaleGUIReg(String currentUserMail, Sale sale, JFrame p) {
		super(sale);

		this.parent = (QueryGUI) p;

		BLFacade facade = MainGUInonReg.getBusinessLogic();

		JButton buyButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.buyButton")); //$NON-NLS-1$ //$NON-NLS-2$
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(sale.getSaleNumber());
				BLFacade facade = MainGUInonReg.getBusinessLogic();
				try {
					boolean b = facade.buySale(currentUserMail, sale.getSaleNumber());
					if (b) {
						//Aurreko pantaila errefreskatu, berriro bilaturi eman beharrik gabe
						parent.refreshQuery();

						dispose();
					}
				}catch(Exception e) {
					e.printStackTrace();
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
