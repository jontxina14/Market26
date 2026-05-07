package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Sale;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class MakeReviewGUI extends JFrame{
	private JTextField textFieldDesc;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public MakeReviewGUI(String currentUserMail, Sale s ) {
		
		this.setSize(new Dimension(550,350));
		
		BLFacade facade = MainGUInonReg.getBusinessLogic();
		
		ImageIcon emptyIcon = new ImageIcon(getClass().getResource("/images/estrella_vacia.png"));
		ImageIcon filledIcon = new ImageIcon(getClass().getResource("/images/estrella_llena.png"));
		
		String sellerEmail = s.getSeller().getEmail();
		
		
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
		
		JLabel msgLbl = new JLabel("");
		msgLbl.setBounds(172, 157, 159, 12);
		msgLbl.setForeground(Color.red);
		getContentPane().add(msgLbl);
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setBackground(new Color(231, 76, 60));
		btnClose.setForeground(Color.WHITE);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(35, 240, 200, 35);
		getContentPane().add(btnClose);
		
		
		
		JRadioButton rdbtn1 = new JRadioButton(filledIcon);
		buttonGroup.add(rdbtn1);
		rdbtn1.setBounds(107, 95, 40, 40);
		getContentPane().add(rdbtn1);
		
		JRadioButton rdbtn2 = new JRadioButton(emptyIcon);
		buttonGroup.add(rdbtn2);
		rdbtn2.setBounds(172, 95, 40, 40);
		getContentPane().add(rdbtn2);
		
		JRadioButton rdbtn3 = new JRadioButton(emptyIcon);
		buttonGroup.add(rdbtn3);
		rdbtn3.setBounds(232, 95, 40, 40);
		getContentPane().add(rdbtn3);
		
		JRadioButton rdbtn4 = new JRadioButton(emptyIcon);
		buttonGroup.add(rdbtn4);
		rdbtn4.setBounds(291, 95, 40, 40);
		getContentPane().add(rdbtn4);
		
		JRadioButton rdbtn5 = new JRadioButton(emptyIcon);
		buttonGroup.add(rdbtn5);
		rdbtn5.setBounds(357, 95, 40, 40);
		getContentPane().add(rdbtn5);
		
		JRadioButton[] stars = {rdbtn1, rdbtn2, rdbtn3, rdbtn4, rdbtn5};

		ActionListener starListener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selected = -1;
		        for (int i = 0; i < stars.length; i++) {
		            if (stars[i] == e.getSource()) {
		                selected = i;
		                break;
		            }
		        }

		        for (int i = 0; i < stars.length; i++) {
		            stars[i].setIcon(i <= selected ? filledIcon : emptyIcon);
		        }
		    }
		};

		for (JRadioButton star : stars) {
		    star.addActionListener(starListener);
		}
		
		JButton btnSend = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MakeReviewGUI.send"));
		btnSend.setBounds(264, 240, 200, 35);
		getContentPane().add(btnSend);
		btnSend.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int rating = -1;
		        for (int i = 0; i < stars.length; i++) {
		            if (stars[i].isSelected()) {
		                rating = i + 1; // 1-5
		                break;
		            }
		        }
		        if (rating == -1) {
		            msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("MakeReviewGUI.noStarSelected"));
		        }else {
		        	facade.makeReview(currentUserMail,s,rating,textFieldDesc.getText());
		        	dispose();
		        }
		    }
		});
		
		
	}
}
