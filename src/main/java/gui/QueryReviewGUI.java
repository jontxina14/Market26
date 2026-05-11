package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Offer;
import domain.OfferContainer;
import domain.Request;
import domain.Review;
import domain.ReviewContainer;
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


public class QueryReviewGUI extends JFrame {

	private String currentMail;

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
			ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.rating"), 
			ResourceBundle.getBundle("Etiquetas").getString("QueryReportGUI.Date")	
	};

	private JTextField jTextFieldSearch;

	private String QueryMessagge = "";
	private String emptyQueryMessagge = "";


	public QueryReviewGUI(String currentUserMail) {
		this.currentMail = currentUserMail;

		tableProducts.setEnabled(false);
		thisFrame=this;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.FindProducts"));

		jLabelProducts.setBounds(52, 108, 427, 16);
		this.getContentPane().add(jLabelProducts);

		jButtonClose.setBounds(new Rectangle(220, 379, 130, 30));
		jButtonClose.setBackground(new Color(231, 76, 60));
        jButtonClose.setForeground(Color.WHITE);
        jButtonClose.setFont(new Font("Tahoma", Font.BOLD, 12));
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


		QueryMessagge = ResourceBundle.getBundle("Etiquetas").getString("QueryReviews.Review");
		emptyQueryMessagge = ResourceBundle.getBundle("Etiquetas").getString("QueryReviews.NoReview");


		jButtonSearch.addActionListener(e -> refreshQuery());
		refreshQuery();


		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				if(mouseEvent.getClickCount() == 2)
				{
					JTable table =(JTable) mouseEvent.getSource();
					Point point = mouseEvent.getPoint();
					int row = table.rowAtPoint(point);
					//TODO
					ReviewContainer r = (ReviewContainer) tableModelProducts.getValueAt(row, 3);
					JFrame a = new ShowReviewGUI(r.getReview());
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
			List<ReviewContainer> reviews = facade.getReviews(currentMail,jTextFieldSearch.getText());

			if (reviews.isEmpty()) 	jLabelProducts.setText(emptyQueryMessagge);
			else 					jLabelProducts.setText(QueryMessagge);			

			for (ReviewContainer r : reviews){
				Vector<Object> row = new Vector<Object>();
				row.add(r.getReview().getSale().getTitle());
				row.add(r.getReview().getRating());
				row.add(r.getReview().getDate());
				row.add(r); 
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


