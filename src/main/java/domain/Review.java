package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import configuration.UtilDate;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Review implements Serializable{
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@XmlTransient
	@Id
	@GeneratedValue
	private Integer reviewId;
	
	private int rating;
	
	private String description;
	
	private Date date;
	
	public Review(int rating, String description) {
		this.rating=rating;
		this.description=description;
		this.date=UtilDate.trim(new Date());
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

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
