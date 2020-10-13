package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource("file:./config/application.properties")
})
public class Server {
    public static void main(String[] args) {
        System.out.println("Starting application...");
        SpringApplication.run(Server.class, args);
    }
}
