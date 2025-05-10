package jwp.controller;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class CreateQuestionController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        QuestionDao questionDao = new QuestionDao();


        Question question = new Question(
                questionDao.getNewQuestionId(),
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents"),
                new Timestamp(System.currentTimeMillis()),
                0
                );
        //MemoryUserRepository.getInstance().addUser(user);
        questionDao.insert(question);

        System.out.println("질문 등록완료");

        return jspView("redirect:/");
    }
}
