package jwp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Question {
    private long questionId;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private int countOfAnswer;

    public Question(long questionId, String writer, String title, String contents, LocalDateTime createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String contents, LocalDateTime createdDate, int countofAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedDate() {
        return Timestamp.valueOf(createdDate);
    }

    public Date getCreatedDateAsDate() {
        return Timestamp.valueOf(createdDate);
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public void increaseCountOfAnswer() {
        this.countOfAnswer+=1;
    }
}
