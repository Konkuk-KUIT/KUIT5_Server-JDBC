package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

public class AddAnswerController implements Controller {
    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(Integer.parseInt(request.getParameter("questionId")), request.getParameter("writer"),
                request.getParameter("contents"));

        Answer savedAnswer = answerDao.insert(answer);
        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        request.setAttribute("answer", answer);
        return new JsonView();
    }
}