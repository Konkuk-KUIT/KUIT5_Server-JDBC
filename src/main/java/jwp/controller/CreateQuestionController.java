package jwp.controller;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        Question question = new Question(
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents")
        );
        Question savedQuestion = questionDao.insert(question);
        System.out.println("생성된 question의 ID : " + savedQuestion.getQuestionId());
        return jspView("redirect:/");
    }
}
