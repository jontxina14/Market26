package domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import enums.OfferStatusType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Offer implements Serializable{
	private static final long serialVersionUID = 1L;

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private int offerId;
	private double price;
	private String description;
	private int status;
	private OfferStatusType offerStatus;
	@XmlIDREF
	@OneToOne
	private Registered registered;
	@XmlIDREF
	@OneToOne
	private Request request;
	
	public Offer(Double price, String description, int status, Registered reg, Request req) {
		this.price=price;
		this.setDescription(description);
		this.offerStatus=OfferStatusType.WAITING;
		this.registered=reg;
		this.request=req;
		this.status=status;
	}
	public Offer() {
		
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


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
	
}
