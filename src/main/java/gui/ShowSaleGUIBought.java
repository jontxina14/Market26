package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Sale;
import domain.SaleContainer;
import exceptions.NotEnoughMoneyException;

public class ShowSaleGUIBought extends ShowSaleGUInonReg {

	private JToggleButton toggleWishListButton;
	private JLabel jLabelError = new JLabel();
	private QuerySaleGUI parent;
	JButton complaintButton;
	JButton reviewtButton;

	public ShowSaleGUIBought(String currentUserMail, Sale s, JFrame p) {
		super(s);

		this.parent = (QuerySaleGUI) p;

		BLFacade facade = MainGUInonReg.getBusinessLogic();

		jLabelError.setBounds(new Rectangle(6, 350, 320, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelError, null);


		complaintButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.complaintButton")); //$NON-NLS-1$ //$NON-NLS-2$


		Sale freshSale = facade.getSale(s.getSaleNumber());
		if(freshSale.hasAnyComplaint()) {
			complaintButton.setEnabled(false);
			complaintButton.setToolTipText(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.complaintPending"));
		}

		complaintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new MakeComplaintGUI(currentUserMail, s);
				a.setVisible(true);
				dispose();
			}
		});
		complaintButton.setBounds(320, 380, 250, 40);
		getContentPane().add(complaintButton);

		
		
		reviewtButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.reviewButton"));
		reviewtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new MakeReviewGUI(currentUserMail, s);
				a.setVisible(true);
				dispose();
			}
		});
		reviewtButton.setBounds(600, 380, 250, 40);
		getContentPane().add(reviewtButton);


	}
}