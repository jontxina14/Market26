package gui;

import java.util.*;
import java.util.List;

import javax.swing.*;

import businessLogic.BLFacade;
import domain.Offer;
import domain.OfferContainer;
import domain.Report;
import domain.ReportContainer;
import domain.Review;
import exceptions.NotEnoughMoneyException;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;


public class ShowReviewGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField titleTextField;
    private JTextField ratingTextField;
    private JTextField decTextField;
    private JTextField dateTextField;


	public ShowReviewGUI(Review review) {
				
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("ShowReviewGUI.title"));
		
		this.getContentPane().setLayout(null);

        JLabel titleLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Title"));
        titleLbl.setBounds(70, 60, 100, 23);
        getContentPane().add(titleLbl);

        JLabel ratingLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowProfileGUI.rating"));
        ratingLbl.setBounds(70, 110, 100, 23);
        getContentPane().add(ratingLbl);

        JLabel descLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.Description"));
        descLbl.setBounds(70, 160, 100, 23);
        getContentPane().add(descLbl);

        JLabel dateLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryReportGUI.Date"));
        dateLbl.setBounds(70, 240, 100, 23);
        getContentPane().add(dateLbl);

        titleTextField = new JTextField();
        titleTextField.setText(review.getSale().getTitle());
        titleTextField.setEditable(false);
        titleTextField.setBounds(180, 61, 146, 20);
        getContentPane().add(titleTextField);
        titleTextField.setColumns(10);

        ratingTextField = new JTextField();
        ratingTextField.setText(String.valueOf(review.getRating()));
        ratingTextField.setEditable(false);
        ratingTextField.setColumns(10);
        ratingTextField.setBounds(180, 111, 146, 20);
        getContentPane().add(ratingTextField);

        decTextField = new JTextField();
        decTextField.setText(review.getDescription());
        decTextField.setEditable(false);
        decTextField.setColumns(10);
        decTextField.setBounds(180, 161, 146, 57);
        getContentPane().add(decTextField);

        dateTextField = new JTextField();
        dateTextField.setText(new SimpleDateFormat("dd-MM-yyyy").format(review.getDate()));
        dateTextField.setEditable(false);
        dateTextField.setColumns(10);
        dateTextField.setBounds(180, 241, 146, 20);
        getContentPane().add(dateTextField);
        
        JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
        btnClose.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        	}
        });
        btnClose.setBackground(new Color(231, 76, 60));
        btnClose.setForeground(Color.WHITE);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnClose.setBounds(164, 290, 126, 35);
        getContentPane().add(btnClose);

        this.setSize(new Dimension(460, 380));
        this.setLocationRelativeTo(null);


	}	 
}



