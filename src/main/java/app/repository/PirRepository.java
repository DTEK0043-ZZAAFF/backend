package app.repository;

import app.domain.Node;
import app.domain.Pir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PirRepository extends JpaRepository<Pir, Long> {
    Pir findTopByNodeOrderByTimeDesc(Node node);
    List<Pir> findAllBy(Node node);
}
