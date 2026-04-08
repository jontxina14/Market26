package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SaleContainer {

    private Sale sale;
    private Registered user;

    public SaleContainer(Sale r) {
        this.sale = r.getSale();
        this.user = r.getSeller();
    }

    public SaleContainer() {
        sale = null;
        user = null;
    }

    public Sale getSale() { return sale; }
    public Registered getUser() { return user; }
}
