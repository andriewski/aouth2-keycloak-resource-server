package by.mark.oauth2.config;

import by.mark.oauth2.controller.UserController;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static by.mark.oauth2.controller.UserController.STATUS;
import static org.springframework.http.HttpMethod.GET;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SCOPE = "SCOPE_";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(GET, UserController.PATH + STATUS).hasAnyAuthority(SCOPE + "profile")
                .anyRequest().authenticated()
                .and()

                .oauth2ResourceServer()
                .jwt();

    }
}
