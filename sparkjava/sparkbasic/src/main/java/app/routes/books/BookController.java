package app.routes.books;

import app.routes.login.LoginController;
import app.view.PageView;
import app.view.paths.TemplatePath;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static app.requests.RequestMethods.*;

public class BookController {

    public static Route fetchAllBooks = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);
        HashMap<String, Object> model = new HashMap<>();
        model.put("books", new BookDao().getAllBooks());
        model.put("searchTerm", "");
        if (clientAcceptsHtml(request)) {
            return PageView.render(request, model, TemplatePath.BOOKS);
        }

        if (clientAcceptsJson(request)) {
            return new Gson().toJson(model);
        }
        return PageView.notAcceptable.handle(request, response);
    };

    public static Route fetchOneBook = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);
        HashMap<String, Object> model = new HashMap<>();
        Book book = new BookDao().getBookByIsbn(getParamIsbn(request));
        model.put("book", book);
        if (clientAcceptsHtml(request)) {
            return PageView.render(request, model, TemplatePath.SINGLE_BOOK);
        }
        if (clientAcceptsJson(request)) {
            return new Gson().toJson(model);
        }
        return PageView.notAcceptable.handle(request, response);
    };

    public static Route fetchAllBooksBySearchTerm = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);
        String searchTerm = request.queryParams("searchTerm");

        HashMap<String, Object> model = new HashMap<>();
        List<Book> allBooks = new BookDao().getAllBooks();
        List<Book> books = searchTerm.length() > 0 ? allBooks.stream()
                .filter(b -> b.match(searchTerm))
                .collect(Collectors.toList()) : allBooks;

        model.put("books", books);
        model.put("searchTerm", searchTerm);

        model.put("searchResult", String.format("%s books found", books.size()));

        if (clientAcceptsHtml(request)) {
            return PageView.render(request, model, TemplatePath.BOOKS);
        }

        if (clientAcceptsJson(request)) {
            return new Gson().toJson(model);
        }
        return PageView.notAcceptable.handle(request, response);
    };

}
