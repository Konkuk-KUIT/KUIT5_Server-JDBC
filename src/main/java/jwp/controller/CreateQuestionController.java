package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class CreateQuestionController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/user/loginForm";
        }

        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Question question = new Question(
                null,                          // questionId (자동 생성)
                user.getUserId(),             // 로그인한 사용자의 ID
                title,
                contents,
                LocalDateTime.now(),          // 현재 시간
                0                             // 초기 댓글 수
        );

        questionDao.insert(question);         // DB에 저장
        return "redirect:/";                  // 질문 등록 후 홈으로 이동
    }
}

