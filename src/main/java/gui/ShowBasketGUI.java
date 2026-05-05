package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Sale;
import domain.SaleContainer;
import enums.MovementType;
import enums.QueryFilterType;
import enums.SaleType;
import exceptions.NotEnoughMoneyException;

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
	private QuerySaleGUI parent;

	private static final long serialVersionUID = 1L;
	private final JLabel jLabelProducts = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.initialize")); 
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton jButtonBuy = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowBasketGUI.buyButton"));
	private JLabel jLabelError = new JLabel((String)null); //$NON-NLS-1$ //$NON-NLS-2$
	private JLabel jLabelTotalPriceAns = new JLabel((String) null);
	private	JLabel jLabelTotalPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowBasketGUI.totalPrice"));



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



	public ShowBasketGUI(String mail, ArrayList<Sale> basket, JFrame p) {
		this.basket=basket;
		this.currentMail=mail;
		this.parent = (QuerySaleGUI) p;
		
		tableProducts.setEnabled(false);
		//thisFrame=this;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.FindProducts"));

		jLabelProducts.setBounds(50, 30, 450, 20);
		this.getContentPane().add(jLabelProducts);

		jButtonClose.setBounds(new Rectangle(190, 400, 150, 35));
		jButtonClose.addActionListener(e -> dispose());
		this.getContentPane().add(jButtonClose, null);

		jButtonBuy.setBounds(new Rectangle(360, 400, 150, 35));
		jButtonBuy.addActionListener(e -> {
			BLFacade facade = MainGUInonReg.getBusinessLogic();

			if(basket.isEmpty()) {
				jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ShowBasketGUI.EmptyBasket"));
			}else {

				ArrayList<Integer> numbBasket = new ArrayList<Integer>();
				for(Sale s:basket) {
					numbBasket.add(s.getSaleNumber());
				}


				try{
					facade.buySale(currentMail, numbBasket);
					basket.clear();
					dispose();
				}catch (NotEnoughMoneyException ex){
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.NotEnoughMoney"));
				}
			}
		});
		this.getContentPane().add(jButtonBuy, null);

		scrollPanelProducts.setBounds(new Rectangle(50, 60, 580, 230));
		scrollPanelProducts.setViewportView(tableProducts);

		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
		tableProducts.setModel(tableModelProducts);

		tableModelProducts.setDataVector(null, columnNamesProducts);

		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(300);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(100);

		refreshQuery();



		this.getContentPane().add(scrollPanelProducts, null);

		jLabelTotalPrice.setBounds(50, 310, 150, 20);
		getContentPane().add(jLabelTotalPrice);

		jLabelTotalPriceAns.setBounds(200, 310, 150, 20);
		getContentPane().add(jLabelTotalPriceAns);
		int prezioTot=0;
		for(Sale s:basket) {
			prezioTot += s.getPrice();
		}
		jLabelTotalPriceAns.setText(String.valueOf(prezioTot));

		jLabelError.setBounds(50, 350, 400, 25);
		getContentPane().add(jLabelError);


		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				if(mouseEvent.getClickCount() == 2)
				{
					JTable table = (JTable) mouseEvent.getSource();
		            Point point = mouseEvent.getPoint();
		            int row = table.rowAtPoint(point);
		            if (row >= 0) {
		            	System.out.println("row: "+row);
		                Sale s = basket.remove(row);
		                refreshQuery();
		                System.out.println("\nSaskitik ezabatua: "+s);
		                parent.removeFromBasket(s);
		                parent.refreshQuery();
		                
		                int prezioTot = 0;
		                for (Sale sale : basket) {
		                    prezioTot += sale.getPrice();
		                }
		                jLabelTotalPriceAns.setText(String.valueOf(prezioTot));
		            }
				}
			}
		});



	}

	public void refreshQuery() {
		try {
			System.out.println("BasketGUI_basket: " + basket);
			tableModelProducts.setDataVector(null, columnNamesProducts);



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
		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(300);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(100);
		//tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(3)); // not shown in JTable
	}
}
