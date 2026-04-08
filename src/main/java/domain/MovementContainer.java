package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MovementContainer {

    private Movement movement;
    private Sale sale;
    private Registered user;

    public MovementContainer(Movement r) {
        this.movement = r;
        this.sale = r.getSale();
        this.user = r.getUser();
    }

    public MovementContainer() {
        movement = null;
        sale = null;
        user = null;
    }

    public Movement getMovement() { return movement; }
    public Sale getSale() { return sale; }
    public Registered getUser() { return user; }
}
