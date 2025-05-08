package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.QuestionDao;
import jwp.model.Question;

public class CreateQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Question question = new Question(
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents"));
        Question savedQuestion = questionDao.insert(question);
        return new JspView("redirect:/");
    }
}
