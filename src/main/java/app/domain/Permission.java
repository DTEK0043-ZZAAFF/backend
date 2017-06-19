package app.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

/**
 * JPA entity for permissions
 */
@Entity
public class Permission extends AbstractPersistable<Long> {
    @Column(unique = true)
    private String uid;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Node> nodes;

    public Permission() {
    }

    public Permission(String uid, List<Node> nodes) {
        this.uid = uid;
        this.nodes = nodes;
    }

    public Permission(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
