package app.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Entity
public class Node extends AbstractPersistable<Long> {
    @Column(unique = true)
    private String name;

    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "node")
    private List<Temperature> temperatures;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "node")
    private List<Pir> pirs;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "nodes")
    private List<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public List<Pir> getPirs() {
        return pirs;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
}
