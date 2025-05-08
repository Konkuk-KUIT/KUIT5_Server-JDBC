package jwp.controller.qna;

import static core.mvc.view.ViewResolver.JSON_VIEW_PREFIX;

import core.mvc.controller.ControllerV1;
import core.mvc.view.ModelAndView;
import java.sql.SQLException;
import java.util.Map;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

public class AddAnswerController implements ControllerV1 {
    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();


    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        Answer answer = new Answer(Integer.parseInt(params.get("questionId")), params.get("writer"),
                params.get("contents"));

        Answer savedAnswer = answerDao.insert(answer);
        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        model.put("answer", answer);
        return JSON_VIEW_PREFIX;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        Answer answer = new Answer(Integer.parseInt(params.get("questionId")), params.get("writer"),
                params.get("contents"));

        Answer savedAnswer = answerDao.insert(answer);
        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        return new ModelAndView(JSON_VIEW_PREFIX)
                .addObject("answer", answer);
    }
}