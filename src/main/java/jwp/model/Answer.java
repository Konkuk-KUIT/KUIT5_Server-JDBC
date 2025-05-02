package jwp.model;

import java.time.LocalDateTime;

public class Answer {
    private Long answerId;
    private Long questionId;
    private String writer;
    private String contents;
    private LocalDateTime createdDate;

    public Answer(Long answerId, Long questionId, String writer, String contents, LocalDateTime createdDate) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
