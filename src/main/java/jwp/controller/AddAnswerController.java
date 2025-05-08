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
import java.sql.Timestamp;
import java.util.Date;

public class AddAnswerController implements Controller {
    AnswerDao answerDao = new AnswerDao();
    QuestionDao questionDao = new QuestionDao();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            Long questionId = Long.parseLong(req.getParameter("questionId"));
            String writer = req.getParameter("writer");
            String contents = req.getParameter("contents");

            Answer answer = new Answer(
                    null,
                    questionId,
                    writer,
                    contents,
                    new Timestamp(System.currentTimeMillis())
            );

            Answer savedAnswer = answerDao.insert(answer);

            Question question = questionDao.findByQuestionId(Math.toIntExact(answer.getQuestionId()));
            if (question == null) {
                throw new IllegalStateException("questionId에 해당하는 질문이 없음: " + questionId);
            }

            question.increaseCountOfAnswer();
            questionDao.updateCountOfAnswer(question);

            ObjectMapper mapper = new ObjectMapper();
            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(mapper.writeValueAsString(savedAnswer));

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}