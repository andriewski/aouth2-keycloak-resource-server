package by.mark.oauth2.resourceserver.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

@Configuration
@Getter
public class ApplicationInfoConfig {

    private final int localServerPort;

    public ApplicationInfoConfig(Environment env) {
        this.localServerPort = parseInt(requireNonNull(env.getProperty("local.server.port")));
    }
}
