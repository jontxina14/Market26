package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin extends User{

	
	public Admin(String e, String p) {
		super(e,p);
	}
	public Admin() {
	    super();
	}


	

}
