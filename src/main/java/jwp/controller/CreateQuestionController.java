package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class CreateQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Question question = new Question(null, writer, title, contents, new Date(), 0);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(question);

        return "redirect:/";
    }
}