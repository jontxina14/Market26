package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import configuration.UtilDate;
import enums.MovementType;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Movement {
	 @XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private int id;
    private Date date;
    private double amount;
    private double balanceAfter;

    // BUY, SELL, DEPOSIT, WITHDRAW
    private MovementType type;
    
    @XmlIDREF
    @OneToOne
    private Registered user;
    private String email;

    @XmlIDREF
    private ArrayList<Sale> sales;         

    private String description;
    
    public Movement(MovementType type, double amount, double balanceAfter, ArrayList<Sale> sales, Registered user) {
    	this.date =  UtilDate.trim(new Date());
    	this.amount = amount;
    	this.balanceAfter = balanceAfter;
    	this.type = type;
    	this.user=user;
    	email=this.user.getEmail();
    	this.sales=sales;
    	//TODO description
    }
    public Movement() {
    	
    }
    
    public Date getDate() {
    	return date;
    }
    
    public double getAmount() {
    	return amount;
    }
    
    public double getBalanceAfter() {
    	return balanceAfter;
    }
    
    public MovementType getType() {
    	return type;
    }
	public Sale getSale() {
		// TODO Auto-generated method stub
		return null;
	}
	public Registered getUser() {
		// TODO Auto-generated method stub
		return null;
	}
}
