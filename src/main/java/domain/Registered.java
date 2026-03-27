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

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Registered implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String email;
	private String name; 
	private String pass;
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
	private List<Sale> complaints = new ArrayList<>();

	public Registered() {
		super();
	}

	public Registered(String email, String name,String pass) {
		this.email = email;
		this.name = name;
		this.pass = pass;
		this.balance = 0;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
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


	public String toString(){
		return email+";"+name+sales;
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
	
	public void addToBought(Sale sale) {
		if(!bought.contains(sale)) {
			bought.add(sale);
		}
	}
	
	public void addToMovements(Movement move) {
		if(!movements.contains(move)) {
			movements.add(move);
		}
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
		
	@Override
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

	
}
