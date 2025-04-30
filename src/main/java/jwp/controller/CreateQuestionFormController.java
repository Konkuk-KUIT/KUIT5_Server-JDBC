package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.Instant;

public class CreateQuestionFormController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            return "redirect:/user/loginForm";
        }

        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Question question = new Question(null, writer, title, contents, Timestamp.from(Instant.now()), 0);
        questionDao.insert(question);

        return "redirect:/";
    }
}
