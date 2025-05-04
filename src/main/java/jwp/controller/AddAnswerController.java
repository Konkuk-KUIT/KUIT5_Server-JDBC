package jwp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AddAnswerController implements Controller {
    AnswerDao answerDao = AnswerDao.getInstance();
    QuestionDao questionDao = QuestionDao.getInstance();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents"), LocalDateTime.now(), Long.parseLong(req.getParameter("questionId")));

        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(savedAnswer));

        return "";
    }
}
