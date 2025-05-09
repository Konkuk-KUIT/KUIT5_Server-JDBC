package jwp.controller.qna;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class CreateQuestionController extends AbstractController {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int countOfAnswer = 0;

        Question question = new Question(writer, title, contents, timestamp, countOfAnswer);

        questionDao.insert(question);

        return jspView("redirect:/");
    }
}