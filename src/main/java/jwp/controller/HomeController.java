package jwp.controller;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.QuestionDao;
import jwp.model.Question;

public class HomeController extends AbstractController {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Question> questions = questionDao.findAll();

        return jspView("/home.jsp")
                .addObject("questions", questions);
    }
}
