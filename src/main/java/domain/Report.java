package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlID;

import configuration.UtilDate;
import enums.ReportReason;

@Entity
public class Report {
	
	@XmlID
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private ReportReason cause;
	
	@OneToOne
	private Registered user;
	
	@OneToOne
	private Sale sale;
	
	public Report(ReportReason cause, Sale sale, Registered user) {
    	this.date =  UtilDate.trim(new Date());
		this.cause=cause;
		this.sale=sale;
		this.user=user;
	}

	public ReportReason getCause() {
		return cause;
	}

	public void setCause(ReportReason cause) {
		this.cause = cause;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
