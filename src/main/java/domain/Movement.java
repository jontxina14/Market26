package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlID;

import configuration.UtilDate;
import enums.MovementType;

@Entity
public class Movement {
	@XmlID
	@Id
	@GeneratedValue
	private int id;
    private Date date;
    private double amount;
    private double balanceAfter;

    // BUY, SELL, DEPOSIT, WITHDRAW
    private MovementType type;

    @OneToOne
    private Registered user;
    private String email;

    private Sale sale;         

    private String description;
    
    public Movement(MovementType type, double amount, double balanceAfter, Sale sale, Registered user) {
    	this.date =  UtilDate.trim(new Date());
    	this.amount = amount;
    	this.balanceAfter = balanceAfter;
    	this.type = type;
    	this.user=user;
    	email=this.user.getEmail();
    	this.sale=sale;
    	//TODO description
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
}
