package jwp.controller.qna;

import core.mvc.*;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController extends AbstractController {

    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long questionId = Long.parseLong(request.getParameter("questionId"));

        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.read(questionId);

        return jspView("/qna/show.jsp")
                .addObject("question", question)
                .addObject("answers", answers);
    }
}
