package jwp.controller;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        request.setAttribute("questions", QuestionDao.getInstance().findAll());
        List<Question> questions = QuestionDao.getInstance().findAll();
        return jspView("/home.jsp").addObject("questions", questions);
    }
}
