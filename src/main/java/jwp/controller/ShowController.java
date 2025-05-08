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
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = new QuestionDao().findByQuestionId(questionId);
        List<Answer> answers = new AnswerDao().findByQuestionId(questionId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        return jspView( "/qna/show.jsp")
                .addObject("question", question)
                .addObject("answers", answers);
    }
}
