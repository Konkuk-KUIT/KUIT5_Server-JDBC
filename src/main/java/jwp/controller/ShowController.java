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
    QuestionDao questionDao;
    AnswerDao answerDao;

    public ShowController(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        List<Question> questionList = questionDao.findAll();
        List<Answer> answerList = answerDao.findAll();
        req.setAttribute("question",question);
        req.setAttribute("answers",answerList);
        return "/qna/show.jsp";
    }
}
