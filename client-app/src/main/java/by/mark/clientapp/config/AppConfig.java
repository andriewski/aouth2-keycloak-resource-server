package by.mark.clientapp.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("config")
@Getter
@RequiredArgsConstructor
public class AppConfig {

    private final String gatewayUri;
}
