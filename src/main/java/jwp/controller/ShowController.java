package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.dao.AnswerDao;
import jwp.model.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        req.setAttribute("question", questionDao.findByQuestionId(questionId));
        req.setAttribute("answers", answers);
        return "/qna/show.jsp";
    }
}