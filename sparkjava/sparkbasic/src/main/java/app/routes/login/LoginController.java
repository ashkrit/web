package app.routes.login;

import app.requests.RequestMethods;
import app.routes.users.UserController;
import app.view.PageView;
import app.view.paths.TemplatePath;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static app.requests.RequestMethods.*;

public class LoginController {

    public static Route serveLoginPage = (Request request, Response response) -> {

        Map<String, Object> model = new HashMap<>();
        model.put("loggedOut", RequestMethods.removeSessionAttrLoggedOut(request));
        model.put("loginRedirect", RequestMethods.removeSessionAttrLoginRedirect(request));
        return PageView.render(request, model, TemplatePath.LOGIN);

    };

    public static Route handleLoginPost = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        if (!UserController.authenticate(getQueryUsername(request), RequestMethods.getQueryPassword(request))) {
            model.put("authenticationFailed", true);
            return PageView.render(request, model, TemplatePath.LOGIN);
        }
        model.put("authenticationSucceeded", true);
        request.session().attribute("currentUser", getQueryUsername(request));
        response.redirect("/books");

        return PageView.render(request, model, TemplatePath.LOGIN);
    };

    public static void ensureUserIsLoggedIn(Request request, Response response) {
        if (request.session().attribute("currentUser") == null) {
            request.session().attribute("loginRedirect", request.pathInfo());
            response.redirect("/login");
        }
    }

}
