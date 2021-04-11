package by.mark.clientapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("config")
@Getter
@Setter
public class AppConfig {

    private String gatewayUri;
}
