package jwp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Answer {
    private Long answerId;
    private String writer;
    private String contents;
    private LocalDateTime createdDate;
    private Long questionId;

    public Answer(Long answerId, String writer, String contents, LocalDateTime createdDate, Long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Answer(String writer, String contents, LocalDateTime createdDate, Long questionId) {
        this(-1L, writer, contents, createdDate, questionId);
    }

    public Answer(Long questionId, String writer, String contents) {
        this(-1L, writer, contents, LocalDateTime.now(), questionId);
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
        return Timestamp.valueOf(createdDate);
    }

    public Long getQuestionId() {
        return questionId;
    }
}
