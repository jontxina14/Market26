package domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

import enums.MovementType;
import enums.ReportReason;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Registered extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	private String name; 
	private double balance;
	@XmlIDREF
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<Sale> sales = new ArrayList<Sale>();

	@OneToMany(fetch = FetchType.LAZY)
	private List<Sale> wishList = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY)
	private List<Sale> bought = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<Movement> movements = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<Complaint> complaints = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<Report> reports = new ArrayList<>();

	public Registered() {
		super();
	}

	public Registered(String email, String name,String pass) {
		super(email,pass);
		this.name = name;
		this.balance = 0;
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void addToWishList(Sale sale) {
		if (!wishList.contains(sale)) {
			wishList.add(sale);
		}
	}
	public void removeFromWishList(Sale sale){
		wishList.remove(sale);
	}


	public List<Sale> getWishList() {
		return wishList;
	}

	public List<Sale> getSales() {
		return sales;
	}
	public List<Sale> getBought() {
		return bought;
	} 


	public List<Complaint> getComplaints() {
		return complaints;
	}

	public List<Report> getReports() {
		return reports;
	}

	public String toString(){
		return  name+sales;
	}

	/**
	 * This method creates/adds a sale to a seller
	 * 
	 * @param title of the sale
	 * @param description of the sale
	 * @param status 
	 * @param selling price
	 * @param publicationDate
	 * @return Sale
	 */




	public Sale addSale(String title, String description, int status, float price,  Date pubDate, File file)  {

		Sale sale=new Sale(title, description, status, price,  pubDate, file, this);
		sales.add(sale);
		return sale;
	}

	public void addToBought(ArrayList<Sale> sales) {
		for(Sale sale : sales) {
			if(!bought.contains(sale)) {
				bought.add(sale);
			}
		}
	}




	public void addToMovements(MovementType type, double amount, double balanceAfter, ArrayList<Sale> sales) {
		Movement m = new Movement(type, amount, balanceAfter, sales, this);
		movements.add(m);
	}




	/**
	 * This method checks if the ride already exists for that driver
	 * 
	 * @param from the origin location 
	 * @param to the destination location 
	 * @param date the date of the ride 
	 * @return true if the ride exists and false in other case
	 */
	public boolean doesSaleExist(String title)  {	
		for (Sale s:sales)
			if ( s.getTitle().compareTo(title)==0 )
				return true;
		return false;
	}

	public void addReport(ReportReason reason, Sale s) {
		Report r = new Report(reason, s,this);
		reports.add(r);
		s.addReport(r);
	}

	public void addComplaint(String complaint, Sale s) {
		Complaint c = new Complaint(complaint, s, this);
		complaints.add(c);
		s.addComplaint(c);
	}




	/*	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registered other = (Registered) obj;
		if (email != other.email)
			return false;
		return true;
	}
	 */

}
