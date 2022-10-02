package app.main;


import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import spark.embeddedserver.EmbeddedServers;
import spark.embeddedserver.jetty.EmbeddedJettyFactory;
import spark.embeddedserver.jetty.JettyServerFactory;

import java.time.LocalDateTime;

public class ApplicationRunner {

    static Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    public static void main(String[] args) {

        logger.info("Server is starting");
        EmbeddedServers.add(EmbeddedServers.Identifiers.JETTY, new EmbeddedJettyFactory(create(newLogger())));
        Spark.port(8080);
        Spark.get("/ping", (request, response) -> String.format("Pong @ %s", LocalDateTime.now()));

    }


    public static JettyServerFactory create(RequestLog log) {

        return new JettyServerFactory() {
            @Override
            public Server create(int maxThreads, int minThreads, int threadTimeoutMillis) {

                Server server;
                if (maxThreads > 0) {
                    int min = minThreads > 0 ? minThreads : 8;
                    int idleTimeout = threadTimeoutMillis > 0 ? threadTimeoutMillis : '\uea60';
                    server = new Server(new QueuedThreadPool(maxThreads, min, idleTimeout));
                } else {
                    server = new Server();
                }
                server.setRequestLog(log);
                return server;

            }

            @Override
            public Server create(ThreadPool threadPool) {
                return new Server(threadPool);
            }
        };

    }

    private static RequestLog newLogger() {
        Slf4jRequestLog requestLog = new Slf4jRequestLog();
        requestLog.setLogLatency(true);
        requestLog.setLogServer(true);
        return requestLog;
    }
}
