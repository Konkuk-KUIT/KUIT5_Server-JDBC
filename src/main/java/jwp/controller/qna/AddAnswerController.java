package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.JsonView;
import core.mvc.ModelAndView;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

// 다른 url에서도 addanswer컨트롤러 로직이 필요한데, 다른데서 같이 쓸수 없을 때 : 계속 복제됨...
// 똑같은 로직으로 복제되면 괜찮은데 댓글 로직을 바꿔달라는 경우가 있음 -> Service 레이어를 추가하기 ! 비즈니스 로직에 대한 실행
// 을 직접 하지 않고 Service로 넘기면 로직을 바뀔 수 있다.
public class AddAnswerController implements Controller {
    AnswerDao answerDao = AnswerDao.getInstance();
    QuestionDao questionDao = QuestionDao.getInstance();
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(request.getParameter("writer"), request.getParameter("contents"), LocalDateTime.now(), Long.parseLong(request.getParameter("questionId")));

        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);
//        writeAnswerJson(response, savedAnswer);
        // json을 직접 반환하지 않고 request attribute로 넘겨주기 (그러면 JsonView render에서 반환해줌)
//        request.setAttribute("answer", answer);
        return new ModelAndView(new JsonView())
                .addObject("answer", answer);
    }

//    private void writeAnswerJson(HttpServletResponse response, Answer answer) throws IOException {
//        // JSON으로 응답 반환하는 로직.
//        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.print(mapper.writeValueAsString(answer));
//    }
}
