package gui;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;

import businessLogic.BLFacade;
import domain.Sale;


public class ShowSaleGUInonReg extends JFrame {
	
    File targetFile;
    BufferedImage targetImg;
    public JPanel panel_1;
    private static final int baseSize = 220;
	private static final String basePath="src/main/resources/images/";
	
	private static final long serialVersionUID = 1L;

	private JTextField fieldTitle=new JTextField();
	private JTextField fieldDescription=new JTextField();
	
	JLabel labelStatus = new JLabel(); 

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.Title"));
	private JLabel jLabelDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Description")); 
	private JLabel jLabelProductStatus = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Status"));
	private JLabel jLabelPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"));
	private JTextField fieldPrice = new JTextField();
	private File selectedFile;
    private String irudia;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	DefaultComboBoxModel<String> statusOptions = new DefaultComboBoxModel<String>();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private JLabel statusField=new JLabel();
	private JFrame thisFrame;
	
	public ShowSaleGUInonReg(Sale sale) { 
		thisFrame=this; 
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(900, 500));
		this.setLocationRelativeTo(null);
		//this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateProductGUI.CreateProduct"));

		fieldTitle.setText(sale.getTitle());
		fieldDescription.setText(sale.getDescription());

		fieldPrice.setText(Float.toString(sale.getPrice()));		
		
		labelStatus.setText(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
		
		jLabelTitle.setBounds(new Rectangle(6, 56, 140, 20));
		
		jLabelPrice.setBounds(new Rectangle(6, 220, 140, 25));
		fieldPrice.setEditable(false);
		fieldPrice.setBounds(new Rectangle(150, 220, 100, 30));

		
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonClose.setBounds(new Rectangle(150, 380, 140, 40));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 214, 305, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(6, 231, 320, 20));
		jLabelError.setForeground(Color.red);
		

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jLabelTitle, null);
		
		
		this.getContentPane().add(jLabelPrice, null);
		this.getContentPane().add(fieldPrice, null);
		
		jLabelProductStatus.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelProductStatus.setBounds(6, 260, 140, 25);
		getContentPane().add(jLabelProductStatus);
		
		jLabelDescription.setBounds(6, 81, 109, 16);
		getContentPane().add(jLabelDescription);
		fieldTitle.setEditable(false);
		
		
		fieldTitle.setBounds(150, 50, 500, 30);
		getContentPane().add(fieldTitle);
		fieldTitle.setColumns(10);
		fieldDescription.setEditable(false);
		
		
		fieldDescription.setBounds(150, 95, 680, 100);
		getContentPane().add(fieldDescription);
		fieldDescription.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBounds(520, 210, 250, 220);
		getContentPane().add(panel_1);
		
		labelStatus.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		labelStatus.setBounds(150, 300, 300, 25);
		getContentPane().add(labelStatus);
		
		
		BLFacade facade = MainGUInonReg.getBusinessLogic();
		String file=sale.getFile();
		if (file!=null) {
			Image img=facade.downloadImage(file);
			targetImg = rescale((BufferedImage)img);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(new JLabel(new ImageIcon(targetImg))); 
		}
		System.out.println("status: "+sale.getStatus());
		statusField = new JLabel(Utils.getStatus(sale.getStatus())); 
		statusField.setBounds(150, 260, 150, 25);
		getContentPane().add(statusField);
		
		
		
		
	}	 
	public BufferedImage rescale(BufferedImage originalImage)
    {
        BufferedImage resizedImage = new BufferedImage(baseSize, baseSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, baseSize, baseSize, null);
        g.dispose();
        return resizedImage;
    }
}

