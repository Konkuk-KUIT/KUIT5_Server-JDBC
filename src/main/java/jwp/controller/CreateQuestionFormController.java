package jwp.controller;

import core.mvc.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQuestionFormController extends AbstractController {
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");

        if (value != null) {
            return jspView("/qna/form.jsp");
        }
        return jspView("redirect:/user/loginForm");
    }
}
