package jwp.controller.qna;

import core.mvc.controller.ControllerV2;
import java.sql.SQLException;
import java.util.Map;
import jwp.dao.QuestionDao;
import jwp.model.Question;

public class CreateQuestionController implements ControllerV2 {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        Question question = new Question(
                params.get("writer"),
                params.get("title"),
                params.get("contents"));
        Question savedQuestion = questionDao.insert(question);
        return "redirect:/";
    }
}
