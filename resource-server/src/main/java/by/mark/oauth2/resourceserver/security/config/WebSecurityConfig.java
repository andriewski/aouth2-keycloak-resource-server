package by.mark.oauth2.resourceserver.security.config;

import by.mark.oauth2.resourceserver.controller.UserController;
import by.mark.oauth2.resourceserver.security.KeyCloakAuthoritiesConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import static by.mark.oauth2.resourceserver.controller.UserController.STATUS;
import static org.springframework.http.HttpMethod.GET;

@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true
)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String DEVELOPER_ROLE = "ROLE_developer";
    public static final String PROFILE_SCOPE = "SCOPE_profile";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers(GET, UserController.PATH + STATUS)
                .hasAnyAuthority(PROFILE_SCOPE, DEVELOPER_ROLE)
//                .hasAnyRole("developer", "user")
//                .hasRole("developer")

                .anyRequest().authenticated()
                .and()

                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(getJwtAuthenticationConverter());
    }

    private static JwtAuthenticationConverter getJwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakAuthoritiesConverter());

        return jwtAuthenticationConverter;
    }
}
