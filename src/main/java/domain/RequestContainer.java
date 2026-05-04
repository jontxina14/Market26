package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RequestContainer {

    private Request request;
    private Registered requester;

    public RequestContainer(Request r) {
        this.request = r;
        this.requester = r.getRequester();
    }

    public RequestContainer() {
        request = null;
        requester = null;
    }

    public Request getRequest() { return request; }
    public Registered getRequester() { return requester; }
}
