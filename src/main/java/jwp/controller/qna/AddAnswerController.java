package jwp.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.JsonView;
import core.mvc.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

public class AddAnswerController implements Controller {
    AnswerDao answerDao = new AnswerDao();
    QuestionDao questionDao = new QuestionDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(Integer.parseInt(request.getParameter("questionId")), request.getParameter("writer"),
                request.getParameter("contents"));

        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        request.setAttribute("answer", savedAnswer);

        return new JsonView();
    }
}