package gui;

import businessLogic.BLFacade;
import domain.Complaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class QueryComplaintGUI extends JFrame {


    private static final long serialVersionUID = 1L;
    private final JLabel jLabelReports = new JLabel("Reports");

    private JButton jButtonSearch = new JButton("Search");
    private JButton jButtonClose = new JButton("Close");

    private JScrollPane scrollPanel = new JScrollPane();

    private JTable table = new JTable();
    private DefaultTableModel tableModel;

    private JFrame thisFrame;

    private String[] columnNames = new String[] {
    		//TODO internalizazioa
            "IDComplaint",
            "Title",
            "User",
            "Date"
    };
    
    private List<Complaint> complaints;

    public QueryComplaintGUI() {
        

        table.setEnabled(false);
        thisFrame = this;
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(643, 470));
       
        //TODO INTERNALIZAZIOA
        this.setTitle("Reports");
        jLabelReports.setFont(new Font("Tahoma", Font.PLAIN, 14));

        jLabelReports.setBounds(52, 55, 427, 16);
        this.getContentPane().add(jLabelReports);

        jButtonClose.setBounds(new Rectangle(52, 355, 141, 30));
        jButtonClose.addActionListener(e -> thisFrame.setVisible(false));
        this.getContentPane().add(jButtonClose, null);

        scrollPanel.setBounds(new Rectangle(52, 104, 497, 211));
        scrollPanel.setViewportView(table);

        tableModel = new DefaultTableModel(null, columnNames);
        table.setModel(tableModel);

        this.getContentPane().add(scrollPanel);

        jButtonSearch.setBounds(408, 49, 141, 29);
        getContentPane().add(jButtonSearch);

        jButtonSearch.addActionListener(e -> refreshQuery());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2) {
                	int row = table.rowAtPoint(mouseEvent.getPoint());
                	Complaint c = complaints.get(row);
                	JFrame a = new ShowComplaintGUI(c);
    				a.setVisible(true);

                }
            }
        });
    }

    public void refreshQuery() {
        try {
            tableModel.setDataVector(null, columnNames);

            BLFacade facade = MainGUInonReg.getBusinessLogic();

            complaints = facade.getComplaints();

            if (!complaints.isEmpty()) 
                jLabelReports.setText("Complaints");
            else 
                jLabelReports.setText("No Complaints");

            for (Complaint complaint : complaints){
                Vector<Object> row = new Vector<Object>();
                row.add(complaint.getId());
                row.add(complaint.getSale().getTitle());
                row.add(complaint.getUser().getEmail());
                row.add(new SimpleDateFormat("dd-MM-yyyy").format(complaint.getDate()));
                tableModel.addRow(row);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //table.getColumnModel().getColumn(0).setPreferredWidth(80);
        //table.getColumnModel().getColumn(1).setPreferredWidth(200);
        //table.getColumnModel().getColumn(2).setPreferredWidth(150);
        //table.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
}