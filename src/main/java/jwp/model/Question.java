package jwp.model;

import java.sql.Timestamp;
import java.time.Instant;

public class Question {

    private int questionId;
    private String writer;
    private String title;
    private String content;
    private Timestamp createdDate;
    private int countOfAnswer;

    public Question(int questionId, String writer, String title, String content, Timestamp createdDate,
                    int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = Timestamp.from(Instant.now());
        this.countOfAnswer = 0;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public void increaseCountOfAnswer() {
        countOfAnswer++;
    }

}