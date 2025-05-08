package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController implements Controller {


    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        int qid = Integer.parseInt(request.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(qid);
        List<Answer> answers = answerDao.findByQuestionId(qid);

        request.setAttribute("question", question);
        request.setAttribute("answers", answers);

        return new JspView("/qna/show.jsp");
    }
}
