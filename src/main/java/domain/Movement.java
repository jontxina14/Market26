package domain;

import java.util.Date;

import javax.persistence.OneToOne;

import configuration.UtilDate;

public class Movement {
    private int id;
    private static int movementCount = 1;
    private Date date;
    private double amount;
    private double balanceAfter;

    // BUY, SELL, DEPOSIT, WITHDRAW
    private MovementType type;

    @OneToOne
    private Registered user;  

    private Sale sale;         

    private String description;
    
    public Movement(MovementType type, double amount, Sale sale, Registered user) {
    	id = movementCount++;
    	date =  UtilDate.trim(new Date());
    	this.amount = amount;
    	balanceAfter += amount;
    	this.type = type;
    	this.user=user;
    	this.sale=sale;
    	//TODO description
    }
}
