package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlID;

import configuration.UtilDate;

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

    private Sale sale;         

    private String description;
    
    public Movement(MovementType type, double amount, double balanceAfter, Sale sale, Registered user) {
    	date =  UtilDate.trim(new Date());
    	this.amount = amount;
    	this.balanceAfter = balanceAfter;
    	this.type = type;
    	this.user=user;
    	this.sale=sale;
    	//TODO description
    }
}
