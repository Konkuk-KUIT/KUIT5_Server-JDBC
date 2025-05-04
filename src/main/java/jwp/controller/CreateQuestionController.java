package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class CreateQuestionController implements Controller {
    QuestionDao questionDao;

    public CreateQuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        Question question = new Question(req.getParameter("writer"),req.getParameter("title"),
                req.getParameter("contents"),Timestamp.from(Instant.now()),0);
        Question saved = questionDao.insert(question);
        return "redirect:/qna/show?questionId="+ saved.getQuestionId();
    }
}
