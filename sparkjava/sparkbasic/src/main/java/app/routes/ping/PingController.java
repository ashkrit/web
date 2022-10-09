package app.routes.ping;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class PingController {

    public static Route pong = (Request request, Response response) -> {
        Map<String, String> values = new HashMap<String, String>() {{
            put("now", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }};
        return new Gson().toJson(values);
    };

}
