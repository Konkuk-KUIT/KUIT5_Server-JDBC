package jwp.service;

import java.sql.SQLException;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

public class QnaService {
    private QuestionDao questionDao = new QuestionDao();
    private AnswerDao answerDao = new AnswerDao();

    public void addAnswer(Answer answer) throws SQLException {
        Answer savedAnswer = answerDao.insert(answer);
        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);
    }
}
