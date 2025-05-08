package jwp.controller.qna;

import core.mvc.Controller;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

public class ShowController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        String questionId = params.get("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        List<Answer> answers = answerDao.findAllByQuestionId(question.getQuestionId());

        model.put("question", question);
        model.put("answers", answers);
        return "/qna/show.jsp";
    }
}
