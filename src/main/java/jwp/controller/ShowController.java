package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionIdStr = req.getParameter("questionId");

        if (questionIdStr == null) {
            return "redirect:/";
        }

        int questionId = Integer.parseInt(questionIdStr);
        Question question = questionDao.findByQuestionId(questionId);

        req.setAttribute("question", question);
        return "/qna/show.jsp";

    }
}
