package by.mark.oauth2eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Oauth2EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2EurekaApplication.class, args);
	}

}
