package controllers;

import models.Login;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.*;
import views.html.forms.register;
import views.html.forms.login;
import views.html.applicationPage.dashboard;
import javax.inject.Inject;

import static Utility.DBUtility.*;

public class HomeController extends Controller {

    private final Form<User> userForm;
    private final Form<Login> loginForm;
    private final MessagesApi messagesApi;

    @Inject
    public HomeController(FormFactory formFactory, MessagesApi messagesApi) {
        this.userForm = formFactory.form(User.class);
        this.loginForm = formFactory.form(Login.class);
        this.messagesApi = messagesApi;
    }

    public Result index(Http.Request request) {
        if(checkSessionForUser(request)) {
            if(request.session().get("user").isPresent())
                return ok(dashboard.render(getUserByEmail(request.session().get("user").get())));
            else return ok(views.html.index.render());
        } else return ok(views.html.index.render());
    }

    public Result signup_page(Http.Request request) {
        if(checkSessionForUser(request)) {
            if(request.session().get("user").isPresent())
                return ok(dashboard.render(getUserByEmail(request.session().get("user").get())));
            else return ok(views.html.index.render());
        } else return ok(register.render(userForm, request, messagesApi.preferred(request)));
    }

    public Result login_page(Http.Request request) {
        if(checkSessionForUser(request)) {
            if(request.session().get("user").isPresent())
                return ok(dashboard.render(getUserByEmail(request.session().get("user").get())));
            else return ok(views.html.index.render());
        } else return ok(login.render(loginForm, request, messagesApi.preferred(request)));
    }

    public Result login(Http.Request request) {
        final Form<Login> boundForm = loginForm.bindFromRequest(request);
        Login login = boundForm.get();
        if (boundForm.hasErrors()) {
            return badRequest(register.render(userForm, request, messagesApi.preferred(request)));
        } else {
            boolean result = getFromTable(login);
            if(result) {
                return ok(dashboard.render(getUserByEmail(login.getEmail()))).addingToSession(request, "user", login.getEmail());
            }
            else return login_page(request);
        }
    }

    public Result signup(Http.Request request) {
        final Form<User> boundForm = userForm.bindFromRequest(request);
        User user = boundForm.get();
        if (boundForm.hasErrors()) {
            return badRequest(register.render(userForm, request, messagesApi.preferred(request)));
        } else {
            insertIntoTable(user);
            return login_page(request);
        }
    }

    public boolean checkSessionForUser(Http.Request request) {
        return request.session().get("user").isPresent();
    }

    public Result dashboard(Http.Request request) {
        if(checkSessionForUser(request)) {
            if(request.session().get("user").isPresent()) {
                return ok(dashboard.render(getUserByEmail(request.session().get("user").get())));
            }
            else return ok(views.html.index.render());
        } else return redirect("/login_page");
    }

    public Result logout() {
        return redirect("/login_page").withNewSession();
    }

}
