package jwp.controller;

import core.jdbc.KeyHolder;
import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class CreateQuestionController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int countOfAnswer = 0;

        Question question = new Question(writer, title, contents, timestamp, countOfAnswer);

        KeyHolder keyHolder = new KeyHolder();
        Question savedQuestion = questionDao.insert(question, keyHolder);

        return "redirect:/";
    }
}
