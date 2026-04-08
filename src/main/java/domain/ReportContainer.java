package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReportContainer {

	    private Report report;
	    private Sale sale;
	    private Registered user;

	    public ReportContainer(Report r) {
	        this.report = r;
	        this.sale = r.getSale();
	        this.user = r.getUser();
	    }

	    public ReportContainer() {
	        report = null;
	        sale = null;
	        user = null;
	    }

	    public Report getReport() { return report; }
	    public Sale getSale() { return sale; }
	    public Registered getUser() { return user; }
}

