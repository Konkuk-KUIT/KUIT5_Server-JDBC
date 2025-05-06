package jwp.controller.qna;

import core.mvc.AbstractController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.mvc.ModelAndView;
import jwp.util.UserSessionUtils;

public class CreateQuestionFormController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if (UserSessionUtils.isLogined(session)) {
            return jspView("/qna/form.jsp");
        }
        return jspView("redirect:/user/loginForm");
    }
}