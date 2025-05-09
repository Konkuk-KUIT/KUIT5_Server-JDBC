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


    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        int qid = Integer.parseInt(request.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(qid);
        List<Answer> answers = answerDao.findByQuestionId(qid);

        request.setAttribute("question", question);
        request.setAttribute("answers", answers);

        return jspView("/qna/show.jsp");
    }
}
