package jwp.controller;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.JsonView;
import core.mvc.ModelAndView;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateAnswerController extends AbstractController {
    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Answer answer = new Answer(
                Long.parseLong(request.getParameter("questionId")),
                request.getParameter("writer"),
                request.getParameter("contents")
        );
        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(savedAnswer.getQuestionId().toString());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        return jsonView().addObject("answer", answer);
    }
}
