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
}
