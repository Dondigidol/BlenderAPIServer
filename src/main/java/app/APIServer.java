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
public class APIServer {
    private static final Logger logger = LoggerFactory.getLogger(APIServer.class);

    public static void main(String[] args) {

        SpringApplication.run(APIServer.class, args);
        logger.info("Server is started...");
    }

}


