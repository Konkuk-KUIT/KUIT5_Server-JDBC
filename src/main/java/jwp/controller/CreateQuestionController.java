package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class CreateQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        QuestionDao questionDao = new QuestionDao();


        Question question = new Question(
                (long) (questionDao.findAll().size())+1,
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"),
                new Timestamp(System.currentTimeMillis()),
                0
                );
        //MemoryUserRepository.getInstance().addUser(user);
        questionDao.insert(question);

        System.out.println("질문 등록완료");

        return "redirect:/";
    }
}
