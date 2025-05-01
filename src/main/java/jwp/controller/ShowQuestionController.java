package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(Long.parseLong(req.getParameter("questionId")));
        req.setAttribute("question", question);
        return "/qna/show.jsp";

    }
}
