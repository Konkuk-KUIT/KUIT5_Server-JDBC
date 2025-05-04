package jwp.model;

import java.time.LocalDateTime;

public class Answer {
    private Long answerId;
    private String writer;
    private String contents;
    private LocalDateTime createdDate;
    private int questionId;

    public Answer(String contents, String writer, int questionId) {
        this.contents = contents;
        this.writer = writer;
        this.createdDate = LocalDateTime.now();;
        this.questionId = questionId;
    }

    public Answer(Long answerId, String contents, String writer, LocalDateTime createdDate, int questionId) {
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public int getQuestionId() {
        return questionId;
    }
}