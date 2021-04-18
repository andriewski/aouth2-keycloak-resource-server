package by.mark.clientapp.pseudojs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = {"by.mark.clientappmvc.config", "by.mark.mvc.security"})
@SpringBootApplication
public class ClientAppPseudoJsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientAppPseudoJsApplication.class, args);
    }

}
