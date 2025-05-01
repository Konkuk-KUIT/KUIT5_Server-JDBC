package jwp.model;

import java.sql.Timestamp;

public class Question {
    Long questionId;
    String writer;
    String title;
    String contents;
    Timestamp createdDate;
    Integer countOfAnswer;

    public Question(Long questionId, String writer, String title, String contents, Timestamp createdDate, Integer countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }
}
