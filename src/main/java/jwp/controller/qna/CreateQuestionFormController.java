package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.util.UserSessionUtils;

public class CreateQuestionFormController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLogined(session)) {
            return new JspView("/qna/form.jsp");
        }
        return new JspView("redirect:/user/loginForm");
    }
}