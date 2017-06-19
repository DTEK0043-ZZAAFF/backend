package app.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * JPA entity for temperature measurement
 */
@Entity
public class Temperature extends AbstractPersistable<Long> {
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Node node;

    private double value;

    @NotNull
    private Date time = new Date();

    public Temperature() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
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
