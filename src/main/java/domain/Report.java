package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;

import configuration.UtilDate;
import enums.ReportReason;

@Entity
public class Report {
	
	@XmlTransient
	@Id
	@GeneratedValue
	private int id;
	@XmlID
	public String getXmlId() {
	    return "report-" + id;
	}
	
	private Date date;
	private ReportReason cause;
	boolean treated;
	
	@OneToOne
	private Registered user;
	
	@OneToOne
	private Sale sale;
	
	public Report(ReportReason cause, Sale sale, Registered user) {
    	this.date =  UtilDate.trim(new Date());
		this.cause=cause;
		this.sale=sale;
		this.user=user;
		this.treated=false;
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
	
	public int getId() {
		return id;
	}

	public boolean isTreated() {
		return treated;
	}

	public void setTreated(boolean treated) {
		this.treated = treated;
	}
	
	
	
	
	
	
}
