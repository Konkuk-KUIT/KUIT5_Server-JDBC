package jwp.controller.qna;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class CreateQuestionController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Question newQuestion = new Question(request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents"),
                LocalDateTime.now(),
                0);
        QuestionDao.getInstance().insert(newQuestion);
        return jspView("redirect:/");
    }
}

/*
@Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User modifiedUser = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
//        MemoryUserRepository.getInstance().update(modifiedUser);
        UserDao.getInstance().update(modifiedUser);
        return "redirect:/user/list";
    }

    resultSet -> { return new Question(
                        resultSet.getLong("questionId"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate").toLocalDateTime(),
                        resultSet.getInt("countofAnswer")); });
 */
