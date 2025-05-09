package jwp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import core.mvc.JsonView;
import core.mvc.ModelAndView;
import core.mvc.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

public class AddAnswerController implements Controller {
    AnswerDao answerDao = new AnswerDao();
    QuestionDao questionDao = new QuestionDao();
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long questionId = Long.parseLong(request.getParameter("questionId"));
        String writer = request.getParameter("writer");
        String contents = request.getParameter("contents");

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

        request.setAttribute("answer", answer);
        return new ModelAndView(new JsonView()).addObject("answer",answer);
    }

    }