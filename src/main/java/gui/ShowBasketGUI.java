package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Sale;
import domain.SaleContainer;
import enums.MovementType;
import enums.QueryFilterType;
import enums.SaleType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class ShowBasketGUI extends JFrame {

	private String currentMail;
	private SaleType saleType;

	private static final long serialVersionUID = 1L;
	private final JLabel jLabelProducts = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.initialize")); 
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private JScrollPane scrollPanelProducts = new JScrollPane();

	private JTable tableProducts= new JTable();
	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 

	private String[] columnNamesProducts = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Title"), 
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"),
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.PublicationDate"),

	};

	private String QueryMessagge = "";
	private String emptyQueryMessagge = "";

	private ArrayList<Sale> basket = new ArrayList<Sale>();
	private String sellerEmail = "";



	public ShowBasketGUI(ArrayList<Sale> basket) {
		this.basket=basket;

		tableProducts.setEnabled(false);
		//thisFrame=this;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.FindProducts"));

		jLabelProducts.setBounds(52, 50, 427, 16);
		this.getContentPane().add(jLabelProducts);

		jButtonClose.setBounds(new Rectangle(220, 379, 130, 30));
		jButtonClose.addActionListener(e -> thisFrame.setVisible(false));
		this.getContentPane().add(jButtonClose, null);

		scrollPanelProducts.setBounds(new Rectangle(52, 76, 459, 211));
		scrollPanelProducts.setViewportView(tableProducts);

		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
		tableProducts.setModel(tableModelProducts);

		tableModelProducts.setDataVector(null, columnNamesProducts);

		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(70);
		
		refreshQuery();



		this.getContentPane().add(scrollPanelProducts, null);
		
		JLabel jLabelTotalPrice = new JLabel((String) null);
		jLabelTotalPrice.setBounds(52, 309, 141, 16);
		getContentPane().add(jLabelTotalPrice);
		
		JLabel jLabelTotalPriceAns = new JLabel((String) null);
		jLabelTotalPriceAns.setBounds(220, 309, 141, 16);
		getContentPane().add(jLabelTotalPriceAns);


		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				if(mouseEvent.getClickCount() == 2)
				{
					
				}
			}
		});



	}

	public void refreshQuery() {
		try {
			System.out.println("BasketGUI: " + basket);
			tableModelProducts.setDataVector(null, columnNamesProducts);

			BLFacade facade = MainGUInonReg.getBusinessLogic();
			Date today = UtilDate.trim(new Date());


			if (basket.isEmpty()) 	jLabelProducts.setText(emptyQueryMessagge);
			else 					jLabelProducts.setText(QueryMessagge);

			for (Sale sale : basket){
				Vector<Object> row = new Vector<Object>();
				row.add(sale.getTitle());
				row.add(sale.getPrice());
				row.add(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
				row.add(sale); // product object added in order to obtain it with tableModelProducts.getValueAt(i,2)
				tableModelProducts.addRow(row);		
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(70);
		//tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(3)); // not shown in JTable
	}
}
