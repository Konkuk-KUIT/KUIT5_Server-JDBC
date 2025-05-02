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
        String questionIdParam = req.getParameter("questionId");
        if (questionIdParam == null || questionIdParam.isEmpty()) {
            return "redirect:/";
        }

        long questionId;
        try {
            questionId = Long.parseLong(questionIdParam);
        } catch (NumberFormatException e) {
            return "redirect:/";
        }

        Question question = questionDao.findById(questionId);
        if (question == null) {
            return "redirect:/";
        }

        req.setAttribute("question", question);
        return "/qna/show.jsp";
    }
}
