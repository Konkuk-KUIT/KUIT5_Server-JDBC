package jwp.controller;

import core.mvc.*;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQuestionController extends AbstractController {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Question question = questionDao.findByQuestionId(Long.parseLong(request.getParameter("questionId")));
        List<Answer> answers = answerDao.findAllByQuestionId(Long.parseLong(request.getParameter("questionId")));

        return jspView("/qna/show.jsp")
                .addObject("question", question)
                .addObject("answers", answers);
    }
}
