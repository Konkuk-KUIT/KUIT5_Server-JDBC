package jwp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AddAnswerController implements Controller {
    AnswerDao answerDao = AnswerDao.getInstance();
    QuestionDao questionDao = QuestionDao.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(request.getParameter("writer"), request.getParameter("contents"), LocalDateTime.now(), Long.parseLong(request.getParameter("questionId")));

        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);
        writeAnswerJson(response, savedAnswer);

        return "";
    }

    private void writeAnswerJson(HttpServletResponse response, Answer answer) throws IOException {
        // JSON으로 응답 반환하는 로직.
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(answer));
    }
}
