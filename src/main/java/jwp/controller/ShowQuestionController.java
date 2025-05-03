package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = questionDao.findByQuestionId(Integer.parseInt(req.getParameter("questionId")));
        req.setAttribute("question", question);
        return "/qna/show.jsp";
    }
}
