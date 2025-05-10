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
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(Long.parseLong(request.getParameter("questionId")));

        AnswerDao answerDao = new AnswerDao();
        List<Answer> answers = answerDao.findAnswersByQuestionId(question.getQuestionId());

        return jspView("/qna/show.jsp")
                .addObject("answers", answers)
                .addObject("question", question);

    }
}
