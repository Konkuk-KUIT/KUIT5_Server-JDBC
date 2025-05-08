package jwp.controller;

import core.mvc.Controller;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import jwp.dao.QuestionDao;
import jwp.model.Question;

public class HomeController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        List<Question> questions = questionDao.findAll();
        model.put("questions", questions);

        return "/home.jsp";
    }
}
