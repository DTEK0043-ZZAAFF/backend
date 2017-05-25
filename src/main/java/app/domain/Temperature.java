package app.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Temperature extends AbstractPersistable<Long> {
    @ManyToOne(fetch = FetchType.EAGER)
    private Node node;

    private float value;

    @NotNull
    private Date time = new Date();

    public Temperature() {
    }

    public Temperature(float value, Node node) {
        this.value = value;
        this.node = node;
        this.time = new Date();
    }

    public Temperature(float value, Node node, Date time) {
        this.value = value;
        this.node = node;
        this.time = time;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
