package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlID;

import configuration.UtilDate;
import enums.ReportType;

@Entity
public class Report {
	
	@XmlID
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private ReportType cause;
	
	@OneToOne
	private Registered user;
	
	@OneToOne
	private Sale sale;
	
	public Report(ReportType casue) {
    	this.date =  UtilDate.trim(new Date());
		this.cause=cause;
	}

	public ReportType getCause() {
		return cause;
	}

	public void setCause(ReportType cause) {
		this.cause = cause;
	}
	
}
