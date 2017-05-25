package app.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Node extends AbstractPersistable<Long> {
    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "node")
    private List<Temperature> temperatures;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "node")
    private List<Pir> pirs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
