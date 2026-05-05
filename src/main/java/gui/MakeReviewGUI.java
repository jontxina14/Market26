package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.JTextField;

import domain.Sale;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MakeReviewGUI extends JFrame{
	private JTextField textFieldDesc;
	public MakeReviewGUI(String currentUserMail, Sale s ) {
		
		this.setSize(new Dimension(550,350));
		
		
		getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MakeReviewGUI.title"));
		lblTitle.setBounds(140, 26, 200, 50);
		getContentPane().add(lblTitle);
		
		JLabel lblDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MakeReviewGUI.description"));
		lblDescription.setBounds(10, 137, 120, 27);
		getContentPane().add(lblDescription);
		
		textFieldDesc = new JTextField();
		textFieldDesc.setBounds(25, 180, 455, 50);
		getContentPane().add(textFieldDesc);
		textFieldDesc.setColumns(10);
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(35, 240, 200, 35);
		getContentPane().add(btnClose);
		
		JButton btnSend = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MakeReviewGUI.send"));
		btnSend.setBounds(264, 240, 200, 35);
		getContentPane().add(btnSend);
	}
}
