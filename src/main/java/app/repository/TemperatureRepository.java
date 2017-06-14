package app.repository;

import app.domain.Node;
import app.domain.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RepositoryRestResource
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    Temperature findTopByNodeOrderByTimeDesc(Node node);
    List<Temperature> findTop10ByNodeOrderByTimeDesc(Node node);
    List<Temperature> findBy(Node node);

    @Query(value = "select new app.repository.Pair(function('hour', t.time),avg(t.value)) from Temperature t where t.node = ?1 group by function('hour', t.time)")
    List<Pair<Integer, Double>> getHourAveraged(Node node);

    @Query(value = "select new app.repository.Pair(min(t.time),avg(t.value)) from Temperature t where t.node = ?1 group by function('hour', t.time), function('trunc', t.time) order by function('trunc', t.time), function('hour', t.time)")
    List<Pair<Date, Double>> getHourAveraged2(Node node);

    // will not work. H2 does not handle timestamp to long conversion(one long is not big enough for timestamp?)
    @Query(value = "select new app.repository.Pair(min(t.time),avg(t.value)) from Temperature t where t.node = ?1 group by function('timestampadd', 'milliseconds',  -mod(t.time, ?2), t.time)")
    List<Pair<Date, Double>> getHourAveraged2(Node node, long t);
}
