package domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Admin.class,Registered.class})
public abstract class User  implements Serializable{
	@Id
	@XmlID

	private String email;
	private String password;
	public User(String email, String pass) {
		super();
		this.email = email;
		this.password = pass;
	}
	public User() {
		
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return password;
	}
	public void setPass(String pass) {
		this.password = pass;
	}
	
	public String toString(){
		return email+";";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registered other = (Registered) obj;
		//if (email != other.email)
			//return false;
		return true;
	}

}
