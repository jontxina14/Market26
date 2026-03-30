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
	
	public Complaint(String description, Sale sale, Registered reg){
    	this.date =  UtilDate.trim(new Date());
		this.description=description;
		this.status=0;
		this.sale=sale;
		this.user=reg;
		saleTitle=sale.getTitle();
	}

	public String getSaleTitle() {
		return saleTitle;
	}

	public void setSaleTitle(String saleTitle) {
		this.saleTitle = saleTitle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Registered getUser() {
		return user;
	}

	public void setUser(Registered user) {
		this.user = user;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	
	
	

}
