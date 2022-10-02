package app.routes.index;

import app.routes.books.BookDao;
import app.routes.users.UserDao;
import app.view.PageView;
import app.view.paths.TemplatePath;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class IndexController {

    public static Route indexPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("users", UserDao.create().userNames());
        model.put("book", BookDao.create().getRandomBook());
        return PageView.render(request, model, TemplatePath.INDEX);
    };


}
