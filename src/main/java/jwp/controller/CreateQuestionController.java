package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CreateQuestionController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 파라미터 받아서 질문 생성하기

        System.out.println(req.getParameter("writer"));
        System.out.println(req.getParameter("title"));
        System.out.println(req.getParameter("contents"));

        Question question = new Question(
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"),
                Timestamp.valueOf(LocalDateTime.now())
        );
        QuestionDao questionDao = new QuestionDao();
        question = questionDao.insert(question);
        System.out.println("question 생성 완료" + question.getQuestionId());
        return "redirect:/";
    }
}
