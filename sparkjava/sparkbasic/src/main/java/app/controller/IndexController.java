package app.controller;

import app.view.PageView;
import app.view.paths.TemplatePath;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IndexController {

    public static Route indexPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("users", Arrays.asList("user1", "user2"));
        //model.put("book", bookDao.getRandomBook());
        return PageView.render(request, model, TemplatePath.INDEX);
    };


}
