package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ComplaintContainer {

    private Complaint complaint;
    private Sale sale;
    private Registered user;

    public ComplaintContainer(Complaint c) {
        this.complaint = c;
        this.sale = c.getSale();
        this.user = c.getUser();
    }

    public ComplaintContainer() {
        complaint = null;
        sale = null;
        user = null;
    }

    public Complaint getComplaint() { return complaint; }
    public Sale getSale() { return sale; }
    public Registered getUser() { return user; }
}
