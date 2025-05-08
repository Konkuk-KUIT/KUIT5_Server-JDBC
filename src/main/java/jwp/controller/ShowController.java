package jwp.controller;

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
        String questionIdStr = request.getParameter("questionId");

        if (questionIdStr == null) {
            return jspView("redirect:/");
        }

        int questionId = Integer.parseInt(questionIdStr);
        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        return jspView("/qna/show.jsp");

    }
}
