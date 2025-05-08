package jwp.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.sql.Timestamp;
import java.time.Instant;

public class Answer {
    private Long answerId;
    private String writer;
    private String contents;
    private Timestamp createdDate;
    private Long questionId;


    public Answer(Long answerId, String writer, String contents, Timestamp createdDate, Long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Answer(Long questionId, String writer, String contents) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = Timestamp.from(Instant.now());
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

    public Long getQuestionId() {
        return questionId;
    }
}
