package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;

@Entity
public class Complaint {
	
	@XmlID
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private String description;

	//1 treated 0 not treated
	private int status;
	
	public Complaint(String description){
		this.description=description;
	}
	

}
