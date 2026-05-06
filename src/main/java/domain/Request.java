package domain;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import enums.RequestStatusType;
import enums.SaleStatusType;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Request implements Serializable {
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer requestNumber;
	private String title;
	private String description;
	private double suggestedPrice;
	private RequestStatusType requestStatus;
	@XmlIDREF
	private Registered requester;
	
	public String getXmlId() {
	    return "request-" + requestNumber;
	}
	@XmlID
    private String xmlId;
	@XmlIDREF
	//TODO: offeren lista sartu (addOfermetodoa egin offer bat sortzean deitzeko)
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<Offer> offers = new ArrayList<>();
	
	public Request(){
		super();
	}
		
	public Request(String title, String description, double price) {
		this.title = title;
		this.description = description;
		this.suggestedPrice = price;
		
		this.requestStatus = RequestStatusType.AVAILABLE;
	}
	

	public Integer getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(Integer requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return suggestedPrice;
	}

	public void setPrice(double price) {
		this.suggestedPrice = price;
	}

	public RequestStatusType getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatusType requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Registered getRequester() {
		return requester;
	}

	public void setRequester(Registered requester) {
		this.requester = requester;
	}
	
	
	public boolean addOffer(Offer o) {
		return offers.add(o);
	}
	
	public String toString() {
		return requestNumber + title + description + suggestedPrice + requestStatus;
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(requestNumber, other.requestNumber);
	}

	
	
	
	
}
