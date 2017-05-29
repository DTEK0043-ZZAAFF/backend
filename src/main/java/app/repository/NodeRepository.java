package app.repository;

import app.domain.Node;
import app.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NodeRepository extends JpaRepository<Node, Long> {
    Node findByName(@Param("name") String name);
    Long countByPermissionsContains(Permission permission);
}
