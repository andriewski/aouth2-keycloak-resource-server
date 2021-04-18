package by.mark.clientapp.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = {"by.mark.clientapp.mvc.config", "by.mark.clientapp.mvc.security"})
@SpringBootApplication
public class ClientAppMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientAppMvcApplication.class, args);
    }

}
