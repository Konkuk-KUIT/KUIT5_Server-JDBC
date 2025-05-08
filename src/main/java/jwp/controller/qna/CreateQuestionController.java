package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.RequestMapping;
import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateQuestionController extends AbstractController {
    private final QuestionDao questionDao = new QuestionDao();

    @RequestMapping(value = "/qna/create", method = RequestMethod.POST)
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"));
        Question savedQuestion = questionDao.insert(question);
        return jspView("redirect:/");
    }
}
