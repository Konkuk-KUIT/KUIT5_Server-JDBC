package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.RequestMapping;
import core.mvc.controller.AbstractController;
import core.mvc.view.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

@Controller
public class AddAnswerController extends AbstractController {
    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @RequestMapping("/api/qna/addAnswer")
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Answer answer = new Answer(Integer.parseInt(req.getParameter("questionId")), req.getParameter("writer"),
                req.getParameter("contents"));

        Answer savedAnswer = answerDao.insert(answer);
        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);
        return jsonView().addObject("answer", answer);
    }
}