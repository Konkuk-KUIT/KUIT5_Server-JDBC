package jwp.controller;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQuestionFormController implements Controller {
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");

        if (value != null) {
            return new JspView("/qna/form.jsp");
        }
        return new JspView("redirect:/user/loginForm");
    }
}
