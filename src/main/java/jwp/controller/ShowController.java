package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String key = req.getParameter("questionId");

        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(key);
        System.out.println(question);
        req.setAttribute("question", question);
        return "/qna/show.jsp";
    }
}
