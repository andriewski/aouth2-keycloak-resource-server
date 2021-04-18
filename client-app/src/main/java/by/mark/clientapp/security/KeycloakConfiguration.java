package by.mark.clientapp.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("spring.security.oauth2.client.registration.my-cool-client-app")
@Getter
@RequiredArgsConstructor
public class KeycloakConfiguration {

    private final String redirectUri;
}
