package jwp.controller;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class CreateQuestionController extends AbstractController {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Timestamp now = new Timestamp(System.currentTimeMillis()); //작성시간을 keyholder로
        Question question = new Question(null, writer, title, contents, now, 0);

        Question saved = questionDao.insert(question);

        return jspView("redirect:/qna/show?questionId=" + saved.getQuestionId());
    }
}
