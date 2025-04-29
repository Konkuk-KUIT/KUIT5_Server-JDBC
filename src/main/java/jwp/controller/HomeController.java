package jwp.controller;

import core.mvc.Controller;
import core.mvc.view.JspView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.QuestionDao;
import jwp.model.Question;

public class HomeController implements Controller {

    @Override
    public JspView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();
        req.setAttribute("questions", questions);
        return new JspView("/home.jsp");
    }
}
