package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(Long.parseLong(req.getParameter("questionId")));

        AnswerDao answerDao = new AnswerDao();
        List<Answer> answers = answerDao.findAnswersByQuestionId(question.getQuestionId());

        req.setAttribute("answers", answers);
        req.setAttribute("question", question);
        return "/qna/show.jsp";

    }
}
