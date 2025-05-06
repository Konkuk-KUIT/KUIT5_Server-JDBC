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
    QuestionDao questionDao = new QuestionDao();
    AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String questionId = request.getParameter("questionId");

        Question question = questionDao.findByQuestionId(questionId);
        request.setAttribute("question", question);

        List<Answer> answers = answerDao.findAllWithQuestionId(questionId);
        return jspView("/qna/show.jsp").addObject("answers", answers);
    }
}
