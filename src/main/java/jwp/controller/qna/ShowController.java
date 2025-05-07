package jwp.controller.qna;

import core.mvc.*;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController extends AbstractController {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String questionId = request.getParameter("questionId");

        request.setAttribute("question", questionDao.findByQuestionId(questionId));
        request.setAttribute("answers", answerDao.findAllByQuestionId(questionId));

        return jspView("/qna/show.jsp");
    }
}
