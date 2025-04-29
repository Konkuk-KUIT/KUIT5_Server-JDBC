package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController implements Controller {
    QuestionDao questionDao = new QuestionDao();
    AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");

        Question question = questionDao.findByQuestionId(questionId);
        req.setAttribute("question", question);

        List<Answer> answers = answerDao.findAllWithQuestionId(questionId);
        req.setAttribute("answers", answers);
        return "/qna/show.jsp";
    }
}
