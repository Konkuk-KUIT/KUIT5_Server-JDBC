package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class CreateQuestionController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(req.getParameter("writer"),req.getParameter("title"),req.getParameter("contents"), new Timestamp(System.currentTimeMillis()),0);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(question);
        return "/";
    }
}
