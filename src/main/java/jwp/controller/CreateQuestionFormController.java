package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

public class CreateQuestionFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (req.getMethod().equals("GET")) {
            HttpSession session = req.getSession();
            if (UserSessionUtils.isLogined(session)) {
                return "/qna/form.jsp";
            }
            return "redirect:/user/loginForm";
        }

        if (req.getMethod().equals("POST")) {
            QuestionDao questionDao = new QuestionDao();
            Question createdQuestion = new Question(
                    req.getParameter("writer"),
                    req.getParameter("title"),
                    req.getParameter("contents"),
                    Date.valueOf(LocalDate.now()),
                    0
                    );

            Question question = questionDao.insert(createdQuestion);
            if(question == null){
                throw new NullPointerException("error occurred in creating question");
            }
            return "redirect:/";

        }

        return null;
    }
}