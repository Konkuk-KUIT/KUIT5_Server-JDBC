package jwp.controller;

import core.mvc.AbstractController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.ModelAndView;
import jwp.dao.QuestionDao;
import jwp.model.Question;

public class HomeController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();

        return jspView("/home.jsp")
                .addObject("questions", questions);
    }
}
