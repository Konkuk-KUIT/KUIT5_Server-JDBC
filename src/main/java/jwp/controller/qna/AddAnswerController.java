package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.JsonView;
import core.mvc.ModelAndView;
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
        Answer answer = new Answer((long) Integer.parseInt(request.getParameter("questionId")), request.getParameter("writer"),
                request.getParameter("contents"));
        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(String.valueOf(answer.getQuestionId()));
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        request.setAttribute("answer", answer);
        return new ModelAndView(new JsonView()).addObject("answer", answer);
    }
}
