package gui;

import businessLogic.BLFacade;
import domain.Complaint;
import domain.ComplaintContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class QueryComplaintGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JLabel jLabelComplaints = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryComplaintGUI.Title"));

    private JButton jButtonSearch = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Search"));
    private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

    private JScrollPane scrollPanel = new JScrollPane();
    private JTable table = new JTable();
    private DefaultTableModel tableModel;
    private JFrame thisFrame;
    private String[] columnNames;
    private List<ComplaintContainer> complaints;

    public QueryComplaintGUI() {

        ResourceBundle bundle = ResourceBundle.getBundle("Etiquetas");

        columnNames = new String[] {
            bundle.getString("QueryComplaintGUI.IDComplaint"),
            bundle.getString("QueryComplaintGUI.ComplaintTitle"),
            bundle.getString("QueryComplaintGUI.User"),
            bundle.getString("QueryComplaintGUI.Date")
        };

        table.setEnabled(false);
        thisFrame = this;
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(643, 470));

        this.setTitle(bundle.getString("QueryComplaintGUI.Title"));
        jLabelComplaints.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLabelComplaints.setBounds(52, 55, 427, 16);
        this.getContentPane().add(jLabelComplaints);

        jButtonClose.setBounds(new Rectangle(52, 355, 141, 30));
        jButtonClose.setBackground(new Color(231, 76, 60));
        jButtonClose.setForeground(Color.WHITE);
        jButtonClose.setFont(new Font("Tahoma", Font.BOLD, 12));
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
                if (mouseEvent.getClickCount() == 2) {
                    int row = table.rowAtPoint(mouseEvent.getPoint());
                    ComplaintContainer c = complaints.get(row);
                    JFrame a = new ShowComplaintGUI(c,thisFrame);
                    a.setVisible(true);
                }
            }
        });
    }

    public void refreshQuery() {

        ResourceBundle bundle = ResourceBundle.getBundle("Etiquetas");

        try {
            tableModel.setDataVector(null, columnNames);
            BLFacade facade = MainGUInonReg.getBusinessLogic();
            complaints = facade.getComplaints();

            if (!complaints.isEmpty())
                jLabelComplaints.setText(bundle.getString("QueryComplaintGUI.Complaints"));
            else
                jLabelComplaints.setText(bundle.getString("QueryComplaintGUI.NoComplaints"));

            for (ComplaintContainer complaint : complaints) {
                Vector<Object> row = new Vector<Object>();
                row.add(complaint.getComplaint().getId());
                row.add(complaint.getSale().getTitle());
                row.add(complaint.getUser().getEmail());
                row.add(new SimpleDateFormat("dd-MM-yyyy").format(complaint.getComplaint().getDate()));
                tableModel.addRow(row);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}