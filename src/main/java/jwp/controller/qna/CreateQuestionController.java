package jwp.controller.qna;

import core.mvc.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import core.mvc.ModelAndView;

public class CreateQuestionController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        Question question = new Question(
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents"));
        Question savedQuestion = questionDao.insert(question);
        return jspView("redirect:/");
    }
}
