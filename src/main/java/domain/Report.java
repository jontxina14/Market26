package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;

import configuration.UtilDate;

@Entity
public class Report {
	
	@XmlID
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private String cause;
	
	public Report(String casue) {
    	this.date =  UtilDate.trim(new Date());
		this.cause=cause;
	}

}
