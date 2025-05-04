package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");

        QuestionDao questionDao = new QuestionDao();
        req.setAttribute("question", questionDao.findByQuestionId(questionId));

        AnswerDao answerDao = new AnswerDao();
        req.setAttribute("answers", answerDao.findAll(questionId));

        return "/qna/show.jsp";
    }
}
