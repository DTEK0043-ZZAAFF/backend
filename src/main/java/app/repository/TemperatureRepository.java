package app.repository;

import app.domain.Node;
import app.domain.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    Temperature findTopByNodeOrderByTimeDesc(Node node);
}
