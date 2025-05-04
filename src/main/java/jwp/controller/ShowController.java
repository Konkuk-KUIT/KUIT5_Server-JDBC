package jwp.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        QuestionDao questionDao = new QuestionDao();

        int qid = Integer.parseInt(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(qid);

        req.setAttribute("question", question);

        return "/qna/show.jsp";
    }
}
