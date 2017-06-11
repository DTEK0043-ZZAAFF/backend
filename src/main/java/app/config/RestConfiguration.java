package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * REST configuration class
 */
@Configuration
public class RestConfiguration {
    /**
     * Enables Spring Data REST interface.
     *
     * Note: No security at all
     * @return configuration which is processed by ???
     */
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                // share only Repositories which are annotated for REST API
                config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
                // Change root path for REST API!
                config.setBasePath("/api/v1/");
            }
        };
    }
}
