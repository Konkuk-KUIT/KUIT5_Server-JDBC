package jwp.controller.qna;

import core.mvc.*;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//http://localhost:8080/qna/show?questionId=5
public class ShowController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String questionId = request.getParameter("questionId");
        Question question = QuestionDao.getInstance().findByQuestionId(Long.parseLong(questionId));
        List<Answer> answerByQuestionId = AnswerDao.getInstance().findByQuestionId(Long.parseLong(questionId));

        // 파라미터로 넘어온 questionId가 Answer객체의 questionId와 일치하면 보여주기
        request.setAttribute("question", question);
        request.setAttribute("answers", answerByQuestionId);
        return jspView("/qna/show.jsp");
    }
}