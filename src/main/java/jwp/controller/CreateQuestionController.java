package jwp.controller;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController extends AbstractController {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Question question = new Question(
                request.getParameter("contents"),
                request.getParameter("title"),
                request.getParameter("writer")
        );
        questionDao.insert(question);
        return jspView("redirect:/");
    }
}
