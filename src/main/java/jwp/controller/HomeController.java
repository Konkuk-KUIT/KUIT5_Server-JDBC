package jwp.controller;

import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.QuestionDao;
import jwp.model.Question;

public class HomeController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Question> questions = questionDao.findAll();

        return jspView("/home.jsp").addObject("questions", questions);
    }
}
