package core.mvc;

import core.mvc.controller.Controller;
import java.util.HashMap;
import java.util.Map;
import jwp.controller.CreateUserController;
import jwp.controller.HomeController;
import jwp.controller.ListUserController;
import jwp.controller.LoginController;
import jwp.controller.LogoutController;
import jwp.controller.UpdateUserController;
import jwp.controller.UpdateUserFormController;
import jwp.controller.qna.AddAnswerController;
import jwp.controller.qna.CreateQuestionController;
import jwp.controller.qna.CreateQuestionFormController;
import jwp.controller.qna.ShowController;

public class RequestMapping {

    private static final Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());

        controllers.put("/user/form", new ForwardController("/user/form.jsp"));
        controllers.put("/user/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/user/loginFailed", new ForwardController("/user/loginFailed.jsp"));

        controllers.put("/qna/form", new CreateQuestionFormController());
        controllers.put("/qna/create", new CreateQuestionController());
        controllers.put("/qna/show", new ShowController());

        controllers.put("/api/qna/addAnswer", new AddAnswerController());

    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
