package jwp.controller;

import core.mvc.Controller;
import core.mvc.JsonView;
import core.mvc.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAnswerController implements Controller {
    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(
                Long.parseLong(request.getParameter("questionId")),
                request.getParameter("writer"),
                request.getParameter("contents")
        );
        answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.setCountOfAnswer(question.getCountOfAnswer() + 1);
        questionDao.update(question);

        request.setAttribute("answer", answer);

        return new JsonView();
    }
}
