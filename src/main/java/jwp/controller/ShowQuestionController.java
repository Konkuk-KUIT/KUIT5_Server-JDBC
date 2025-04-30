package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String idParam = req.getParameter("questionId");
        if (idParam == null) {
            return "redirect:/";
        }

        Long questionId = Long.parseLong(idParam);
        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        req.setAttribute("question", question);
        req.setAttribute("answers", answers);

        return "/qna/show.jsp";
    }
}
