package jwp.controller.qna;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class CreateQuestionController extends AbstractController {
    QuestionDao questionDao;

    public CreateQuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Question question = new Question(request.getParameter("writer"),request.getParameter("title"),
                request.getParameter("contents"),Timestamp.from(Instant.now()),0);
        Question saved = questionDao.insert(question);
        return jspView("redirect:/qna/show?questionId="+ saved.getQuestionId());
    }
}
