package core.mvc;

import jwp.controller.*;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {

    private static final Map<String, Controller> controllers = new HashMap<>();
    static UserDao userDao = new UserDao();
    static QuestionDao questionDao = new QuestionDao();
    static AnswerDao answerDao = new AnswerDao();
    static {
        controllers.put("/", new HomeController(questionDao));
        controllers.put("/user/signup", new CreateUserController(userDao));
        controllers.put("/user/list", new ListUserController(userDao));
        controllers.put("/user/login", new LoginController(userDao));
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController(userDao));

        controllers.put("/user/form", new ForwardController("/user/form.jsp"));
        controllers.put("/user/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/user/loginFailed", new ForwardController("/user/loginFailed.jsp"));

        controllers.put("/qna/form", new CreateQuestionFormController());
        controllers.put("/qna/show", new ShowController(questionDao, answerDao));
        controllers.put("/qna/create", new CreateQuestionController(questionDao));

    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
