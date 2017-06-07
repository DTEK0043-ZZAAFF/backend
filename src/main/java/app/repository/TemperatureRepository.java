package app.repository;

import app.domain.Node;
import app.domain.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    Temperature findTopByNodeOrderByTimeDesc(Node node);
    List<Temperature> findTop10ByNodeOrderByTimeDesc(Node node);
    List<Temperature> findBy(Node node);

    @Query(value = "select avg(t.value),hour(t.time) from temperature t where t.node_id = ?1 group by hour(t.time)", nativeQuery = true)
    List<Object[]> getHourAveraged(Long node);
}
