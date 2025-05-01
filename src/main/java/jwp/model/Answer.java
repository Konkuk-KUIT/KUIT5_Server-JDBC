package jwp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Answer {
    private Long answerId;
    private String writer;
    private String contents;
    private Timestamp createdDate;
    private int questionId;

    public Answer(String contents, String writer, int questionId) {
        this.contents = contents;
        this.writer = writer;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.questionId = questionId;
    }

    public Answer(Long answerId, String contents, String writer, Timestamp createdDate, int questionId) {
        this.contents = contents;
        this.answerId = answerId;
        this.writer = writer;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public int getQuestionId() {
        return questionId;
    }
}
