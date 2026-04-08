package domain;

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

import configuration.UtilDate;
import enums.ReportReason;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Report {
	
	@XmlTransient
	@Id
	@GeneratedValue
	private int id;
	
	public String getXmlId() {
	    return "report-" + id;
	}
	@XmlID
	private String XmlId;
	
	public void setId(int id) {
	    this.id = id;
	    this.XmlId = "report-" + id;
	}

	public Report() {
		//derrigorrezkoa xml-rako
	}
	
	private Date date;
	private ReportReason cause;
	boolean treated;
   
	@XmlIDREF
	@OneToOne
	private Registered user;
	
	@XmlIDREF
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
