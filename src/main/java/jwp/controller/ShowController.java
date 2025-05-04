package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

//http://localhost:8080/qna/show?questionId=5
public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");
        Question question = QuestionDao.getInstance().findByQuestionId(Long.parseLong(questionId));
        List<Answer> answerByQuestionId = AnswerDao.getInstance().findByQuestionId(Long.parseLong(questionId));

        // 파라미터로 넘어온 questionId가 Answer객체의 questionId와 일치하면 보여주기
        req.setAttribute("question", question);
        req.setAttribute("answers", answerByQuestionId);
        return "/qna/show.jsp";
    }
}