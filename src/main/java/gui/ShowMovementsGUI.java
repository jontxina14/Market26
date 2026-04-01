package gui;

import businessLogic.BLFacade;
import domain.Movement;
import enums.MovementType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class ShowMovementsGUI extends JFrame {

	private String currentMail;
	private MovementType type;

	private static final long serialVersionUID = 1L;
	private final JLabel jLabelChoose = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowMovementsGUI.Search")); 
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowMovementsGUI.Close"));

	private JScrollPane scrollPanelProducts = new JScrollPane();

	private JTable tableProducts= new JTable();
	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 

	private String[] columnNamesProducts = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("ShowMovementsGUI.Type"), 
			ResourceBundle.getBundle("Etiquetas").getString("ShowMovementsGUI.Date"),
			ResourceBundle.getBundle("Etiquetas").getString("ShowMovementsGUI.Amount"),
			ResourceBundle.getBundle("Etiquetas").getString("ShowMovementsGUI.AmountAfter"),

	};
	
	private JLabel jLabelMessage;
	
	private JComboBox<MovementType> comboBox;


	public ShowMovementsGUI(String currentUserMail) {
		this.currentMail = currentUserMail;
		
		tableProducts.setEnabled(false);
		thisFrame=this;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("ShowMovementsGUI.Title"));
		jLabelChoose.setBounds(52, 40, 427, 16);
		this.getContentPane().add(jLabelChoose);

		jButtonClose.setBounds(new Rectangle(220, 379, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				thisFrame.setVisible(false);
			}
		});		
		this.getContentPane().add(jButtonClose, null);



		scrollPanelProducts.setBounds(new Rectangle(52, 148, 552, 189));

		scrollPanelProducts.setViewportView(tableProducts);
		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);

		tableProducts.setModel(tableModelProducts);

		tableModelProducts.setDataVector(null, columnNamesProducts);
		tableModelProducts.setColumnCount(5); // another column added to allocate ride objects

		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableProducts.getColumnModel().getColumn(3).setPreferredWidth(50);



		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(4)); // not shown in JTable

		this.getContentPane().add(scrollPanelProducts, null);

		jLabelMessage = new JLabel(""); 
		jLabelMessage.setBounds(52, 122, 427, 16);
		getContentPane().add(jLabelMessage);

		comboBox = new JComboBox<>(MovementType.values());
		comboBox.setBounds(52, 66, 427, 30);
		getContentPane().add(comboBox);
		

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Search"));
		btnNewButton.addActionListener(e -> refreshQuery());
		btnNewButton.setBounds(503, 71, 84, 20);
		getContentPane().add(btnNewButton);
		
		



	}

	public void refreshQuery() {
		try {
			this.type = (MovementType)comboBox.getSelectedItem();
			tableModelProducts.setDataVector(null, columnNamesProducts);
			tableModelProducts.setColumnCount(4); // another column added to allocate product object

			BLFacade facade = MainGUInonReg.getBusinessLogic();

			//Query deia
			List<Movement> movements=facade.getMovements(currentMail,type);

			if (movements.isEmpty() ) {
				jLabelMessage.setText(ResourceBundle.getBundle("Etiquetas").getString("ShowMovementsGUI.Error"));
			}else {
				jLabelMessage.setText(ResourceBundle.getBundle("Etiquetas").getString("ShowMovementsGUI.Movements"));
			}
			
			for (domain.Movement movement:movements){
				Vector<Object> row = new Vector<Object>();
				row.add(movement.getType());
				row.add(new SimpleDateFormat("dd-MM-yyyy").format(movement.getDate()));
				row.add(movement.getAmount());
				row.add(movement.getBalanceAfter()); // product object added in order to obtain it with tableModelProducts.getValueAt(i,2)
				tableModelProducts.addRow(row);		
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
