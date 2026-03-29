package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlID;

import configuration.UtilDate;

@Entity
public class Complaint {
	
	@XmlID
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private String description;

	//1 treated 0 not treated
	private int status;
	
	@OneToOne
    private Registered user;  

	@OneToOne
    private Sale sale;
	private String saleTitle;
	
	public Complaint(String description){
    	this.date =  UtilDate.trim(new Date());
		this.description=description;
		this.status=0;
		saleTitle=this.sale.getTitle();
	}
	
	

}
