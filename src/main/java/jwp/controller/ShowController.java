package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//http://localhost:8080/qna/show?questionId=5
@Slf4j
public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");
        Question question = QuestionDao.getInstance().findByQuestionId(Long.parseLong(questionId));

        log.info("조회한 Question: {}", question);

        req.setAttribute("question", question);
        return "/qna/show.jsp";
    }
}