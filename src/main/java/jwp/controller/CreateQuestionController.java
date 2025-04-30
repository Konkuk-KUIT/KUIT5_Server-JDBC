package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.Instant;

public class CreateQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/user/loginForm";
        }

        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Question question = new Question(null, writer, title, contents, Timestamp.from(Instant.now()), 0);
        Question saved = questionDao.insert(question);

        return "redirect:/qna/show?questionId=" + saved.getQuestionId();
    }
}