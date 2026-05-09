package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReviewContainer {

    private Review review;
    private Registered evaluator;

    public ReviewContainer(Review r) {
        this.review = r;
        this.evaluator = r.getEvaluator();
        }

    public ReviewContainer() {
        review = null;
        evaluator = null;
    }

    public Review getReview() { return review; }
    public Registered getEvaluator() { return evaluator; }
}
