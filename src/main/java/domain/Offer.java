package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import enums.OfferStatusType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Offer {
	@XmlTransient
	@Id
	@GeneratedValue
	private int offerId;
	
	private double price;
	
	private String description;
	
	private OfferStatusType offerStatus;

	@OneToOne
	private Registered registered;
	
	@OneToOne
	private Request request;
	
	public Offer(Double price, String description, Registered reg, Request req) {
		this.price=price;
		this.setDescription(description);
		this.offerStatus=OfferStatusType.WAITING;
		this.registered=reg;
		this.request=req;
	}
	
	
	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OfferStatusType getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(OfferStatusType offerStatus) {
		this.offerStatus = offerStatus;
	}

	public Registered getRegistered() {
		return registered;
	}

	public void setRegistered(Registered registered) {
		this.registered = registered;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
