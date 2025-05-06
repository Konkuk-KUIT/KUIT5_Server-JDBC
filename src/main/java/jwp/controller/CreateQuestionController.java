package jwp.controller;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CreateQuestionController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 파라미터 받아서 질문 생성하기

        System.out.println(request.getParameter("writer"));
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("contents"));

        Question question = new Question(
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents"),
                Timestamp.valueOf(LocalDateTime.now())
        );
        QuestionDao questionDao = new QuestionDao();
        question = questionDao.insert(question);
//        System.out.println("question 생성 완료" + question.getQuestionId());
        return jspView("redirect:/");
    }
}
