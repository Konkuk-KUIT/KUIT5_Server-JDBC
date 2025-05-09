package jwp.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.*;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerController extends AbstractController {
    AnswerDao answerDao = new AnswerDao();
    QuestionDao questionDao = new QuestionDao();
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(Integer.parseInt(request.getParameter("questionId")), request.getParameter("writer"), request.getParameter("contents"));

        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        return jsonView().addObject("answer", answer);
    }
}