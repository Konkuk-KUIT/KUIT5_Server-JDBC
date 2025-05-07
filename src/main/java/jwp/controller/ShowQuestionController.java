package jwp.controller;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = questionDao.findByQuestionId(Long.parseLong(req.getParameter("questionId")));
        List<Answer> answers = answerDao.findAllByQuestionId(Long.parseLong(req.getParameter("questionId")));
        req.setAttribute("question", question);
        req.setAttribute("answers", answers);
        return new JspView("/qna/show.jsp");
    }
}
