package gui;

import java.util.*;

import javax.swing.*;

import businessLogic.BLFacade;
import domain.Report;
import domain.ReportContainer;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;


public class ShowReportGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField fieldTitle=new JTextField();
	private JTextField fieldCause=new JTextField();
	private final JTextField fieldUser = new JTextField();
	private final JTextField fieldDate = new JTextField();	

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Title"));
	private JLabel jLabelCause = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Cause")); 
	private JLabel jLabelUser = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.User"));
	private final JLabel jLabelDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Date"));
	private final JButton jButtonDecline = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Decline"));
	private final JButton jButtonBan = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Ban"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Close"));
	private JButton jButtonShowSale = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.ShowSale"));

	private JLabel jLabelMsg = new JLabel();
	private JFrame thisFrame;
	
	private QueryReportGUI parent;


	public ShowReportGUI(ReportContainer report, JFrame p) {
		thisFrame=this; 
		
		this.parent = (QueryReportGUI) p;

		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 360));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		BLFacade facade = MainGUInonReg.getBusinessLogic();
		
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ShowReportGUI.Name"));

		fieldTitle.setText(
			    report.getSale() != null ? report.getSale().getTitle() : "null"
			);

		fieldUser.setText(
			    report.getUser() != null ? report.getUser().getEmail() : "null"
			);

		fieldDate.setText(
			    report.getReport().getDate() != null 
			        ? new SimpleDateFormat("dd-MM-yyyy").format(report.getReport().getDate()) 
			        : "null"
			);

		fieldCause.setText(
			    report.getReport().getCause() != null ? report.getReport().getCause().toString() : "null"
			);

		
		jLabelTitle.setBounds(50, 30, 250, 25);
		jLabelUser.setBounds(50, 75, 250, 25);
		jLabelDate.setBounds(50, 120, 250, 25);
		jLabelCause.setBounds(50, 165, 250, 25);

		fieldTitle.setBounds(220, 30, 400, 30);
		fieldUser.setBounds(220, 75, 300, 30);
		fieldDate.setBounds(220, 120, 120, 30);
		fieldCause.setBounds(220, 165, 200, 30);

		jButtonClose.setBounds(40, 250, 140, 40);
		jButtonDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (report != null && facade != null) {
				    facade.declineReport(report.getReport());
				}

				if (parent != null) {
				    parent.refreshQuery();
				}

				dispose();
			}
		});
		
		
		jButtonDecline.setBounds(200, 250, 140, 40);
		jButtonBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.adminReport(report.getReport());
				parent.refreshQuery();
				dispose();
			}
		});
		
		
		jButtonBan.setBounds(360, 250, 140, 40);
		jButtonShowSale.setBounds(520, 250, 140, 40);

		fieldTitle.setEditable(false);
		fieldCause.setEditable(false);
		fieldUser.setEditable(false);
		fieldDate.setEditable(false);



		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);			
			}
		});
		
		
		
		
		jButtonShowSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ShowSaleGUInonReg(report.getReport().getSale());
			}
		});	
		

		jLabelMsg.setBounds(new Rectangle(220, 210, 300, 20));
		jLabelMsg.setForeground(Color.red);



		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jLabelTitle, null);

		fieldTitle.setColumns(10);
		fieldCause.setColumns(10);

		getContentPane().add(jButtonDecline);
		getContentPane().add(jButtonBan);
		getContentPane().add(fieldTitle);
		getContentPane().add(jLabelUser);
		getContentPane().add(jLabelCause);
		getContentPane().add(fieldCause);
		getContentPane().add(fieldUser);
		getContentPane().add(jLabelDate);
		getContentPane().add(fieldDate);
		getContentPane().add(jButtonShowSale);
		this.setVisible(true);

	}	 
}



