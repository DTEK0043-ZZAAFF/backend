package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration class
 *
 * Mostly removes security features to make development easier
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: do proper config
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and().httpBasic();
        http.authorizeRequests().anyRequest().permitAll();

        // disable csrf
        http.csrf().disable();

        // required for h2-console
        http.headers().frameOptions().sameOrigin();
    }

    /*
     * Really simple authentication scheme. Not (yet) used in our code
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }
}
