package jwp.controller;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Question question = new Question(
                request.getParameter("contents"),
                request.getParameter("title"),
                request.getParameter("writer")
        );
        questionDao.insert(question);
        return new JspView("redirect:/");
    }
}
