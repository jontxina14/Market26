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
import exceptions.NotEnoughMoneyException;

public class ShowSaleGUIBought extends ShowSaleGUInonReg {

	private JToggleButton toggleWishListButton;
	private JLabel jLabelError = new JLabel();
	private QueryGUI parent;
	JButton complaintButton;

	public ShowSaleGUIBought(String currentUserMail, Sale sale, JFrame p) {
		super(sale);

		this.parent = (QueryGUI) p;

		BLFacade facade = MainGUInonReg.getBusinessLogic();

		jLabelError.setBounds(new Rectangle(6, 350, 320, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelError, null);


		complaintButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.complaintButton")); //$NON-NLS-1$ //$NON-NLS-2$


		Sale freshSale = facade.getSale(sale.getSaleNumber());
		if(freshSale.hasAnyComplaint()) {
			complaintButton.setEnabled(false);
			complaintButton.setToolTipText(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.complaintPending"));
		}


		//BIDEO (Eager complaint)
		complaintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFrame a = new MakeComplaintGUI(currentUserMail, sale);
				a.setVisible(true);

			}
		});
		complaintButton.setBounds(300, 380, 250, 40);
		getContentPane().add(complaintButton);


	}
}
