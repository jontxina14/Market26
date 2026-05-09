package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class OfferContainer {

    private Offer offer;
    private Request request;
    //private Registered registered;

    public OfferContainer(Offer o) {
        this.offer = o;
        this.request = o.getRequest();
       // this.registered = o.getRegistered();
    }

    public OfferContainer() {
        offer = null;
        request = null;
     //   registered = null;
    }

    public Offer getOffer() { return offer; }
    public Request getRequest() { return request; }
   // public Registered getRegistered() { return registered; }
}