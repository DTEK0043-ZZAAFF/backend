package app.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Pir extends AbstractPersistable<Long> {
    @ManyToOne(fetch = FetchType.EAGER)
    private Node node;

    @NotNull
    private Date time = new Date();

    public Pir() {
    }

    public Pir(Node node) {
        this.node = node;
    }

    public Pir(Node node, Date time) {
        this.node = node;
        this.time = time;
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
}
