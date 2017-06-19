package app.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * JPA entity for PIR detection
 */
@Entity
public class Pir extends AbstractPersistable<Long> {
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Node node;

    @NotNull
    private Date time = new Date();

    /**
     * True means that measurement if for rising edge/new movement measurement
     */
    private boolean up = true;

    public Pir() {
    }

    public Pir(Date time) {
        this.time = time;
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

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
