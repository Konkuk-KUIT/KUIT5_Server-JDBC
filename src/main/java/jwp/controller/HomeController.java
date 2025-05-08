package jwp.controller;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.QuestionDao;
import jwp.model.Question;

public class HomeController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Question> questions = questionDao.findAll();
        request.setAttribute("questions", questions);
        return new JspView("/home.jsp");
    }
}
