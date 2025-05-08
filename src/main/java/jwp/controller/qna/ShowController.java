package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        List<Answer> answers = answerDao.findAllByQuestionId(question.getQuestionId());

        // 모델에 데이터를 추가
        ModelAndView mav = new ModelAndView("/qna/show.jsp");
        mav.addAttribute("question", question);
        mav.addAttribute("answers", answers);

        return mav; // ModelAndView 반환
    }
}
