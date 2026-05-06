package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlTransient;

import configuration.UtilDate;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Review implements Serializable{
	
	@XmlTransient
	@Id
	@GeneratedValue
	private Integer reviewId;
	
	private int rating;
	
	private String description;
	
	private Date date;
	
	private Sale sale;
	
	@XmlIDREF	
	private Registered evaluator;
	
	public Review() {}
	
	public Review(int rating, String description, Sale s, Registered evaluator) {
		this.rating=rating;
		this.description=description;
		this.date=UtilDate.trim(new Date());
		this.sale=s;
		this.evaluator=evaluator;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Registered getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(Registered evaluator) {
		this.evaluator = evaluator;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
