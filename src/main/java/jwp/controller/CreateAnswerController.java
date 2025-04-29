package jwp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class CreateAnswerController implements Controller {
    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();

//        if (UserSessionUtils.isLogined(session)) {
            Answer answer = new Answer(
                    Long.parseLong(req.getParameter("questionId")),
                    req.getParameter("writer"),
                    req.getParameter("contents")
            );
            Answer savedAnswer = answerDao.insert(answer);

            Question question = questionDao.findByQuestionId(savedAnswer.getQuestionId().toString());
            question.increaseCountOfAnswer();
            questionDao.updateCountOfAnswer(question);
            ObjectMapper mapper = new ObjectMapper(); // Java 객체를 JSON 문자열로 변환
            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter out = resp.getWriter(); // 서버에서 브라우저로 데이터를 직접 쓰는 스트림.
            out.print(mapper.writeValueAsString(savedAnswer));
//        }
        return "";
    }
}
