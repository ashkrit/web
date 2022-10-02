package app.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.time.LocalDateTime;

public class ApplicationRunner {

    static Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    public static void main(String[] args) {

        logger.info("Server is starting");


        Spark.port(8080);

        Spark.get("/ping", (request, response) -> String.format("Pong @ %s", LocalDateTime.now()));

    }
}
