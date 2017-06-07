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

    @Query(value = "select new app.repository.Pair(avg(t.value),function('hour', t.time)) from Temperature t where t.node = ?1 group by function('hour', t.time)")
    List<Pair<Double, Integer>> getHourAveraged(Node node);
}
