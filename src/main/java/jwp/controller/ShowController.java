package jwp.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController implements Controller {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        int qid = Integer.parseInt(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(qid);
        List<Answer> answers = answerDao.findByQuestionId(qid);

        req.setAttribute("question", question);
        req.setAttribute("answers", answers);

        return "/qna/show.jsp";
    }
}
