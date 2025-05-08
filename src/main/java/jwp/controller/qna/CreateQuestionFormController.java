package jwp.controller.qna;

import core.mvc.Controller;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import jwp.util.UserSessionUtils;

public class CreateQuestionFormController implements Controller {
    private HttpSession session;

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        if (UserSessionUtils.isLogined(session)) {
            return "/qna/form.jsp";
        }
        return "redirect:/user/loginForm";
    }
}