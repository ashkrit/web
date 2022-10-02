package app.controller;

import app.view.PageView;
import app.view.paths.TemplatePath;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;

import static app.requests.RequestMethods.*;

public class BookController {

    public static Route fetchAllBooks = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("books", new BookDao().getAllBooks());
            return PageView.render(request, model, TemplatePath.BOOKS);
        }
        /*
        if (clientAcceptsJson(request)) {
            return dataToJson(bookDao.getAllBooks());
        }*/
        return PageView.notAcceptable.handle(request, response);
    };

    public static Route fetchOneBook = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            Book book = new BookDao().getBookByIsbn(getParamIsbn(request));
            model.put("book", book);
            return PageView.render(request, model, TemplatePath.SINGLE_BOOK);
        }
        /*
        if (clientAcceptsJson(request)) {
            return dataToJson(bookDao.getBookByIsbn(getParamIsbn(request)));
        }*/
        return PageView.notAcceptable.handle(request, response);
    };

}
