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
    QuestionDao questionDao;
    AnswerDao answerDao;

    public ShowController(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        List<Question> questionList = questionDao.findAll();
        List<Answer> answerList = answerDao.findAll();
        request.setAttribute("question",question);
        request.setAttribute("answers",answerList);
        return jspView("/qna/show.jsp");
    }
}
