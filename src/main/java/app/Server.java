package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource("file:./config/application.properties")
})
public class Server {

    private static Logger logger = LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) {


        SpringApplication.run(Server.class, args);
        logger.info("Server is started...");
    }
}
