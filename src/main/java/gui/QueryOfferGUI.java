package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Offer;
import domain.OfferContainer;
import domain.Request;
import domain.Sale;
import domain.SaleContainer;
import enums.MovementType;
import enums.QueryFilterType;
import enums.SaleStatusType;
import enums.SaleType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class QueryOfferGUI extends JFrame {

	private String currentMail;
	private SaleType saleType;

	private static final long serialVersionUID = 1L;
	private final JLabel jLabelProducts = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.initialize")); 

	private JButton jButtonSearch = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Search")); 
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private JScrollPane scrollPanelProducts = new JScrollPane();

	private JTable tableProducts= new JTable();
	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 

	private String[] columnNamesProducts = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Title"), 
			ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Status"), 
			ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Price")	
	};

	private JTextField jTextFieldSearch;

	private String QueryMessagge = "";
	private String emptyQueryMessagge = "";


	public QueryOfferGUI(String currentUserMail) {
		this.currentMail = currentUserMail;

		tableProducts.setEnabled(false);
		thisFrame=this;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.FindProducts"));

		jLabelProducts.setBounds(52, 108, 427, 16);
		this.getContentPane().add(jLabelProducts);

		jButtonClose.setBounds(new Rectangle(220, 379, 130, 30));
		jButtonClose.addActionListener(e -> thisFrame.setVisible(false));
		this.getContentPane().add(jButtonClose, null);

		scrollPanelProducts.setBounds(new Rectangle(52, 137, 459, 150));
		scrollPanelProducts.setViewportView(tableProducts);

		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
		tableProducts.setModel(tableModelProducts);

		tableModelProducts.setDataVector(null, columnNamesProducts);
		tableModelProducts.setColumnCount(4); // another column added to allocate ride objects

		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(40);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(20);

		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(3)); // not shown in JTable

		this.getContentPane().add(scrollPanelProducts, null);

		jTextFieldSearch = new JTextField();
		jTextFieldSearch.setBounds(52, 56, 357, 26);
		getContentPane().add(jTextFieldSearch);
		jTextFieldSearch.setColumns(10);


		jButtonSearch.setBounds(427, 56, 117, 29);
		getContentPane().add(jButtonSearch);


		//TODO
		QueryMessagge = ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.OnSale");
		emptyQueryMessagge = ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.NoOnSale");


		jButtonSearch.addActionListener(e -> refreshQuery());


		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				if(mouseEvent.getClickCount() == 2)
				{
					JTable table =(JTable) mouseEvent.getSource();
					Point point = mouseEvent.getPoint();
					int row = table.rowAtPoint(point);
					OfferContainer o = (OfferContainer) tableModelProducts.getValueAt(row, 3);
					System.out.println(currentUserMail);

					JFrame a = new ShowOfferGUI(o,currentUserMail);
					a.setVisible(true);
				}
			}
		});



	}

	public void refreshQuery() {
		try {
			tableModelProducts.setDataVector(null, columnNamesProducts);
			tableModelProducts.setColumnCount(4); // another column added to allocate product object

			BLFacade facade = MainGUInonReg.getBusinessLogic();

			//Query deia
			List<OfferContainer> offers = facade.getOffers(currentMail);

			if (offers.isEmpty()) 	jLabelProducts.setText(emptyQueryMessagge);
			else 					jLabelProducts.setText(QueryMessagge);

			List<String> stList = Utils.getStatus();
			
			
			for (OfferContainer offer : offers){
				Vector<Object> row = new Vector<Object>();
				row.add(offer.getRequest().getTitle());
				row.add(stList.get(offer.getOffer().getStatus()));
				row.add(offer.getOffer().getPrice());
				row.add(offer); 
				tableModelProducts.addRow(row);		
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(40);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(20);
		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(3)); // not shown in JTable
	}

}


