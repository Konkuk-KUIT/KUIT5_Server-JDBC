package jwp.controller.qna;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

public class CreateQuestionFormController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            HttpSession session = request.getSession();
            if (UserSessionUtils.isLogined(session)) {
                return jspView("/qna/form.jsp");
            }
            return jspView("redirect:/user/loginForm");
        }

        if (request.getMethod().equals("POST")) {
            QuestionDao questionDao = new QuestionDao();
            Question createdQuestion = new Question(
                    request.getParameter("writer"),
                    request.getParameter("title"),
                    request.getParameter("contents"),
                    Date.valueOf(LocalDate.now()),
                    0
            );

            Question question = questionDao.insert(createdQuestion);
            if (question == null) {
                throw new NullPointerException("error occurred in creating question");
            }
            return jspView("redirect:/");

        }

        return null;
    }
}