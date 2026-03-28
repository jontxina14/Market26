package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	private String email;
	private String password;
	
	public Admin(String e, String p) {
		this.email=e;
		this.password=p;
	}

}
