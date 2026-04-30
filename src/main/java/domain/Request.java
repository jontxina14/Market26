package domain;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import enums.RequestStatusType;
import enums.SaleStatusType;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Request implements Serializable {
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer requestNumber;
	private String title;
	private String description;
	private double price;
	private RequestStatusType requestStatus;
	@XmlIDREF
	private Registered requester;

	
	public Request(){
		super();
	}
		
	public Request(String title, String description, double price) {
		this.title = title;
		this.description = description;
		this.price = price;
		
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
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
	
	
	
}
